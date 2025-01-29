package se.lexicon.g52todoapi.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.g52todoapi.domain.entity.Role;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;


    //1.finding with an actual role
    @DisplayName("findByName with existing role should return role")
    @Test
    void testFindByName_ExistingRole_shouldReturnRole() {

        Role user = roleRepository.save(new Role("USER"));


        Optional<Role> foundRole = roleRepository.findByName("USER");


        assertEquals("USER", user.getName());
        assertTrue(foundRole.isPresent());
        assertEquals("USER", foundRole.get().getName());
    }

    //2. try to find while searching for non-existing role
    @DisplayName("findByName with non-existing role should return empty optional")
    @Test
    void testFindByName_NonExistingRole_ShouldReturnEmptyOptional() {
        Optional<Role> foundRole = roleRepository.findByName("ROLE_NON_EXISTENT");

        assertFalse(foundRole.isPresent());
    }

    //3.try to find by null
    @DisplayName("findByName with null name should return empty optional")
    @Test
    void testFindByName_NullName_ShouldReturnEmptyOptional() {
        Optional<Role> foundRole = roleRepository.findByName(null);

        assertFalse(foundRole.isPresent());
    }
}