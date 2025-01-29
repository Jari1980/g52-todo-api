package se.lexicon.g52todoapi.converter;

import se.lexicon.g52todoapi.domain.dto.RoleDTOForm;
import se.lexicon.g52todoapi.domain.dto.RoleDTOView;
import se.lexicon.g52todoapi.domain.entity.Role;

public interface RoleConverter {

    RoleDTOView toRoleDto(Role entity);

    Role toEntity(RoleDTOForm dto);


}
