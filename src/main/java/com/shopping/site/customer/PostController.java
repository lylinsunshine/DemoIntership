package com.shopping.site.customer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.entity.Post;
import com.shopping.service.IPostService;
import com.shopping.util.PageModel;
import com.shopping.util.ResponseModel;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
	
	@Autowired
	private IPostService postService;

	@GetMapping
	public ResponseModel<PageModel<Post>> getProductPage(@RequestParam int page, @RequestParam int size,
			@RequestParam(required = false) String name) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);

		return postService.findAll(page, size, map);
	}
	
	@PostMapping
	public ResponseModel<Post> addPost(@RequestBody Post post) {
		post.setDateCreated(new Date());
		return postService.addPost(post);
	}
	
	@GetMapping("/{postId}")
	public ResponseModel<Post> getOnePost(@PathVariable int postId) {
		return postService.getOnePost(postId);
	}
}
