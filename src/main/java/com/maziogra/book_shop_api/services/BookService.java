package com.maziogra.book_shop_api.services;

import com.maziogra.book_shop_api.domain.entities.AuthorEntity;
import com.maziogra.book_shop_api.domain.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity save(BookEntity bookEntity);

    List<BookEntity> getBooks();

    Optional<BookEntity> getBookById(Long id);

    List<BookEntity> getBooksByAuthors(AuthorEntity authorEntity);

    boolean isExists(Long id);

    void delete(Long id);

    BookEntity partialEdit(BookEntity bookEntity);
}
