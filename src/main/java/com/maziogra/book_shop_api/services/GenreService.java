package com.maziogra.book_shop_api.services;

import com.maziogra.book_shop_api.domain.entities.GenreEntity;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    GenreEntity save(GenreEntity genreEntity);

    List<GenreEntity> getGenres();

    Optional<GenreEntity> getGenreById(Long id);

    boolean isExists(Long id);

    void delete(Long id);
}
