package com.spotify_back.catalogcontext.repository;

import com.spotify_back.catalogcontext.domain.Favorite;
import com.spotify_back.catalogcontext.domain.FavoriteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId> {
}
