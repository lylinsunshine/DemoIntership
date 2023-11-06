package com.shopping.service;

import java.util.Map;

import com.shopping.entity.Post;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;

public interface IPostService {
	
	ResponseModel<String> login();

	ResponseModel<PageModel<Post>> findAll(int page, int size, Map<String, Object> map);

	ResponseModel<Post> addPost(Post post);

	ResponseModel<Post> getOnePost(int postId);
}
