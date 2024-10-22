package com.spotify_back.usercontext;

public record ReadUserDto(
        String firstName,String lastName,String email,
        String imageUrl
) {
}
