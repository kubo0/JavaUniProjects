package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dto.CharactersDto;
import org.example.model.Characters;
import org.example.repositories.IRepositoriesCatalog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharactersService implements ICharactersService {
    private final IRepositoriesCatalog repositories;

    @Override
    public List<CharactersDto> getAllCharacters() {
        return repositories
                .getCharacters()
                .findAll()
                .stream()
                .map(c -> mapFromCharacter(c))
                .toList();
    }

    @Override
    public CharactersDto getById(long id) {
        return mapFromCharacter(repositories.getCharacters().getById(id));
    }

    @Override
    public long save(CharactersDto charactersDto) {
        Characters characters = getCharacterFromDto(charactersDto);
        var c = repositories.getCharacters().save(characters);
        return c.getId();
    }

    @Override
    public CharactersDto delete(long id) {
        var character = repositories.getCharacters().getById(id);
        repositories.getCharacters().delete(character);
        return mapFromCharacter(character);
    }

    @Override
    public CharactersDto update(long id, CharactersDto charactersDto) {
        var character = repositories.getCharacters().getById(id);
        repositories.getCharacters().save(getCharacterFromDto(charactersDto, character));
        return charactersDto;
    }

    @Override
    public List<CharactersDto> getByAge(int age) {
        return repositories
                .getCharacters()
                .findAllByAge(age)
                .stream()
                .map(c -> mapFromCharacter(c))
                .toList();
    }

    @Override
    public List<CharactersDto> getByAgeBetween(int ageFrom, int ageTo) {
        return repositories
                .getCharacters()
                .findAllByAgeBetween(ageFrom, ageTo)
                .stream()
                .map(c -> mapFromCharacter(c))
                .toList();
    }

    private static Characters getCharacterFromDto(CharactersDto charactersDto) {
        return getCharacterFromDto(charactersDto, new Characters());
    }
    private static Characters getCharacterFromDto(CharactersDto charactersDto, Characters characters) {
        characters.setFirstName(charactersDto.getFirstName());
        characters.setLastName(charactersDto.getLastName());
        characters.setAge(charactersDto.getAge());
        return characters;
    }
    private static CharactersDto mapFromCharacter(Characters characters) {
        return new CharactersDto()
                .setAge(characters.getAge())
                .setFirstName(characters.getFirstName())
                .setLastName(characters.getLastName());
    }
}
