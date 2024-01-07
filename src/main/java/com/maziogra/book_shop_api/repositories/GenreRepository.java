package com.maziogra.book_shop_api.repositories;

import com.maziogra.book_shop_api.domain.entities.GenreEntity;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<GenreEntity, Long> {
}
