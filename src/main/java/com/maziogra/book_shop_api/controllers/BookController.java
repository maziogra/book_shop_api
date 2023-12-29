package com.maziogra.book_shop_api.controllers;


import com.maziogra.book_shop_api.domain.DTO.BookDTO;
import com.maziogra.book_shop_api.domain.entities.AuthorEntity;
import com.maziogra.book_shop_api.domain.entities.BookEntity;
import com.maziogra.book_shop_api.mappers.mapperImpl.BookMapper;
import com.maziogra.book_shop_api.services.impl.AuthorServiceImpl;
import com.maziogra.book_shop_api.services.impl.BookServiceImpl;
import com.maziogra.book_shop_api.utilities.Utilities;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    private final Utilities utilities;
    private final BookMapper bookMapper;
    private final BookServiceImpl bookServiceImpl;
    private final AuthorServiceImpl authorServiceImpl;

    public BookController(Utilities utilities, BookMapper bookMapper, BookServiceImpl bookServiceImpl, AuthorServiceImpl authorServiceImpl) {
        this.utilities = utilities;
        this.bookMapper = bookMapper;
        this.bookServiceImpl = bookServiceImpl;
        this.authorServiceImpl = authorServiceImpl;
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO){
        BookEntity bookEntity;
        AuthorEntity authorEntity = utilities.authorInsideBook(bookDTO);
        if(authorEntity == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        bookEntity = bookMapper.mapFrom(bookDTO);
        bookEntity.setAuthorEntity(authorEntity);
        bookEntity = bookServiceImpl.save(bookEntity);
        return new ResponseEntity<>(bookMapper.mapTo(bookEntity), HttpStatus.OK);
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

    @GetMapping(path = "/author/{id}")
    public ResponseEntity<List<BookDTO>> getBooksByAuthor(@PathVariable(value = "id") Long id){
        Optional<AuthorEntity> authorEntityOptional = authorServiceImpl.getAuthorById(id);
        if(authorEntityOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        AuthorEntity authorEntity = authorEntityOptional.orElseThrow(() -> new RuntimeException("Cannot found author"));
        List<BookEntity> books = bookServiceImpl.getBooksByAuthors(authorEntity);
        return new ResponseEntity<>(books.stream().map(bookMapper::mapTo).toList(), HttpStatus.OK);
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
        BookEntity bookEntity;
        if(!bookServiceImpl.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        AuthorEntity authorEntity = utilities.authorInsideBook(bookDTO);
        if(authorEntity == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        bookEntity = bookMapper.mapFrom(bookDTO);
        bookEntity.setId(id);
        bookEntity.setAuthorEntity(authorEntity);
        bookEntity = bookServiceImpl.save(bookEntity);
        return new ResponseEntity<>(bookMapper.mapTo(bookEntity), HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<BookDTO> partialEditBook(@PathVariable(value = "id") Long id, @RequestBody BookDTO bookDTO){
        BookEntity bookEntity;
        if(!bookServiceImpl.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        AuthorEntity authorEntity = utilities.authorInsideBook(bookDTO);
        bookEntity = bookMapper.mapFrom(bookDTO);
        bookEntity.setId(id);
        bookEntity.setAuthorEntity(authorEntity);
        BookEntity updated = bookServiceImpl.partialEdit(bookEntity);
        return new ResponseEntity<>(bookMapper.mapTo(updated), HttpStatus.OK);
    }

}
