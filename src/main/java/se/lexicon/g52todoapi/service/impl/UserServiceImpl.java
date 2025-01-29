package se.lexicon.g52todoapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.g52todoapi.converter.RoleConverterImpl;
import se.lexicon.g52todoapi.domain.dto.RoleDTOView;
import se.lexicon.g52todoapi.domain.dto.UserDTOForm;
import se.lexicon.g52todoapi.domain.dto.UserDTOView;
import se.lexicon.g52todoapi.domain.entity.Role;
import se.lexicon.g52todoapi.domain.entity.User;
import se.lexicon.g52todoapi.exception.DataDuplicationException;
import se.lexicon.g52todoapi.exception.DataNotFoundException;
import se.lexicon.g52todoapi.repository.RoleRepository;
import se.lexicon.g52todoapi.repository.UserRepository;
import se.lexicon.g52todoapi.service.UserService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    RoleConverterImpl roleConverterImpl;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, RoleConverterImpl roleConverterImpl) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.roleConverterImpl = roleConverterImpl;
    }

    @Override
    public UserDTOView register(UserDTOForm userDTOForm) {

        //1. Check Parameter
        if (userDTOForm == null) throw new IllegalArgumentException("User from cannot be null!");
        //2. Check if email exists in the DB

        boolean doesEmailExist = userRepository.existsByEmail(userDTOForm.getEmail()); // TODO: Validate that there is an email address and not null
        if (doesEmailExist) throw new DataDuplicationException("Email already Exists");

        //3. Validate Roles for user by calling repo, and retrieve roles
        Set<Role> roles = userDTOForm.getRoles()
                .stream()
                .map((roleDto) ->
                        roleRepository.findById(roleDto.getId())
                                .orElseThrow(() -> new DataNotFoundException("Role is not Valid"))
                )
                .collect(Collectors.toSet());

        //4. Convert UserDTOForm to Entity
        //5. Todo: Hashing Password "Encrypt" with BCryptPasswordEncoder (EXTRA)
        User user = User.builder()
                .email(userDTOForm.getEmail())
                .password(userDTOForm.getPassword())
                .roles(roles) // With converted roles 😎
                .build();

        //6. Save User to Database
        User saved = userRepository.save(user); // Entity
        //7. Convert the saved User to a UserDtoView

        Set<RoleDTOView> roleDTOViews =
                saved.getRoles().stream()
                        .map(
                                role -> RoleDTOView.builder()
                                        .id(role.getId())
                                        .name(role.getName())
                                        .build()
                        )
                        .collect(Collectors.toSet());

        UserDTOView userDTOView = UserDTOView.builder() // DTO VIEW
                .email(user.getEmail())
                .roles(roleDTOViews)
                .build();

        //8. Return the View
        return userDTOView;
    }

    @Override
    public boolean authorizeUser(String email, String password) {
        return false; // Todo: Implement methods (EXTRA)
    }

    @Override
    public UserDTOView getByEmail(String email) {

        // 1. Find user by email or throw exception
        User user = userRepository.findById(email).orElseThrow(() -> new DataNotFoundException("Email does not exist"));

        //Convert user to DTO Object.
        //2a. Convert Role that user has to DTOs
        Set<RoleDTOView> roleDTOViews =
                user.getRoles().stream()
                        .map(
                                role -> RoleDTOView.builder()
                                        .id(role.getId())
                                        .name(role.getName())
                                        .build()
                        )
                        .collect(Collectors.toSet());

        //2b. Convert User to DTO with roleDTOs
        //3. return value
        return UserDTOView.builder()
                .email(user.getEmail())
                .roles(roleDTOViews)
                .build();
    }

    @Override
    public void disableByEmail(String email) { // TODO: validate input?
        //1. Does the email exist in DB?
        if (!userRepository.existsByEmail(email)) throw new DataNotFoundException("Email does not exist");

        //2. Update status for email
        userRepository.updateExpiredByEmail(email, true);
    }

    @Override
    public void enableByEmail(String email) {// TODO: validate input?
        //1. Does the email exist in DB?
        if (!userRepository.existsByEmail(email)) throw new DataNotFoundException("Email does not exist");

        //2. Update status for email
        userRepository.updateExpiredByEmail(email, false);
    }
}
