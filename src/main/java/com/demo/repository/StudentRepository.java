package com.demo.repository;

import com.demo.model.Student;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends CassandraRepository<Student, UUID> {

    List<Student> findByFirstName(String firstName);
}
