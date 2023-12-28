package com.maziogra.book_api.repositories;

import com.maziogra.book_api.domain.DTO.BookDTO;
import com.maziogra.book_api.domain.entities.AuthorEntity;
import com.maziogra.book_api.domain.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<BookEntity, Long> {
    List<BookEntity> getBookEntitiesByAuthorEntity(AuthorEntity authorEntity);
}
