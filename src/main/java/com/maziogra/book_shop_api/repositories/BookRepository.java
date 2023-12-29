package com.maziogra.book_shop_api.repositories;

import com.maziogra.book_shop_api.domain.entities.AuthorEntity;
import com.maziogra.book_shop_api.domain.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<BookEntity, Long> {
    List<BookEntity> getBookEntitiesByAuthorEntity(AuthorEntity authorEntity);
}
