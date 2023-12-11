package com.maziogra.book_api.mappers.mapperImpl;

import com.maziogra.book_api.domain.DTO.AuthorDTO;
import com.maziogra.book_api.domain.entities.AuthorEntity;
import com.maziogra.book_api.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper implements Mapper<AuthorEntity, AuthorDTO> {
    private final ModelMapper modelMapper;

    public AuthorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorDTO mapTo(AuthorEntity authorEntity) {
        return modelMapper.map(authorEntity, AuthorDTO.class);
    }

    @Override
    public AuthorEntity mapFrom(AuthorDTO authorDTO) {
        return modelMapper.map(authorDTO, AuthorEntity.class);
    }
}
