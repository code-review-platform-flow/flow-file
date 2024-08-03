package com.flow.file.service.file;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.flow.file.common.exception.CustomNotFoundException;
import com.flow.file.common.property.GcpProperty;
import com.flow.file.dto.file.FileDto;
import com.flow.file.dto.file.response.FileUploadResponseDto;
import com.flow.file.service.file.persistence.FileService;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileUploadService {

	private final Storage storage = StorageOptions.getDefaultInstance().getService();
	private final GcpProperty gcpProperty;
	private final FileService fileService;

	public FileUploadResponseDto upload(MultipartFile file) {
		try {
			String fileName = generateFileName(file.getOriginalFilename());
			BlobInfo blobInfo = BlobInfo.newBuilder(gcpProperty.getBucket(), fileName).build();
			storage.create(blobInfo, file.getBytes());
			String filePath = getFilePath(fileName);
			String fileType = file.getContentType();

			FileDto fileDto = FileDto.builder().fileName(fileName).filePath(filePath).fileType(fileType).build();
			FileDto returnFile = fileService.save(fileDto);

			return FileUploadResponseDto.builder()
				.fileName(returnFile.getFileName())
				.filePath(returnFile.getFilePath())
				.fileType(returnFile.getFileType())
				.build();
		} catch (IOException e) {
			throw new CustomNotFoundException();
		}
	}

	private String getFilePath(String fileName) {
		return String.format("https://storage.googleapis.com/%s/%s", gcpProperty.getBucket(), fileName);
	}

	private String generateFileName(String originalFileName) {
		return UUID.randomUUID().toString() + "-" + originalFileName;
	}
}
