package com.demo.service.subject;

import com.demo.dto.SubjectDto;

import java.util.List;
import java.util.UUID;

public interface SubjectService {

    SubjectDto save(SubjectDto subjectDto);

    SubjectDto update(SubjectDto subjectDto);

    SubjectDto getById(UUID id);

    SubjectDto getByName(String name);

    List<SubjectDto> getAll();

    void deleteById(UUID id);

    List<SubjectDto> getByStudentId(UUID studentId);
}
