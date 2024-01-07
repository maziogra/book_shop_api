package com.maziogra.book_shop_api.mappers.mapperImpl;

import com.maziogra.book_shop_api.domain.DTO.BookDTO;
import com.maziogra.book_shop_api.domain.entities.BookEntity;
import com.maziogra.book_shop_api.mappers.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookMapper implements Mapper<BookEntity, BookDTO> {

    private final ModelMapper modelMapper;

    @Override
    public BookDTO mapTo(BookEntity bookEntity) {
        return modelMapper.map(bookEntity, BookDTO.class);
    }

    @Override
    public BookEntity mapFrom(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, BookEntity.class);
    }
}
