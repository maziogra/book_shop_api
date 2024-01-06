package com.maziogra.book_shop_api.repositories;

import com.maziogra.book_shop_api.domain.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookEntity, Long> {
}
