package com.maziogra.book_api.services;

import com.maziogra.book_api.domain.entities.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    AuthorEntity save(AuthorEntity authorEntity);

    List<AuthorEntity> getAuthors();

    Optional<AuthorEntity> getAuthorById(Long id);
}
