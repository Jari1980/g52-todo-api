package se.lexicon.g52todoapi.service.impl;

import jakarta.transaction.Transactional;
import jakarta.websocket.ClientEndpointConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.g52todoapi.domain.dto.*;
import se.lexicon.g52todoapi.domain.entity.Person;
import se.lexicon.g52todoapi.domain.entity.User;
import se.lexicon.g52todoapi.exception.DataDuplicationException;
import se.lexicon.g52todoapi.repository.PersonRepository;
import se.lexicon.g52todoapi.repository.RoleRepository;
import se.lexicon.g52todoapi.repository.UserRepository;
import se.lexicon.g52todoapi.service.PersonService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Transactional
@Service
public class PersonServiceImpl implements PersonService {

    private final RoleRepository roleRepository;
    private PersonRepository personRepository;
    private UserRepository userRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public PersonDTOView create(PersonDTOForm form) {
        if (form == null) throw new IllegalArgumentException("User from cannot be null!");
        User user = userRepository.findById(form.getName()).orElseThrow(() -> new IllegalArgumentException("User not found"));

        boolean doesIdExist = userRepository.existsById(form.getId().toString());
        if (doesIdExist) throw new DataDuplicationException("Id already Exists");

        Person person = Person.builder()
                .name(form.getName())
                .user(user)
                .build();

        personRepository.save(person);

         PersonDTOView result = PersonDTOView.builder()
                 .id(form.getId())
                 .name(form.getName())
                 .build();

        return result;
    }

    @Override
    public PersonDTOView findById(Long id) {
        Person person = personRepository.findById(id).orElse(null);
        if(person == null) {
            return null;
        }
        PersonDTOView result = PersonDTOView.builder()
                .id(person.getId())
                .name(person.getName())
                .build();
        return result;
    }

    @Override
    public List<PersonDTOView> findAll() {
        List<Person> persons = personRepository.findAll();
        if(persons.isEmpty()) {
            return null;
        }
        List<PersonDTOView> result = new ArrayList<>();
        for(Person person : persons) {
            result.add(PersonDTOView.builder()
                    .id(person.getId())
                    .name(person.getName())
                    .build());
        }
        return result;
    }

    @Override
    public PersonDTOView update(Long id, PersonDTOForm form) {
        if(form == null) throw new IllegalArgumentException("User from cannot be null!");
        Person person = personRepository.findById(id).orElse(null);
        if(person == null) throw new IllegalArgumentException("Person does not exist");
        personRepository.save(Person.builder()
                .id(form.getId())
                .name(form.getName())
                .build());

        return PersonDTOView.builder()
                .id(form.getId())
                .name(form.getName())
                .build();
    }

    @Override
    public void delete(Long id) {
        personRepository.deleteById(id);
    }
    // Todo: Implement class after interface methods are created.

}
