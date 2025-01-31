package se.lexicon.g52todoapi.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.g52todoapi.domain.dto.PersonDTOForm;
import se.lexicon.g52todoapi.domain.dto.PersonDTOView;
import se.lexicon.g52todoapi.domain.dto.TaskDTOView;
import se.lexicon.g52todoapi.domain.entity.Person;

import java.util.List;

@Transactional
@Service
public interface PersonService {
    // Todo: Make sure to use the matching PersonDTOForm & PersonDTOView in your parameters and Return types.

    // Todo: create
    PersonDTOView create(PersonDTOForm form);

    // Todo: findById
    PersonDTOView findById(Long id);

    // Todo: findAll
    List<PersonDTOView> findAll();

    // Todo: update
    PersonDTOView update(Long id, PersonDTOForm form);

    // Todo: delete
    void delete(Long id);
}
