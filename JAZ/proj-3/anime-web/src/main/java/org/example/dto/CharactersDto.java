package org.example.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Getter
@Setter
public class CharactersDto {
    private String firstName;
    private String lastName;
    private int age;
}
