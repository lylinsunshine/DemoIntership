package com.shopping.dao;

import com.shopping.entity.User;

public interface IUserDAO{
	User findByUsername(String username);
	User findByUsernameAndPassword(String username, String password);
}
