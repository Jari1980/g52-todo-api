package se.lexicon.g52todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.g52todoapi.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    // find user by email ❌

    // is there any existing user with this email?
    boolean existsByEmail(String email);


    // update ExpiredStatus By email
    @Modifying
    @Query("UPDATE User u set u.expired = :status where u.email = :em")
    void updateExpiredByEmail(@Param("em") String email, @Param("status") boolean status);

    // Update password by Email
    @Modifying
    @Query("UPDATE User u set u.password = :password where u.email = :email")
    void updatePasswordByEmail(String email, String password);
}
