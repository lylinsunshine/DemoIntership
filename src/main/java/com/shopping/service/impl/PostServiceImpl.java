package com.shopping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dao.IPostDAO;
import com.shopping.service.IPostService;

@Service
public class PostServiceImpl implements IPostService {
	
	@Autowired
	private IPostDAO postDAO;
}
