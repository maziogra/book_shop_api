package com.maziogra.book_shop_api.services.impl;

import com.maziogra.book_shop_api.domain.entities.AuthorEntity;
import com.maziogra.book_shop_api.repositories.AuthorRepository;
import com.maziogra.book_shop_api.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

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

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public boolean isExists(Long id){
        return authorRepository.existsById(id);
    }

    @Override
    public AuthorEntity partialEdit(AuthorEntity authorEntity) {
        return authorRepository.findById(authorEntity.getId()).map(author -> {
            Optional.ofNullable(authorEntity.getName()).ifPresent(author::setName);
            Optional.ofNullable(authorEntity.getAge()).ifPresent(author::setAge);
            Optional.ofNullable(authorEntity.getDead()).ifPresent(author::setDead);
            return authorRepository.save(author);
        }).orElseThrow(() -> new RuntimeException("Cannot found the author"));
    }


}
