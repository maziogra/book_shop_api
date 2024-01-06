package com.maziogra.book_shop_api.utilities;

import com.maziogra.book_shop_api.domain.DTO.AuthorDTO;
import com.maziogra.book_shop_api.domain.DTO.BookDTO;
import com.maziogra.book_shop_api.domain.entities.AuthorEntity;
import com.maziogra.book_shop_api.services.impl.AuthorServiceImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Utilities {
    private final AuthorServiceImpl authorService;

    public Utilities(AuthorServiceImpl authorService) {
        this.authorService = authorService;
    }

    public Set<AuthorEntity> authorInsideBook(BookDTO bookDTO){
        AuthorEntity authorEntity;
        List<AuthorDTO> authorDTOs = new ArrayList<>(bookDTO.getAuthors());
        Set<AuthorEntity> authors = new HashSet<>();
        for(AuthorDTO author : authorDTOs){
            if(author == null){
                return null;
            } else if(author.getId() == null){
                return null;
            }
            if(authorService.isExists(author.getId())){
                authorEntity = authorService.getAuthorById(author.getId())
                        .orElseThrow(() -> new RuntimeException("Cannot find author"));
            } else{
                return null;
            }
            authors.add(authorEntity);
        }
        return authors;
    }
}
