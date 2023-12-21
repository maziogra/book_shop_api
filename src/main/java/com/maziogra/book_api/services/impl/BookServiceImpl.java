package com.maziogra.book_api.services.impl;

import com.maziogra.book_api.domain.entities.BookEntity;
import com.maziogra.book_api.repositories.BookRepository;
import com.maziogra.book_api.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity save(BookEntity bookEntity) {
        return bookRepository.save(bookEntity);
    }

    @Override
    public List<BookEntity> getBooks() {
        return StreamSupport.stream(
                        bookRepository.findAll()
                                .spliterator(),
                        false)
                .toList();
    }
}