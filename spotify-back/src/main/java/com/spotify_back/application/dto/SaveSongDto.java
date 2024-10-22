package com.spotify_back.application.dto;

import com.spotify_back.application.vo.SongAuthroVO;
import com.spotify_back.application.vo.SongTitleVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record SaveSongDto(
        @Valid SongTitleVO songTitleVO,
        @Valid SongAuthroVO songAuthroVO,
        @NotNull byte[] cover,
        @NotNull String coverContentType,
        @NotNull byte[] file,
        @NotNull String fileContentType
        ) {
}
