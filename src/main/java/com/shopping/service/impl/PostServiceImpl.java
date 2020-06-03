package com.shopping.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.shopping.dao.IPostDAO;
import com.shopping.entity.Post;
import com.shopping.repository.PostRepository;
import com.shopping.service.IPostService;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;

@Service
public class PostServiceImpl implements IPostService {
	
	@Autowired
	private IPostDAO postDAO;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public ResponseModel<String> login() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseModel<PageModel<Post>> findAll(int page, int size, Map<String, Object> map) {
		// TODO Auto-generated method stub
		Page<Post> page1 = postDAO.page(page, size, map);
		PageModel<Post> pageModel = new PageModel<Post>(page1.getContent(), page, page1.getTotalPages());
		return new ResponseModel<PageModel<Post>>(pageModel, HttpStatus.OK, "All posts");
	}

	@Override
	public ResponseModel<Post> addPost(Post post) {
		// TODO Auto-generated method stub
		postDAO.insertOrUpdate(post);
		return new ResponseModel<Post>(null, HttpStatus.OK, "All posts");
	}

	@Override
	public ResponseModel<Post> getOnePost(int postId) {
		// TODO Auto-generated method stub
		Post p = postRepository.findById(postId).get();
		return new ResponseModel<Post>(p, HttpStatus.OK, "All posts");
	}
}
