package com.maziogra.book_shop_api.controllers;

import com.maziogra.book_shop_api.domain.DTO.GenreDTO;
import com.maziogra.book_shop_api.domain.entities.GenreEntity;
import com.maziogra.book_shop_api.mappers.mapperImpl.GenreMapper;
import com.maziogra.book_shop_api.services.impl.GenreServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreServiceImpl genreService;
    private final GenreMapper genreMapper;

    @PostMapping
    public ResponseEntity<GenreDTO> createGenre(@RequestBody GenreDTO genreDTO){
        if(genreDTO.isNull()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        GenreEntity genreEntity = genreService.save(genreMapper.mapFrom(genreDTO));
        return new ResponseEntity<>(genreMapper.mapTo(genreEntity), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getGenres(){
        List<GenreEntity> genreEntities = genreService.getGenres();
        return ResponseEntity.ok(genreEntities.stream().map(genreMapper::mapTo).toList());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GenreDTO> getGenreById(@PathVariable(value = "id") Long id){
        Optional<GenreEntity> genreEntity = genreService.getGenreById(id);
        return genreEntity.map(genre -> {
            GenreDTO genreDTO = genreMapper.mapTo(genre);
            return new ResponseEntity<>(genreDTO, HttpStatus.FOUND);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteGenreById(@PathVariable(value = "id") Long id){
        if(!genreService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        genreService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
