package com.spotify_back.catalogcontext.repository;

import com.spotify_back.catalogcontext.domain.SongContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongContentRepository extends JpaRepository<SongContent,Long> {
}
