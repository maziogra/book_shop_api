package com.maziogra.book_shop_api.domain.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenreDTO {
    private Long id;
    private String name;
    @JsonIgnore
    private Set<BookDTO> books;
    @JsonIgnore
    public boolean isNull(){
        return (this.getName() == null);
    }
}