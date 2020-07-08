package com.demo.mapper;

import com.demo.dto.SubjectDto;
import com.demo.model.Subject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    Subject toModel(SubjectDto subjectDto);

    SubjectDto toDto(Subject subject);
}
