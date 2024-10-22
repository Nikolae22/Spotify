package com.spotify_back.application.vo;

import jakarta.validation.constraints.NotBlank;

public record SongAuthroVO (@NotBlank String value) {
}
