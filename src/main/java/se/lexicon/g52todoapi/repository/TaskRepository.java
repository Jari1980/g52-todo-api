package se.lexicon.g52todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.g52todoapi.domain.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // Todo: select tasks contain title
    // Todo: select tasks by person's id
    // Todo: select tasks by status
    // Todo: select tasks by date between start and end
    // Todo: select all unassigned tasks
    // Todo: select all unfinished tasks
    // Todo: select all unfinished and overdue tasks

}
