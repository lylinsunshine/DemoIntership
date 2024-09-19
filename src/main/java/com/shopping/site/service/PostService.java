package com.shopping.site.service;

import com.shopping.site.entity.Post;
import com.shopping.site.repository.PostRepository;
import com.shopping.site.specification.PostSpec;
import com.shopping.site.util.PageResponse;
import com.shopping.site.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	public Response<String> login() {
		return null;
	}

	public Response<PageResponse<Post>> findAll(int page, int size, Map<String, Object> map) {
		String name = (String) map.get("name");

		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"id"));
		Specification<Post> spec = PostSpec.search(name);

		Page<Post> page1 = postRepository.findAll(spec, pageable);
		PageResponse<Post> pageModel = new PageResponse<Post>(page1.getContent(), page, page1.getTotalPages());
		return new Response<PageResponse<Post>>(pageModel, 200, "All posts");
	}

	public Response<Post> addPost(Post post) {
		postRepository.save(post);
		return new Response<>(null, 200, "All posts");
	}

	public Response<Post> getOnePost(int postId) {
		Post p = postRepository.findById(postId).get();
		return new Response<>(p, 200, "All posts");
	}
}
