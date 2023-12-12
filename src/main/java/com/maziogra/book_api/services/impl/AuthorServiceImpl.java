package com.maziogra.book_api.services.impl;

import com.maziogra.book_api.domain.entities.AuthorEntity;
import com.maziogra.book_api.repositories.AuthorRepository;
import com.maziogra.book_api.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

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

    @Override
    public List<AuthorEntity> getAuthors() {
        return StreamSupport.stream(
                authorRepository.findAll()
                        .spliterator(),
                        false)
                .toList();
    }

    @Override
    public Optional<AuthorEntity> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

}
