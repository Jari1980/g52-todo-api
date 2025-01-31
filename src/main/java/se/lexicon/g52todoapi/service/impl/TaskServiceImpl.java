package se.lexicon.g52todoapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import se.lexicon.g52todoapi.domain.dto.TaskDTOForm;
import se.lexicon.g52todoapi.domain.dto.TaskDTOView;
import se.lexicon.g52todoapi.repository.PersonRepository;
import se.lexicon.g52todoapi.repository.UserRepository;
import se.lexicon.g52todoapi.service.TaskService;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private PersonRepository personRepository;
    private UserRepository userRepository;

    @Autowired
    public TaskServiceImpl(PersonRepository personRepository, UserRepository userRepository) {
        this.personRepository = personRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TaskDTOView create(TaskDTOForm form) {
        return null;
    }

    @Override
    public TaskDTOView findById(Long id) {
        return null;
    }

    @Override
    public TaskDTOView update(Long id, TaskDTOForm form) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<TaskDTOView> findByPersonId(Long personId) {
        return List.of();
    }

    @Override
    public List<TaskDTOView> findTasksBetweenStartAndEndDate(LocalDate startDate, LocalDate endDate) {
        return List.of();
    }

    @Override
    public List<TaskDTOView> findAllUnassignedTasks() {
        return List.of();
    }

    @Override
    public List<TaskDTOView> findAllUnfinishedAndOverdueTasks() {
        return List.of();
    }
    // Todo: Implement class after interface methods are created.

}
