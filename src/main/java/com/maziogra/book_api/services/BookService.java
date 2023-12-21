package com.maziogra.book_api.services;

import com.maziogra.book_api.domain.entities.BookEntity;

import java.util.List;

public interface BookService {
    BookEntity save(BookEntity bookEntity);

    List<BookEntity> getBooks();
}
