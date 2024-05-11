package org.example.repositories;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@Getter
@RequiredArgsConstructor
public class RepositoriesCatalog implements IRepositoriesCatalog {
    private final AnimeRepository animes;
    private final CharactersRepository characters;
    private final StaffRepository staff;
}
