package com.zencode.personsapi;

import org.springframework.stereotype.Component;

@Component
public class PersonMapperImpl implements PersonMapper {

    @Override
    public Person toEntity(PersonDto personDto) {
        return Person.builder()
                .name(personDto.getName())
                .age(personDto.getAge())
                .build();
    }

    @Override
    public PersonDto toDto(Person person) {
        return PersonDto.builder()
                .id(person.getId())
                .name(person.getName())
                .age(person.getAge())
                .build();
    }
}
