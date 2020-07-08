package com.demo.service.student;

import com.demo.dto.StudentDto;
import com.demo.dto.StudentSubjectDto;
import com.demo.mapper.StudentMapper;
import com.demo.model.Student;
import com.demo.repository.StudentRepository;
import com.demo.repository.StudentSubjectRepository;
import com.demo.service.studentsubject.StudentSubjectService;
import com.fasterxml.uuid.Generators;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.demo.constant.ErrorMessage.STUDENT_NOT_FOUND_MSG;

@Service
@RequiredArgsConstructor
@Log4j2
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final StudentSubjectService studentSubjectService;
    private final StudentSubjectRepository studentSubjectRepository;

    @Override
    public StudentDto save(StudentDto studentDto) {
        log.info("Save Student {} ", studentDto);
        studentDto.setId(Generators.timeBasedGenerator().generate());
        StudentDto savedStudentDto = studentMapper
                .toDto(studentRepository
                        .save(studentMapper.toModel(studentDto)));
        if (studentDto.getStudentSubject() != null) {
            StudentSubjectDto studentSubjectDto = studentDto.getStudentSubject();
            studentSubjectDto.setStudentId(savedStudentDto.getId());
            savedStudentDto.setStudentSubject(studentSubjectService.save(studentSubjectDto));
        }
        return savedStudentDto;
    }

    @Override
    public StudentDto update(StudentDto studentDto) {
        log.info("Update Student {} ", studentDto);
        if (studentDto.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please Provide Id To Update Student Data");
        }
        checkStudentAvailableOrNot(studentDto.getId());
        StudentDto savedStudentDto = studentMapper
                .toDto(studentRepository
                        .save(studentMapper.toModel(studentDto)));
        if (studentDto.getStudentSubject() != null) {
            StudentSubjectDto studentSubjectDto = studentDto.getStudentSubject();
            studentSubjectDto.setStudentId(savedStudentDto.getId());
            savedStudentDto.setStudentSubject(studentSubjectService.update(studentSubjectDto));
        }
        return savedStudentDto;
    }

    @Override
    public StudentDto getById(UUID id) {
        log.info("Get Student By Id: {} ", id);
        Student student = studentRepository.findById(id)
                .orElseThrow((() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        STUDENT_NOT_FOUND_MSG.concat(id.toString()))));
        return studentMapper.toDto(student);
    }

    @Override
    public List<StudentDto> getAll() {
        log.info("Get All Students");
        return studentRepository
                .findAll()
                .stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        log.info("Delete Student By Id: {} ", id);
        checkStudentAvailableOrNot(id);
        studentRepository.deleteById(id);
        studentSubjectRepository.deleteByStudentId(id);
    }

    @Override
    public List<StudentDto> findByFirstName(String firstName) {
        log.info("Get Student By First Name {}", firstName);
        return studentRepository
                .findByFirstName(firstName)
                .stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Set<StudentDto> findBySubjectId(UUID subjectId) {
        log.info("Get Students By Subject Id: {}", subjectId);
        Set<StudentDto> studentDtos = new HashSet<>();
        studentSubjectRepository
                .findBySubjectId(subjectId)
                .forEach(studentSubject ->
                        studentRepository
                                .findById(studentSubject.getStudentId())
                                .ifPresent(student -> studentDtos.add(studentMapper.toDto(student))));
        return studentDtos;
    }

    private void checkStudentAvailableOrNot(UUID id) {
        if (!studentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    STUDENT_NOT_FOUND_MSG.concat(id.toString()));
        }
    }
}
