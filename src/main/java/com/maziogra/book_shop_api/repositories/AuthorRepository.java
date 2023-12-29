package com.maziogra.book_shop_api.repositories;

import com.maziogra.book_shop_api.domain.entities.AuthorEntity;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {
}
