package org.example.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Getter
@Setter
public class AnimeDto {
    private String title;
    private int episodes;
    private int score;
}
