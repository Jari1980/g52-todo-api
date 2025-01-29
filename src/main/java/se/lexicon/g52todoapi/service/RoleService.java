package se.lexicon.g52todoapi.service;

import se.lexicon.g52todoapi.domain.dto.RoleDTOView;

import java.util.List;

public interface RoleService {

    List<RoleDTOView> getAll();
}
