package com.maziogra.book_api.controllers;


import com.maziogra.book_api.domain.DTO.BookDTO;
import com.maziogra.book_api.domain.entities.AuthorEntity;
import com.maziogra.book_api.domain.entities.BookEntity;
import com.maziogra.book_api.mappers.mapperImpl.BookMapper;
import com.maziogra.book_api.services.impl.AuthorServiceImpl;
import com.maziogra.book_api.services.impl.BookServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        BookEntity bookEntity = authorCheck(bookDTO);
        if(bookEntity == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BookEntity savedBook = bookServiceImpl.save(bookEntity);
        return new ResponseEntity<>(bookMapper.mapTo(savedBook), HttpStatus.OK);
    }

    @GetMapping
    public List<BookDTO> getBooks(){
        List<BookEntity> books = bookServiceImpl.getBooks();
        return books.stream().map(bookMapper::mapTo).toList();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable(value = "id") Long id){
        Optional<BookEntity> bookEntity = bookServiceImpl.getBookById(id);
        return bookEntity.map(book -> {
            BookDTO bookDTO = bookMapper.mapTo(book);
            return new ResponseEntity<>(bookDTO, HttpStatus.FOUND);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteBooksById(@PathVariable(value = "id") Long id){
        if(!bookServiceImpl.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookServiceImpl.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<BookDTO> fullEditBook(@PathVariable(value = "id") Long id, @RequestBody BookDTO bookDTO){
        BookEntity bookEntity = authorCheck(bookDTO);
        if(bookEntity == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(!bookServiceImpl.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookEntity.setId(id);
        bookEntity = bookServiceImpl.save(bookEntity);
        return ResponseEntity.ok(bookMapper.mapTo(bookEntity));
    }

    public BookEntity authorCheck(BookDTO bookDTO){
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
            return bookEntity;
        } else{
            if(bookDTO.getAuthor().getName() == null || bookDTO.getAuthor().getAge() == null){
                return null;
            }
        }
        return bookMapper.mapFrom(bookDTO);
    }
}
