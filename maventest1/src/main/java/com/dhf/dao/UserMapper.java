package com.dhf.dao;

import java.util.List;

import com.dhf.dao.BaseDao;
import com.dhf.model.User;

public interface UserMapper extends BaseDao<User,User, Long>{//
	List<User> findAllUser();
	
	int save(User u);
	
	User selectId(int id);
	
	int deleteId(int id);
	
	int update(User u);
	
	List<User> pager(int startSize);
	
    User select_login(String username,String password);
}
