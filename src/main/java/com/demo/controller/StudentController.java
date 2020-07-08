package com.demo.controller;

import com.demo.dto.StudentDto;
import com.demo.service.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public StudentDto saveStudent(@Valid @RequestBody StudentDto studentDto) {
        return studentService.save(studentDto);
    }

    @PutMapping
    public StudentDto updateStudent(@Valid @RequestBody StudentDto studentDto) {
        return studentService.update(studentDto);
    }

    @GetMapping("/id/{id}")
    public StudentDto getStudentById(@PathVariable UUID id) {
        return studentService.getById(id);
    }

    @GetMapping
    public List<StudentDto> getAllStudents() {
        return studentService.getAll();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable UUID id) {
        studentService.deleteById(id);
        return ResponseEntity.ok("Student Deleted Successfully");
    }

    @GetMapping("/firstName/{firstName}")
    public List<StudentDto> getStudentByFirstName(@PathVariable String firstName) {
        return studentService.findByFirstName(firstName);
    }

    @GetMapping("/subject-id/{subjectId}")
    public Set<StudentDto> getStudentBySubjectId(@PathVariable UUID subjectId) {
        return studentService.findBySubjectId(subjectId);
    }

}
