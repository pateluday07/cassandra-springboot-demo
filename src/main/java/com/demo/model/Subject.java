package com.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.Indexed;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table
@Getter
@Setter
@ToString
public class Subject {

    @PrimaryKey
    private UUID id;

    @Indexed
    private String name;

    private String description;
}
