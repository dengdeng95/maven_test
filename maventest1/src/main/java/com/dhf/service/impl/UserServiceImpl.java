package com.dhf.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhf.aop.AnnotationCustom;
import com.dhf.dao.BaseDao;
import com.dhf.dao.UserMapper;
import com.dhf.model.User;
import com.dhf.service.UserService;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User,User, Long>  implements UserService {//

	@Resource
    public UserMapper userMapper;
	
	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		List<User> findAllUser = userMapper.findAllUser();
        return findAllUser;
	}
	public int save(User u){
		int a = userMapper.save(u);
		return a;
	}
	public User selectId(int id){
		return userMapper.selectId(id);
	}
	public int deleteId(int id){
		return userMapper.deleteId(id);
	}
	public int update(User u){
		return userMapper.update(u);
	}
	@AnnotationCustom("yeah")
	public List<User> pager(int startSize){
		return userMapper.pager(startSize);
	}
	
	public User select_login(String name,String password){
		return userMapper.select_login(name, password);
	}

	@Resource(name = "userMapper")
	@Override
	public void setDao(BaseDao<User, User, Long> dao) {
		super.dao = dao;
	}
}
