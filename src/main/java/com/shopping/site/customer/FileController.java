package com.shopping.site.customer;

import java.io.IOException;

import com.shopping.site.service2.FileService;
import com.shopping.site.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {

	private final FileService fileService;

	@PostMapping
	public Response<String> addImage(@RequestParam(required = false) MultipartFile file) throws IOException {
		String fileName = null;
		if (file != null)
			fileName = fileService.save(file);
		return new Response<>(fileName, 0, "Upload file success");
	}
}
