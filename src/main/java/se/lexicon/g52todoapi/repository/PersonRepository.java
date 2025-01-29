package se.lexicon.g52todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.lexicon.g52todoapi.domain.entity.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    //find all persons who have no task
    @Query("select p from Person p where SIZE(p.tasks) = 0 ")
    List<Person> findIdlePeople();

    // Todo: Define more methods if needed
}
