package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dto.CharactersDto;
import org.example.services.ICharactersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/character")
@RequiredArgsConstructor
public class CharactersController {
    private final ICharactersService characterService;

    @GetMapping
    public ResponseEntity<List<CharactersDto>> getAllCharacters() {
        return ResponseEntity.ok(characterService.getAllCharacters());
    }
    @PostMapping
    public void saveCharacter(@RequestBody CharactersDto charactersDto) {
        characterService.save(charactersDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<CharactersDto> getById(@PathVariable long id) {
        var character = characterService.getById(id);
        if (character == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(character);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id) {
        var result = characterService.delete(id);
        if (result == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody CharactersDto charactersDto) {
        var result = characterService.update(id, charactersDto);
        if  (result == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<List<CharactersDto>> getByAge(@PathVariable int age) {
        return ResponseEntity.ok(characterService.getByAge(age));
    }
    @GetMapping("/age-between/{age1},{age2}")
    public ResponseEntity<List<CharactersDto>> getByAgeBetween(@PathVariable int age1, @PathVariable int age2) {
        return ResponseEntity.ok(characterService.getByAgeBetween(age1, age2));
    }
}
