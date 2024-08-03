package com.flow.file.dto.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {
	private Long fileId;
	private String fileName;
	private String filePath;
	private String fileType;
}
