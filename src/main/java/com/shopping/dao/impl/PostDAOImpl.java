package com.shopping.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.IPostDAO;
import com.shopping.entity.Post;
import com.shopping.repository.PostRepository;

@Repository("postDAO")
@Transactional
public class PostDAOImpl implements IPostDAO {
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void insertOrUpdate(Post entity) {
		// TODO Auto-generated method stub
		postRepository.save(entity);
	}

	@Override
	public void delete(Post entity) {
		// TODO Auto-generated method stub
		postRepository.delete(entity);
	}

	@Override
	public Page<Post> page(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
