package com.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@ToString
public class StudentDto {

    private UUID id;

    @NotBlank(message = "First Name Is Mandatory")
    @JsonProperty("fName")
    private String firstName;

    @NotBlank(message = "Last Name Is Mandatory")
    @JsonProperty("lName")
    private String lastName;

    private Integer age;

    @Valid
    private StudentSubjectDto studentSubject;
}
