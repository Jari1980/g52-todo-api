package se.lexicon.g52todoapi.repository;


import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.g52todoapi.domain.dto.RoleDTOForm;
import se.lexicon.g52todoapi.domain.dto.UserDTOForm;
import se.lexicon.g52todoapi.domain.entity.User;
import se.lexicon.g52todoapi.service.UserService;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase
class UserRepositoryTest {


    //Todo: Write tests for implemented methods


    @Autowired
    private UserRepository userRepository;
    //@Autowired
    //private UserService userService;
    @Autowired
    private EntityManager entityManager;


    //Testing userRepository.existsByEmail

    //Finding actual user with existing email
    @Test
    void testExistsByEmail_ExistingEmail_ShouldReturnTrue() {

        User user = new User("test@test.se", "test");
        userRepository.save(user);
        boolean exists = userRepository.existsByEmail("test@test.se");

        assertTrue(exists);
    }

    //Finding non existing user with non existing email
    @Test
    void testExistsByEmail_NonExistingEmail_ShouldReturnFalse() {
        assertFalse(userRepository.existsByEmail("nono@nono.com"));
    }

    //Finding with null email
    @Test
    void testExistsByEmail_NullEmail_ShouldReturnFalse() {
        assertFalse(userRepository.existsByEmail(null));
    }


    //Testing userRepository.updateExpiredByEmail

    //Testing expired set to true
    @Test
    @Transactional
    void testUpdateExpiredByEmail_ExistingEmail_ShouldReturnTrue() {
        User user = new User("test@test.se", "test"); //Expired default false
        userRepository.save(user);
        //userRepository.flush();
        userRepository.updateExpiredByEmail("test@test.se", true);
        //entityManager.flush();
        entityManager.clear();
        //userRepository.flush();

        //User updatedUser = userRepository.findByEmail("test@test.se");
        User updatedUser = userRepository.findByEmail("test@test.se");
        System.out.println(updatedUser);
        assertEquals(true, userRepository.findByEmail("test@test.se").isExpired());
    }
    //Testing expired set to false
    @Test
    @Transactional
    void testUpdateExpiredByEmail_ExistingEmail_ShouldReturnFalse() {
        User user = new User("test@test.se", "test"); //Expired default false
        userRepository.save(user);
        userRepository.updateExpiredByEmail("test@test.se", false);
        entityManager.clear();
        User updatedUser = userRepository.findByEmail("test@test.se");
        System.out.println(updatedUser);
        assertEquals(false, updatedUser.isExpired());
    }

    //Testing userRepository.updatePasswordByEmail

    //Testing correct updating password of existing user
    @Test
    void testUpdatePasswordByEmail_ExistingEmail_ShouldReturnTrue() {
        User user = new User("test@test.se", "test");
        userRepository.save(user);
        //entityManager.clear();
        userRepository.updatePasswordByEmail("test@test.se", "testUpdated");
        //entityManager.flush();
        entityManager.clear();
        User updatedUser = userRepository.findByEmail("test@test.se");
        //System.out.println(updatedUser.getEmail());
        assertEquals("testUpdated",updatedUser.getPassword());
    }
    //Testing correct updating password of non-existing user
    @Test
    void testUpdatePasswordByEmail_NonExistingEmail_ShouldReturnNull() {
        userRepository.updatePasswordByEmail("test@test.se", "testUpdated");
        entityManager.clear();
        assertEquals(null,userRepository.findByEmail("test@test.se"));
    }
    //Testing null password to existing user
    @Test
    void testUpdatePasswordToNullByEmail_ExistingEmail_ShouldThrowException() {
        User user = new User("test@test.se", "test");
        userRepository.save(user);
        System.out.println("----------------------------" + userRepository.findById("test@test.se").orElseThrow().getPassword());
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userRepository.updatePasswordByEmail("test@test.se", null);
        });
        //entityManager.clear();
        //System.out.println(exception);
        assertTrue(exception.getMessage().contains("JDBC exception executing SQL"));
    }

}