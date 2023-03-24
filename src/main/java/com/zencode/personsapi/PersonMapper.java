package com.zencode.personsapi;

public interface PersonMapper {
    Person toEntity(PersonDto personDto);
    PersonDto toDto(Person person);
}
