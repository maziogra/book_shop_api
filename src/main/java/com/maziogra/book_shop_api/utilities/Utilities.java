package com.maziogra.book_shop_api.utilities;

import com.maziogra.book_shop_api.domain.DTO.AuthorDTO;
import com.maziogra.book_shop_api.domain.DTO.BookDTO;
import com.maziogra.book_shop_api.domain.DTO.GenreDTO;
import com.maziogra.book_shop_api.domain.entities.AuthorEntity;
import com.maziogra.book_shop_api.domain.entities.GenreEntity;
import com.maziogra.book_shop_api.services.impl.AuthorServiceImpl;
import com.maziogra.book_shop_api.services.impl.GenreServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Utilities {
    private final AuthorServiceImpl authorService;
    private final GenreServiceImpl genreService;

    public Set<AuthorEntity> authorInsideBook(BookDTO bookDTO){
        AuthorEntity authorEntity;
        if(bookDTO.getAuthors() == null){
            return null;
        }
        List<AuthorDTO> authorDTOs = new ArrayList<>(bookDTO.getAuthors());
        Set<AuthorEntity> authors = new HashSet<>();
        for(AuthorDTO author : authorDTOs){
            if(author == null){
                return null;
            } else if(author.getId() == null){
                return null;
            } else if(authorService.isExists(author.getId())){
                authorEntity = authorService.getAuthorById(author.getId())
                        .orElseThrow(() -> new RuntimeException("Cannot find author"));
            } else{
                return null;
            }
            authors.add(authorEntity);
        }
        return authors;
    }

    public Set<GenreEntity> genreInsideBook(BookDTO bookDTO){
        GenreEntity genreEntity;
        if(bookDTO.getGenres() == null){
            return null;
        }
        List<GenreDTO> genreDTOs = new ArrayList<>(bookDTO.getGenres());
        Set<GenreEntity> genres = new HashSet<>();
        for(GenreDTO genre: genreDTOs){
            if(genre == null){
                return null;
            } else if(genre.getId() == null){
                return null;
            } else if(genreService.isExists(genre.getId())){
                genreEntity = genreService.getGenreById(genre.getId())
                        .orElseThrow(() -> new RuntimeException("Cannot find Genre"));
            } else{
                return null;
            }
            genres.add(genreEntity);
        }
        return genres;
    }
}
