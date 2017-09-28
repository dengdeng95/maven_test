package com.dhf.service;

import java.util.List;

import com.dhf.model.User;

public interface UserService extends BaseService<User, User, Long>{//
	List<User> findAllUser();
	
	int save(User u);
	
	User selectId(int id);
	
	int deleteId(int id);
	
	int update(User u);
	
	List<User> pager(int startSize);
	
	User select_login(String name,String password);
}
