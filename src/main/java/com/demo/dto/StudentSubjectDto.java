package com.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentSubjectDto {

    private UUID studentId;

    private UUID subjectId;

    @NotNull(message = "Please Provide Subject Id(s)")
    private Set<UUID> subjectIds;
}
