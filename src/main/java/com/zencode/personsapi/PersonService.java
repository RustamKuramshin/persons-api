package com.zencode.personsapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import java.util.List;

public interface PersonService {
    List<PersonDto> getAllPersons();
    PersonDto getPersonById(Long id);
    List<PersonDto> getPersonsByName(String name);
    PersonDto createPerson(PersonDto personDto);
    PersonDto updatePersonById(Long id, PersonDto personDto);
    PersonDto patchPersonById(Long id, JsonPatch jsonPatchPerson) throws JsonPatchException, JsonProcessingException;
    void deletePersonById(Long id);
}
