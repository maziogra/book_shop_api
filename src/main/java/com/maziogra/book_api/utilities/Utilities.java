package com.maziogra.book_api.utilities;

import com.maziogra.book_api.domain.DTO.AuthorDTO;
import com.maziogra.book_api.domain.DTO.BookDTO;
import com.maziogra.book_api.domain.entities.AuthorEntity;
import com.maziogra.book_api.services.impl.AuthorServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class Utilities {
    private final AuthorServiceImpl authorService;

    public Utilities(AuthorServiceImpl authorService) {
        this.authorService = authorService;
    }

    public AuthorEntity authorInsideBook(BookDTO bookDTO){
        AuthorEntity authorEntity;
        if(bookDTO.getAuthor() == null){
            return null;
        } else if(bookDTO.getAuthor().getId() == null){
            bookDTO.getAuthor().setId(-1L);
        }
        if(authorService.isExists(bookDTO.getAuthor().getId())){
            authorEntity = authorService.getAuthorById(bookDTO.getAuthor().getId())
                    .orElseThrow(() -> new RuntimeException("Cannot find author"));
        } else if(bookDTO.getAuthor().getName() == null || bookDTO.getAuthor().getAge() == null || bookDTO.getAuthor().getDead() == null){
            return null;
        } else{
            authorEntity = authorService.save(AuthorEntity.builder()
                    .name(bookDTO.getAuthor().getName())
                    .age(bookDTO.getAuthor().getAge())
                    .dead(bookDTO.getAuthor().getDead())
                    .build());
        }
        return authorEntity;
    }
}
