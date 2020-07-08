package com.demo.service.studentsubject;

import com.demo.dto.StudentSubjectDto;
import com.demo.model.StudentSubject;
import com.demo.repository.StudentSubjectRepository;
import com.demo.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class StudentSubjectServiceImpl implements StudentSubjectService {

    private final StudentSubjectRepository studentSubjectRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public StudentSubjectDto save(StudentSubjectDto studentSubjectDto) {
        log.info("Save StudentSubject {}", studentSubjectDto);
        Set<UUID> subjectIds = new HashSet<>();
        studentSubjectDto
                .getSubjectIds()
                .forEach(subjectId -> {
                    if (subjectRepository.existsById(subjectId)) {
                        StudentSubject studentSubject = new StudentSubject(studentSubjectDto.getStudentId(), subjectId);
                        subjectIds.add(studentSubjectRepository.save(studentSubject).getSubjectId());
                    }
                });
        studentSubjectDto.setStudentId(null);
        studentSubjectDto.setSubjectIds(subjectIds);
        return studentSubjectDto;
    }

    @Override
    public StudentSubjectDto update(StudentSubjectDto studentSubjectDto) {
        log.info("Update StudentSubject {}", studentSubjectDto);
        studentSubjectRepository.deleteByStudentId(studentSubjectDto.getStudentId());
        return save(studentSubjectDto);
    }

}
