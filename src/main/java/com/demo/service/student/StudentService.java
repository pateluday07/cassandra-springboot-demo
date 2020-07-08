package com.demo.service.student;

import com.demo.dto.StudentDto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface StudentService {

    StudentDto save(StudentDto studentDto);

    StudentDto update(StudentDto student);

    StudentDto getById(UUID id);

    List<StudentDto> getAll();

    void deleteById(UUID id);

    List<StudentDto> findByFirstName(String firstName);

    Set<StudentDto> findBySubjectId(UUID subjectId);
}
