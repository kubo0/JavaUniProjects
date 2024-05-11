package org.example.repositories;

public interface IRepositoriesCatalog {
    AnimeRepository getAnimes();
    CharactersRepository getCharacters();
    StaffRepository getStaff();
}
