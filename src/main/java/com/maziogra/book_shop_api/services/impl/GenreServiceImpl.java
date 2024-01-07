package com.maziogra.book_shop_api.services.impl;

import com.maziogra.book_shop_api.domain.entities.GenreEntity;
import com.maziogra.book_shop_api.repositories.GenreRepository;
import com.maziogra.book_shop_api.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public GenreEntity save(GenreEntity genreEntity) {
        return genreRepository.save(genreEntity);
    }

    @Override
    public List<GenreEntity> getGenres() {
        return StreamSupport.stream(
                genreRepository.findAll()
                        .spliterator(),
                false
        ).toList();
    }

    @Override
    public Optional<GenreEntity> getGenreById(Long id) {
        return genreRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return genreRepository.existsById(id);
    }

    @Override
    public void delete(Long id) {
        genreRepository.deleteById(id);
    }
}
