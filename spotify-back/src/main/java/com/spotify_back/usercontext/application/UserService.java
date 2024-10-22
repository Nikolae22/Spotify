package com.spotify_back.usercontext.application;

import com.spotify_back.usercontext.ReadUserDto;
import com.spotify_back.usercontext.domain.User;
import com.spotify_back.usercontext.mapper.UserMapper;
import com.spotify_back.usercontext.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void syncWithIdp(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        User user = mapOauth2AttributeToUser(attributes);
        Optional<User> existingUser = userRepository.findOneByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            if (attributes.get("updated_at") != null) {
                Instant dbLastModifiedDate = existingUser.orElseThrow().getLastModifiedDate();
                Instant idpModifiedDate;
                if(attributes.get("updated_at") instanceof Instant) {
                    idpModifiedDate = (Instant) attributes.get("updated_at");
                } else {
                    idpModifiedDate = Instant.ofEpochSecond((Integer) attributes.get("updated_at"));
                }
                if(idpModifiedDate.isAfter(dbLastModifiedDate)) {
                    updateUser(user);
                }
            }
        } else {
            userRepository.saveAndFlush(user);
        }
    }

    public ReadUserDto getAuthenticatedUserFromSecurityContext(){
        OAuth2User principal=(OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user=mapOauth2AttributeToUser(principal.getAttributes());
        return userMapper.readUserDtoToUser(user);
    }

    private void updateUser(User user){
        Optional<User> userToUpdateOpt=userRepository.findOneByEmail(user.getEmail());
        if(userToUpdateOpt.isPresent()){
            User userToUpdate=userToUpdateOpt.get();
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setImageUrl(user.getImageUrl());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setFirtName(user.getFirtName());

            userRepository.saveAndFlush(userToUpdate);
        }
    }

    private User mapOauth2AttributeToUser(Map<String,Object> attributes){
        User user=new User();
        String sub=String.valueOf(attributes.get("sub"));

        String username=null;

        if(attributes.get("preferred_username" ) !=null){
            username = ((String) attributes.get("preferred_username")).toLowerCase();
        }

        if(attributes.get("given_name") !=null){
            user.setFirtName((String) attributes.get("given_name"));
        }else  if (attributes.get("name")!=null){
            user.setFirtName((String) attributes.get("name"));
        }

        if(attributes.get("family_name")!=null){
            user.setLastName((String) attributes.get("family_name"));
        }

        if(attributes.get("email")!=null){
            user.setEmail((String) attributes.get("email"));
        }else if(sub.contains("|") && (username !=null && username.contains("@"))){
            user.setEmail(username);
        }else {
            user.setEmail(sub);
        }

        if(attributes.get("picture")!=null){
            user.setImageUrl((String) attributes.get("pictures"));
        }
        return user;
    }
}
