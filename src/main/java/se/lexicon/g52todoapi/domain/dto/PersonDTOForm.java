package se.lexicon.g52todoapi.domain.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class PersonDTOForm {
    private Long id;
    private String name;
}