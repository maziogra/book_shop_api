package com.maziogra.book_shop_api.domain.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {
    private Long id;
    private String title;
    private Set<AuthorDTO> authors;
    private Set<GenreDTO> genres;

    @JsonIgnore
    public boolean isNull(){
        return (this.getTitle() == null) || (this.getAuthors() == null) || (this.getGenres() == null);
    }
}
