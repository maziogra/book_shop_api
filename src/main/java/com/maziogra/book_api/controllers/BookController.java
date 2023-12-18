package com.maziogra.book_api.controllers;


import com.maziogra.book_api.domain.DTO.BookDTO;
import com.maziogra.book_api.domain.entities.AuthorEntity;
import com.maziogra.book_api.domain.entities.BookEntity;
import com.maziogra.book_api.mappers.mapperImpl.BookMapper;
import com.maziogra.book_api.services.impl.AuthorServiceImpl;
import com.maziogra.book_api.services.impl.BookServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    private final AuthorServiceImpl authorService;
    private final BookMapper bookMapper;
    private final BookServiceImpl bookServiceImpl;

    public BookController(AuthorServiceImpl authorService, BookMapper bookMapper, BookServiceImpl bookServiceImpl) {
        this.authorService = authorService;
        this.bookMapper = bookMapper;
        this.bookServiceImpl = bookServiceImpl;
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO){
        BookEntity bookEntity;
        if(bookDTO.getAuthor().getId() != null && authorService.isExists(bookDTO.getAuthor().getId())){
            Optional<AuthorEntity> existingAuthorOptional = authorService.getAuthorById(bookDTO.getAuthor().getId());
            AuthorEntity existingAuthor = existingAuthorOptional.orElseGet(() ->
                    authorService.save(new AuthorEntity(
                            bookDTO.getAuthor().getId(),
                            bookDTO.getAuthor().getName(),
                            bookDTO.getAuthor().getAge())));
            bookEntity = BookEntity.builder()
                    .title(bookDTO.getTitle())
                    .authorEntity(existingAuthor)
                    .build();
        } else{
            if(bookDTO.getAuthor().getName() == null || bookDTO.getAuthor().getAge() == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            bookEntity = bookMapper.mapFrom(bookDTO);
        }
        BookEntity savedBook = bookServiceImpl.save(bookEntity);
        return new ResponseEntity<>(bookMapper.mapTo(bookEntity), HttpStatus.OK);
    }
}
