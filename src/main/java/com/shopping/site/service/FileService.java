package com.shopping.site.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Transactional
public class FileService {
	
	private final Path rootLocation = Paths.get("C://Users//Blindfold Gang//Desktop//images/");

	public String save(MultipartFile file) throws IOException {
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		String fileName = UUID.randomUUID() + "." + extension;
		Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName));
		return fileName;
	}

}
