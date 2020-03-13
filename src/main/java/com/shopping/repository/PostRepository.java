package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.entity.Post;

@Repository("postRepository")
public interface PostRepository extends JpaRepository<Post, Integer> {

}
