package com.maziogra.book_shop_api.mappers.mapperImpl;

import com.maziogra.book_shop_api.domain.DTO.GenreDTO;
import com.maziogra.book_shop_api.domain.entities.GenreEntity;
import com.maziogra.book_shop_api.mappers.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenreMapper implements Mapper<GenreEntity, GenreDTO> {

    private final ModelMapper modelMapper;

    @Override
    public GenreDTO mapTo(GenreEntity genreEntity) {
        return modelMapper.map(genreEntity, GenreDTO.class);
    }

    @Override
    public GenreEntity mapFrom(GenreDTO genreDTO) {
        return modelMapper.map(genreDTO, GenreEntity.class);
    }
}
