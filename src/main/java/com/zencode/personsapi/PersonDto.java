package com.zencode.personsapi;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonDto {

    private Long id;
    private String name;
    private Long age;
}
