package com.zencode.personsapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    private final PersonPatcher personPatcher;

    @Override
    public List<PersonDto> getAllPersons() {

        Iterable<Person> personsIterable = personRepository.findAll();
        return StreamSupport.stream(personsIterable.spliterator(), false)
                .map(personMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDto getPersonById(Long id) {

        Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Person not found"));
        return personMapper.toDto(person);
    }

    @Override
    public List<PersonDto> getPersonsByName(String name) {

        Iterable<Person> personsIterable = personRepository.findByName(name);
        return StreamSupport.stream(personsIterable.spliterator(), false)
                .map(personMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDto createPerson(PersonDto personDto) {

        Person person = personRepository.save(personMapper.toEntity(personDto));
        return personMapper.toDto(person);
    }

    @Override
    public PersonDto updatePersonById(Long id, PersonDto personDto) {

        Person savedPerson = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Person not found"));
        savedPerson.setName(personDto.getName());
        savedPerson.setAge(personDto.getAge());
        Person person = personRepository.save(savedPerson);
        return personMapper.toDto(person);
    }

    @Override
    public PersonDto patchPersonById(Long id, JsonPatch jsonPatchPerson) throws JsonPatchException, JsonProcessingException {

        Person savedPerson = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Person not found"));
        Person patchedPerson = personPatcher.patch(jsonPatchPerson, savedPerson);
        Person person = personRepository.save(patchedPerson);
        return personMapper.toDto(person);
    }

    @Override
    public void deletePersonById(Long id) {

        try {
            personRepository.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
