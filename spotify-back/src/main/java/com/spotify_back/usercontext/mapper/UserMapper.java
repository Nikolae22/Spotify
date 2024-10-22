package com.spotify_back.usercontext.mapper;


import com.spotify_back.usercontext.ReadUserDto;
import com.spotify_back.usercontext.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    ReadUserDto readUserDtoToUser(User entity);
}
