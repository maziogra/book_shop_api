package com.maziogra.book_api.repositories;

import com.maziogra.book_api.domain.entities.AuthorEntity;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {
}
