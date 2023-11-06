package com.shopping.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
	
	//List<String> save(MultipartFile[] file);
	String save(MultipartFile file) throws IOException;
}
