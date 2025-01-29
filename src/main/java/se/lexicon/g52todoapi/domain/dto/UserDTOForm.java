package se.lexicon.g52todoapi.domain.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
public class UserDTOForm {
    private String email;
    private String password;
    private Set<RoleDTOForm> roles;
}
