package com.demo.mapper;

import com.demo.dto.StudentDto;
import com.demo.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student toModel(StudentDto dto);

    StudentDto toDto(Student student);
}
