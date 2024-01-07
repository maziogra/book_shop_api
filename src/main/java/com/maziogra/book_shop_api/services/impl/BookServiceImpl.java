package com.maziogra.book_shop_api.services.impl;

import com.maziogra.book_shop_api.domain.entities.BookEntity;
import com.maziogra.book_shop_api.repositories.BookRepository;
import com.maziogra.book_shop_api.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

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

    @Override
    public Optional<BookEntity> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
            return bookRepository.existsById(id);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookEntity partialEdit(BookEntity bookEntity) {
        return bookRepository.findById(bookEntity.getId()).map(existingBook -> {
            Optional.ofNullable(bookEntity.getTitle()).ifPresent(existingBook::setTitle);
            Optional.ofNullable(bookEntity.getReleaseDate()).ifPresent(existingBook::setReleaseDate);
            Optional.ofNullable(bookEntity.getAuthors()).ifPresent(existingBook::setAuthors);
            Optional.ofNullable(bookEntity.getGenres()).ifPresent(existingBook::setGenres);
            return bookRepository.save(existingBook);
        }).orElseThrow(() -> new RuntimeException("Book does not exist"));
    }
}
