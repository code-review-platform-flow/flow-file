package com.flow.file.service.file.persistence;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flow.file.dto.file.FileDto;
import com.flow.file.entity.FileEntity;
import com.flow.file.mapper.FileMapper;
import com.flow.file.repository.FileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {

	private final FileRepository fileRepository;
	private final FileMapper fileMapper;

	@Transactional
	public FileDto save(FileDto fileDto) {
		FileEntity file = fileMapper.toEntity(fileDto);
		fileRepository.save(file);
		return fileMapper.toDto(file);
	}

}
