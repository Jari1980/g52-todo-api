package se.lexicon.g52todoapi.service;

import org.springframework.stereotype.Service;
import se.lexicon.g52todoapi.domain.dto.TaskDTOForm;
import se.lexicon.g52todoapi.domain.dto.TaskDTOView;

import java.time.LocalDate;
import java.util.List;

@Service
public interface TaskService {
    //Todo: Make sure to use the matching TaskDTOForm & TaskDTOView in your parameters and Return types.
    //Todo: create
    TaskDTOView create(TaskDTOForm form);

    //Todo: findById
    TaskDTOView findById(Long id);

    //Todo: update
    TaskDTOView update(Long id, TaskDTOForm form);

    //Todo: delete
    void delete(Long id);

    //Todo: findTasksByPersonId
    List<TaskDTOView> findByPersonId(Long personId);

    //Todo: findTasksBetweenStartAndEndDate
    List<TaskDTOView> findTasksBetweenStartAndEndDate(LocalDate startDate, LocalDate endDate);

    //Todo: findAllUnassignedTasks
    List<TaskDTOView> findAllUnassignedTasks();

    //Todo: findAllUnfinishedAndOverdueTasks
    List<TaskDTOView> findAllUnfinishedAndOverdueTasks();

}
