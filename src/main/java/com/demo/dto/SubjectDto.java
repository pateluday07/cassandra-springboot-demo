package com.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@ToString
public class SubjectDto {

    private UUID id;

    @NotEmpty(message = "Subject Name Is Mandatory")
    @JsonProperty("sName")
    private String name;

    @JsonProperty("sDescription")
    private String description;
}
