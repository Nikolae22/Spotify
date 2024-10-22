package com.spotify_back.application.dto;

import jakarta.persistence.Lob;

import java.util.UUID;

public record SongContentDto(
        UUID publicId,
        @Lob byte[] file,
        String fileContentType
) {
}
