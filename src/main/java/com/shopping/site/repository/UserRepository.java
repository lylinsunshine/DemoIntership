package com.shopping.site.repository;

import com.shopping.site.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, String>{
	User findByUsername(String username);
	User findByUsernameAndPassword(String username, String password);
	
	@Query(value = "SELECT COUNT(username) FROM `user` WHERE role = 'CUSTOMER'", nativeQuery = true)
	int getTotalUserRegistration();
	
	boolean existsByUsername(String name);
}
