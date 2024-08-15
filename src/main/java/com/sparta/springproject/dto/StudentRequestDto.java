package com.sparta.springproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudentRequestDto {

    Long student_id;
    String to_do;
    String charge_name;
    String password;
    String write_day;
    String modified_day;

}
