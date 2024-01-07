package com.maziogra.book_shop_api.controllers;


import com.maziogra.book_shop_api.domain.DTO.BookDTO;
import com.maziogra.book_shop_api.domain.entities.AuthorEntity;
import com.maziogra.book_shop_api.domain.entities.BookEntity;
import com.maziogra.book_shop_api.domain.entities.GenreEntity;
import com.maziogra.book_shop_api.mappers.mapperImpl.BookMapper;
import com.maziogra.book_shop_api.services.impl.BookServiceImpl;
import com.maziogra.book_shop_api.utilities.Utilities;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "/books")
@RequiredArgsConstructor
public class BookController {

    private final Utilities utilities;
    private final BookMapper bookMapper;
    private final BookServiceImpl bookServiceImpl;

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO){
        if(bookDTO.isNull()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BookEntity bookEntity;
        Set<AuthorEntity> authors = utilities.authorInsideBook(bookDTO);
        Set<GenreEntity> genres = utilities.genreInsideBook(bookDTO);
        if(authors == null || genres == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        bookEntity = bookMapper.mapFrom(bookDTO);
        bookEntity.setAuthors(authors);
        bookEntity.setGenres(genres);
        bookEntity = bookServiceImpl.save(bookEntity);
        return new ResponseEntity<>(bookMapper.mapTo(bookEntity), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getBooks(){
        List<BookEntity> books = bookServiceImpl.getBooks();
        return ResponseEntity.ok(books.stream().map(bookMapper::mapTo).toList());
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
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable(value = "id") Long id){
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
        if(bookDTO.isNull()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Set<AuthorEntity> authors = utilities.authorInsideBook(bookDTO);
        Set<GenreEntity> genres = utilities.genreInsideBook(bookDTO);
        if(authors == null || genres == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        bookEntity = bookMapper.mapFrom(bookDTO);
        bookEntity.setId(id);
        bookEntity.setAuthors(authors);
        bookEntity.setGenres(genres);
        bookEntity = bookServiceImpl.save(bookEntity);
        return new ResponseEntity<>(bookMapper.mapTo(bookEntity), HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<BookDTO> partialEditBook(@PathVariable(value = "id") Long id, @RequestBody BookDTO bookDTO){
        BookEntity bookEntity;
        if(!bookServiceImpl.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Set<AuthorEntity> authors = utilities.authorInsideBook(bookDTO);
        Set<GenreEntity> genres = utilities.genreInsideBook(bookDTO);
        bookEntity = bookMapper.mapFrom(bookDTO);
        bookEntity.setId(id);
        bookEntity.setAuthors(authors);
        bookEntity.setGenres(genres);
        BookEntity updated = bookServiceImpl.partialEdit(bookEntity);
        return new ResponseEntity<>(bookMapper.mapTo(updated), HttpStatus.OK);
    }

}
