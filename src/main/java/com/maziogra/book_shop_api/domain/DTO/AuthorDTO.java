package com.maziogra.book_shop_api.domain.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDTO {
    private Long id;
    private String name;
    private Integer age;
    private Boolean dead;
    @JsonIgnore
    private Set<BookDTO> books;
    @JsonIgnore
    public boolean isNull(){
        return (this.getName() == null) || (this.getAge() == null) || (this.getDead() == null);
    }
}
