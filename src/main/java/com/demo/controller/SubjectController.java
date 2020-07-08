package com.demo.controller;

import com.demo.dto.SubjectDto;
import com.demo.service.subject.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    public SubjectDto saveSubject(@Valid @RequestBody SubjectDto subjectDto){
        return subjectService.save(subjectDto);
    }

    @PutMapping
    public SubjectDto updateSubject(@Valid @RequestBody SubjectDto subjectDto){
        return subjectService.update(subjectDto);
    }

    @GetMapping("/id/{id}")
    public SubjectDto getSubjectById(@PathVariable UUID id){
        return subjectService.getById(id);
    }

    @GetMapping("/name/{name}")
    public SubjectDto getSubjectByName(@PathVariable String name){
        return subjectService.getByName(name);
    }

    @GetMapping
    public List<SubjectDto> getAllSubject(){
        return subjectService.getAll();
    }

    @GetMapping("/student-id/{studentId}")
    public List<SubjectDto> getSubjectsByStudentId(@PathVariable UUID studentId){
        return subjectService.getByStudentId(studentId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable UUID id){
        subjectService.deleteById(id);
        return ResponseEntity.ok("Subject Deleted Successfully");
    }

}