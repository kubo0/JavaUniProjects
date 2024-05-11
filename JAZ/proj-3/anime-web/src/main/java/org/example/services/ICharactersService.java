package org.example.services;

import org.example.dto.CharactersDto;

import java.util.List;

public interface ICharactersService {
    List<CharactersDto> getAllCharacters();
    CharactersDto getById(long id);
    long save(CharactersDto charactersDto);
    CharactersDto delete(long id);
    CharactersDto update(long id, CharactersDto charactersDto);
    List<CharactersDto> getByAge(int age);
    List<CharactersDto> getByAgeBetween(int ageFrom, int ageTo);
}
