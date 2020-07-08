package com.demo.model;

import lombok.*;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Indexed;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StudentSubject {

    @PrimaryKeyColumn(name = "studentId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID studentId;

    @PrimaryKeyColumn(name = "subjectId", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    @Indexed
    private UUID subjectId;
}
