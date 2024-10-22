package com.spotify_back.catalogcontext.repository;

import com.spotify_back.catalogcontext.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song,Long> {
}
