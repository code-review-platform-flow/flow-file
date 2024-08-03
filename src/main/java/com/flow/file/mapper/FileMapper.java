package com.flow.file.mapper;

import org.mapstruct.Mapper;

import com.flow.file.common.mapper.GenericMapper;
import com.flow.file.dto.file.FileDto;
import com.flow.file.entity.FileEntity;

@Mapper(componentModel = "spring")
public interface FileMapper extends GenericMapper<FileDto, FileEntity> {
}
