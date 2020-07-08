package com.demo.repository;

import com.demo.model.Subject;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.Optional;
import java.util.UUID;

public interface SubjectRepository extends CassandraRepository<Subject, UUID> {

    Optional<Subject> findByName(String name);

    Boolean existsByName(String name);
}
