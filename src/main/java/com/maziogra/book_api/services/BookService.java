package com.maziogra.book_api.services;

import com.maziogra.book_api.domain.entities.BookEntity;

public interface BookService {
    BookEntity save(BookEntity bookEntity);
}
