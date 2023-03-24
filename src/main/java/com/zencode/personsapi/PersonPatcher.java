package com.zencode.personsapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

public interface PersonPatcher {
    Person patch(JsonPatch jsonPatchPerson , Person person) throws JsonProcessingException, JsonPatchException;
}
