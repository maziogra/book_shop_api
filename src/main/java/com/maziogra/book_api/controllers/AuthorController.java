package com.maziogra.book_api.controllers;

import com.maziogra.book_api.domain.DTO.AuthorDTO;
import com.maziogra.book_api.domain.entities.AuthorEntity;
import com.maziogra.book_api.mappers.mapperImpl.AuthorMapper;
import com.maziogra.book_api.services.impl.AuthorServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return new ResponseEntity<>(authorMapper.mapTo(authorEntity), HttpStatus.CREATED);
    }

    @GetMapping
    public List<AuthorDTO> getAuthors(){
        List<AuthorEntity> authors = authorServiceImpl.getAuthors();
        return authors.stream().map(authorMapper::mapTo).toList();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable(value = "id") Long id){
        Optional<AuthorEntity> author = authorServiceImpl.getAuthorById(id);
        return author.map(authorEntity -> {
            AuthorDTO authorDTO = authorMapper.mapTo(authorEntity);
            return new ResponseEntity<>(authorDTO, HttpStatus.FOUND);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteAuthorById(@PathVariable(value = "id") Long id){
        if(!authorServiceImpl.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authorServiceImpl.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AuthorDTO> fullEditAuthor(@PathVariable(value = "id") Long id, @RequestBody AuthorDTO authorDTO){
        if(!authorServiceImpl.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authorDTO.setId(id);
        AuthorEntity authorEntity = authorServiceImpl.save(authorMapper.mapFrom(authorDTO));
        return ResponseEntity.ok(authorMapper.mapTo(authorEntity));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<AuthorDTO> partialEditAuthor(@PathVariable(value = "id") Long id, @RequestBody AuthorDTO authorDTO){
        if(!authorServiceImpl.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authorDTO.setId(id);
        AuthorEntity authorEntity = authorServiceImpl.partialEdit(authorMapper.mapFrom(authorDTO));
        return ResponseEntity.ok(authorMapper.mapTo(authorEntity));
    }
}
