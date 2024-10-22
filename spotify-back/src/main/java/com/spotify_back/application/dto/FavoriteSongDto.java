package com.spotify_back.application.dto;

import org.springframework.lang.NonNull;

import java.util.UUID;

public record FavoriteSongDto(
        @NonNull boolean favorite,
        @NonNull UUID publicId
        ) {
}
