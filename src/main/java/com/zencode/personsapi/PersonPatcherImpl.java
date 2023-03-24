package com.zencode.personsapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.stereotype.Component;

@Component
public class PersonPatcherImpl implements PersonPatcher {
    @Override
    public Person patch(JsonPatch jsonPatchPerson, Person person) throws JsonProcessingException, JsonPatchException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode patched = jsonPatchPerson.apply(mapper.convertValue(person, JsonNode.class));
        return mapper.treeToValue(patched, Person.class);
    }
}
