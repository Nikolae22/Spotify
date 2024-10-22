package com.spotify_back.usercontext.repository;

import com.spotify_back.usercontext.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
