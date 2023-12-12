package com.maziogra.book_api.controllers;

import com.maziogra.book_api.domain.DTO.AuthorDTO;
import com.maziogra.book_api.domain.entities.AuthorEntity;
import com.maziogra.book_api.mappers.mapperImpl.AuthorMapper;
import com.maziogra.book_api.services.impl.AuthorServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/authors")
public class AuthorController {
    private final AuthorMapper authorMapper;
    private final AuthorServiceImpl authorServiceImpl;

    public AuthorController(AuthorMapper authorMapper, AuthorServiceImpl authorServiceImpl) {
        this.authorMapper = authorMapper;
        this.authorServiceImpl = authorServiceImpl;
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO){
        AuthorEntity authorEntity = authorServiceImpl.save(authorMapper.mapFrom(authorDTO));
        return new ResponseEntity<>(authorMapper.mapTo(authorEntity), HttpStatus.OK);
    }

    @GetMapping
    public List<AuthorDTO> getAuthors(){
        List<AuthorEntity> authors = authorServiceImpl.getAuthors();
        return authors.stream().map(authorMapper::mapTo).toList();
    }
}
