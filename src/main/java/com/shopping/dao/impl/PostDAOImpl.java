package com.shopping.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.IPostDAO;
import com.shopping.entity.Post;
import com.shopping.repository.PostRepository;
import com.shopping.specification.PostSpec;

@Repository("postDAO")
@Transactional
public class PostDAOImpl implements IPostDAO {
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void insertOrUpdate(Post entity) {
		postRepository.save(entity);
	}

	@Override
	public void delete(Post entity) {
		postRepository.delete(entity);
	}


	@Override
	public Page<Post> page(int pageNumber, int pageSize, Map<String, Object> map) {
		String name = (String) map.get("name");

		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC,"id"));
		Specification<Post> spec = PostSpec.search(name);
		return postRepository.findAll(spec, pageable);
	}

}
