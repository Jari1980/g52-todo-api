package se.lexicon.g52todoapi.domain.dto;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTOView {
    private String email;
    private Set<RoleDTOView> roles;
}
