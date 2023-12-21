package com.maziogra.book_api.services;

import com.maziogra.book_api.domain.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity save(BookEntity bookEntity);

    List<BookEntity> getBooks();

    Optional<BookEntity> getBookById(Long id);

    boolean isExists(Long id);

    void delete(Long id);
}
