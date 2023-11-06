package com.shopping.site.repository;

import java.util.List;

import com.shopping.site.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("wishlistRepository")
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
	
	@Query("From Wishlist w where w.userEntity.username=?1 order by w.id desc")
	List<Wishlist> getWishLisByUsername(String username);
}
