package org.example.services;

import org.example.dto.AnimeDto;

import java.util.List;

public interface IAnimeService {
    List<AnimeDto> getAllAnime();
    AnimeDto getById(long id);
    long save(AnimeDto animeDto);
    AnimeDto delete(long id);
    AnimeDto update(long id, AnimeDto animeDto);
}
