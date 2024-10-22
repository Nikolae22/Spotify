package com.spotify_back.application.dto;

import com.spotify_back.application.vo.SongAuthroVO;
import com.spotify_back.application.vo.SongTitleVO;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class ReadSongInfoDto {
    private SongTitleVO title;
    private SongAuthroVO author;
    @NotNull
    private byte[] cover;

    @NotNull
    private String coverContentType;

    @NotNull
    private String isFavorite;

    @NotNull
    private UUID publicid;

    public SongTitleVO getTitle() {
        return title;
    }

    public void setTitle(SongTitleVO title) {
        this.title = title;
    }

    public SongAuthroVO getAuthor() {
        return author;
    }

    public void setAuthor(SongAuthroVO author) {
        this.author = author;
    }

    @NotNull
    public byte[] getCover() {
        return cover;
    }

    public void setCover(@NotNull byte[] cover) {
        this.cover = cover;
    }

    public @NotNull String getCoverContentType() {
        return coverContentType;
    }

    public void setCoverContentType(@NotNull String coverContentType) {
        this.coverContentType = coverContentType;
    }

    public @NotNull String getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(@NotNull String isFavorite) {
        this.isFavorite = isFavorite;
    }

    public @NotNull UUID getPublicid() {
        return publicid;
    }

    public void setPublicid(@NotNull UUID publicid) {
        this.publicid = publicid;
    }
}
