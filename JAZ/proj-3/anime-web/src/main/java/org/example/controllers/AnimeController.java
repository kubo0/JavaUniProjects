package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dto.AnimeDto;
import org.example.services.IAnimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/anime")
@RequiredArgsConstructor
public class AnimeController {
    private final IAnimeService animeService;

    @GetMapping
    public ResponseEntity<List<AnimeDto>> getAllAnime() {
        return ResponseEntity.ok(animeService.getAllAnime());
    }
    @PostMapping
    public void saveAnime(@RequestBody AnimeDto animeDto) {
        animeService.save(animeDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimeDto> getById(@PathVariable long id) {
        var anime = animeService.getById(id);
        if (anime == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(anime);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id) {
        var result = animeService.delete(id);
        if (result == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody AnimeDto animeDto) {
        var result = animeService.update(id, animeDto);
        if (result == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }
}
