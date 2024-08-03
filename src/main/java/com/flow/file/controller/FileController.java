package com.flow.file.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.flow.file.dto.file.response.FileUploadResponseDto;
import com.flow.file.service.file.FileUploadService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

	private final FileUploadService fileUploadService;

	@PostMapping("/upload")
	public ResponseEntity<FileUploadResponseDto> uploadFile(@RequestParam("file") MultipartFile file) {
		FileUploadResponseDto response = fileUploadService.upload(file);
		return ResponseEntity.ok(response);
	}

}
