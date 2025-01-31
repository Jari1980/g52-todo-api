package se.lexicon.g52todoapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.g52todoapi.domain.entity.Person;
import se.lexicon.g52todoapi.domain.entity.Task;


import java.time.LocalDate;
import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Long> {



    // Todo: select tasks contain title
    List<Task> findByTitleContaining(String title);

    // Todo: select tasks by person's id
    List<Task> findByPerson_Id(Long id);

    // Todo: select tasks by status
    List<Task> findTaskByDone(boolean done);

    // Todo: select tasks by date between start and end
    List<Task> findByDeadlineBetween(LocalDate start, LocalDate end);

    // Todo: select all unassigned tasks
    List<Task> findTasksByPersonNull();

    // Todo: select all unfinished tasks
    List<Task> findTasksByDoneIsFalse();

    // Todo: select all unfinished and overdue tasks
    List<Task> findTasksByDeadlineIsBeforeAndDoneIsFalse(LocalDate deadline);






}
