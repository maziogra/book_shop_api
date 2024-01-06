package com.maziogra.book_shop_api.domain.DTO;

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
}
