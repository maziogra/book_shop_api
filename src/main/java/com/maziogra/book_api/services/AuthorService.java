package com.maziogra.book_api.services;

import com.maziogra.book_api.domain.entities.AuthorEntity;

public interface AuthorService {
    AuthorEntity save(AuthorEntity authorEntity);
}
