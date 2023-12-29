package com.maziogra.book_shop_api.services;

import com.maziogra.book_shop_api.domain.entities.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    AuthorEntity save(AuthorEntity authorEntity);

    List<AuthorEntity> getAuthors();

    Optional<AuthorEntity> getAuthorById(Long id);

    void delete(Long id);

    boolean isExists(Long id);

    AuthorEntity partialEdit(AuthorEntity authorEntity);
}
