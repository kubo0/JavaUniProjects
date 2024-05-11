package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dto.AnimeDto;
import org.example.model.Anime;
import org.example.repositories.IRepositoriesCatalog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService implements IAnimeService {
    private final IRepositoriesCatalog repositories;

    @Override
    public List<AnimeDto> getAllAnime() {
        return repositories
                .getAnimes()
                .findAll()
                .stream()
                .map(a -> mapFromAnime(a))
                .toList();
    }

    @Override
    public AnimeDto getById(long id) {
        return mapFromAnime(repositories.getAnimes().getById(id));
    }

    @Override
    public long save(AnimeDto animeDto) {
        Anime anime = getAnimeFromDto(animeDto);
        var a = repositories.getAnimes().save(anime);
        return a.getId();
    }

    @Override
    public AnimeDto delete(long id) {
        var anime = repositories.getAnimes().getById(id);
        repositories.getAnimes().delete(anime);
        return mapFromAnime(anime);
    }

    @Override
    public AnimeDto update(long id, AnimeDto animeDto) {
        var anime = repositories.getAnimes().getById(id);
        repositories.getAnimes().save(getAnimeFromDto(animeDto, anime));
        return animeDto;
    }

    private static Anime getAnimeFromDto(AnimeDto animeDto) {
        return getAnimeFromDto(animeDto, new Anime());
    }
    private static Anime getAnimeFromDto(AnimeDto animeDto, Anime anime) {
        anime.setTitle(animeDto.getTitle());
        anime.setEpisodes(animeDto.getEpisodes());
        anime.setScore(animeDto.getScore());
        return anime;
    }
    private static AnimeDto mapFromAnime(Anime anime) {
        return new AnimeDto()
                .setTitle(anime.getTitle())
                .setEpisodes(anime.getEpisodes())
                .setScore(anime.getScore());
    }
}
