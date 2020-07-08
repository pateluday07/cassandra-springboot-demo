package com.demo.service.subject;

import com.demo.dto.SubjectDto;
import com.demo.mapper.SubjectMapper;
import com.demo.model.Subject;
import com.demo.repository.StudentSubjectRepository;
import com.demo.repository.SubjectRepository;
import com.fasterxml.uuid.Generators;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.demo.constant.ErrorMessage.SUBJECT_NOT_FOUND_MSG;

@Service
@Log4j2
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;
    private final StudentSubjectRepository studentSubjectRepository;

    @Override
    public SubjectDto save(SubjectDto subjectDto) {
        log.info("Save Subject {} ", subjectDto);
        subjectDto.setId(Generators.timeBasedGenerator().generate());
        return subjectMapper
                .toDto(subjectRepository
                        .save(subjectMapper.toModel(subjectDto)));
    }

    @Override
    public SubjectDto update(SubjectDto subjectDto) {
        log.info("Update Subject {} ", subjectDto);
        if (subjectDto.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please Provide Id To Update Subject");
        }
        checkSubjectAvailableOrNot(subjectDto.getId());
        if (Boolean.TRUE.equals(subjectRepository.existsByName(subjectDto.getName()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Subject Name Already Exists, Please Provide Unique Name");
        }
        return subjectMapper
                .toDto(subjectRepository
                        .save(subjectMapper.toModel(subjectDto)));
    }

    @Override
    public SubjectDto getById(UUID id) {
        log.info("Get Subject by Name: {}", id);
        Subject subject = subjectRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, SUBJECT_NOT_FOUND_MSG.concat(id.toString())));
        return subjectMapper
                .toDto(subject);
    }

    @Override
    public SubjectDto getByName(String name) {
        Subject subject = subjectRepository
                .findByName(name)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, SUBJECT_NOT_FOUND_MSG.concat(name)));
        return subjectMapper
                .toDto(subject);
    }

    @Override
    public List<SubjectDto> getAll() {
        log.info("Get All Subjects");
        return subjectRepository
                .findAll()
                .stream()
                .map(subjectMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        log.info("Delete Subject by Name: {}", id);
        checkSubjectAvailableOrNot(id);
        subjectRepository.deleteById(id);
        studentSubjectRepository
                .findBySubjectId(id)
                .forEach(studentSubjectRepository::delete);
    }

    @Override
    public List<SubjectDto> getByStudentId(UUID studentId) {
        log.info("Get Subjects by Student Id: {}", studentId);
        List<SubjectDto> subjectDtos = new ArrayList<>();
        studentSubjectRepository
                .findByStudentId(studentId)
                .forEach(studentSubject ->
                        subjectRepository
                                .findById(studentSubject.getSubjectId())
                                .ifPresent(subject -> subjectDtos.add(subjectMapper.toDto(subject))));
        return subjectDtos;
    }

    private void checkSubjectAvailableOrNot(UUID id) {
        if (!subjectRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, SUBJECT_NOT_FOUND_MSG.concat(id.toString()));
        }
    }

}
