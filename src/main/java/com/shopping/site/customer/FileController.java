package com.shopping.site.customer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shopping.service.IFileService;
import com.shopping.util.ResponseModel;

@CrossOrigin
@RestController
@RequestMapping("/files")
public class FileController {

	@Autowired
	IFileService fileSerivce;

	@PostMapping
	public ResponseModel<String> addImage(@RequestParam(required = false) MultipartFile file) throws IOException {

		String fileName = null;
		if (file != null)
			fileName = fileSerivce.save(file);
		return new ResponseModel<String>(fileName, HttpStatus.OK, "Upload Success");
	}
}
