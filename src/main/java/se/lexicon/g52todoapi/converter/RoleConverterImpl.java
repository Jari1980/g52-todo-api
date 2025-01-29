package se.lexicon.g52todoapi.converter;

import org.springframework.stereotype.Component;
import se.lexicon.g52todoapi.domain.dto.RoleDTOForm;
import se.lexicon.g52todoapi.domain.dto.RoleDTOView;
import se.lexicon.g52todoapi.domain.entity.Role;

@Component
public class RoleConverterImpl implements RoleConverter {
    @Override
    public RoleDTOView toRoleDto(Role entity) {
//        return  new RoleDTOView(entity.getId(),entity.getName());
        return RoleDTOView.builder().id(entity.getId()).name(entity.getName()).build();
    }

    @Override
    public Role toEntity(RoleDTOForm dto) {
//        return new Role(dto.getId(), dto.getName());
        return Role.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
