package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shopping.entity.Wishlist;

@Repository("wishlistRepository")
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
	
	@Query("From Wishlist w where w.userEntity.username=?1 order by w.id desc")
	List<Wishlist> getWishLisByUsername(String username);
}
