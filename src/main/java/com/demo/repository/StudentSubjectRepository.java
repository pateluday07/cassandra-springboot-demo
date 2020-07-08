package com.demo.repository;

import com.demo.model.StudentSubject;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentSubjectRepository extends CassandraRepository<StudentSubject, UUID> {

    void deleteByStudentId(UUID studentId);

    List<StudentSubject> findByStudentId(UUID studentId);

    List<StudentSubject> findBySubjectId(UUID subjectId);
}
