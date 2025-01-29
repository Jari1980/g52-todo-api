package se.lexicon.g52todoapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.g52todoapi.converter.RoleConverter;
import se.lexicon.g52todoapi.domain.dto.RoleDTOView;
import se.lexicon.g52todoapi.domain.entity.Role;
import se.lexicon.g52todoapi.repository.RoleRepository;
import se.lexicon.g52todoapi.service.RoleService;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;
    private RoleConverter roleConverter;


    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleConverter roleConverter) {
        this.roleRepository = roleRepository;
        this.roleConverter = roleConverter;
    }


    @Override
    public List<RoleDTOView> getAll() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDTOView> roleDTOViews = new ArrayList<>();
        for (Role role: roles){
            RoleDTOView dto = roleConverter.toRoleDto(role);
            roleDTOViews.add(dto);
        }

        return roleDTOViews;
    }
}
