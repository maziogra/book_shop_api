package com.maziogra.book_api.services.impl;

import com.maziogra.book_api.domain.entities.AuthorEntity;
import com.maziogra.book_api.repositories.AuthorRepository;
import com.maziogra.book_api.services.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity save(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }
}
