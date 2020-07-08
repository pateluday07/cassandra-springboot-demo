package com.demo.mapper;

import com.demo.dto.StudentSubjectDto;
import com.demo.model.StudentSubject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentSubjectMapper {

    StudentSubject toModel(StudentSubjectDto studentSubjectDto);

    StudentSubjectDto toDto(StudentSubject studentSubject);
}
