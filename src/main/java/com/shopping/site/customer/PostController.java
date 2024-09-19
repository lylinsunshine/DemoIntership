package com.shopping.site.customer;

import com.shopping.site.entity.Post;
import com.shopping.site.service.PostService;
import com.shopping.site.util.PageResponse;
import com.shopping.site.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

	private PostService postService;

	@GetMapping
	public Response<PageResponse<Post>> getProductPage(@RequestParam int page, @RequestParam int size,
													   @RequestParam(required = false) String name) {

		Map<String, Object> map = new HashMap<>();
		map.put("name", name);

		return postService.findAll(page, size, map);
	}
	
	@PostMapping
	public Response<Post> addPost(@RequestBody Post post) {
		post.setDateCreated(new Date());
		return postService.addPost(post);
	}
	
	@GetMapping("/{postId}")
	public Response<Post> getOnePost(@PathVariable int postId) {
		return postService.getOnePost(postId);
	}
}
