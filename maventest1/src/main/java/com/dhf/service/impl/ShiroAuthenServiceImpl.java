package com.dhf.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhf.dao.ShiroAuthenMapper;
import com.dhf.model.ShiroAuthen;
import com.dhf.service.ShiroAuthenService;

@Service
@Transactional
public class ShiroAuthenServiceImpl implements ShiroAuthenService{
	@Resource
	private ShiroAuthenMapper shiroAuthenMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return shiroAuthenMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ShiroAuthen record) {
		return shiroAuthenMapper.insert(record);
	}

	@Override
	public int insertSelective(ShiroAuthen record) {
		return shiroAuthenMapper.insertSelective(record);
	}

	@Override
	public ShiroAuthen selectByPrimaryKey(Long id) {
		return shiroAuthenMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ShiroAuthen record) {
		return shiroAuthenMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ShiroAuthen record) {
		return shiroAuthenMapper.updateByPrimaryKey(record);
	}
	
	public List<ShiroAuthen> selectAll(){
		return shiroAuthenMapper.selectAll();
	}

	@Override
	public ShiroAuthen selectIDAnd(String key,String value) {
		return shiroAuthenMapper.selectIDAnd(key,value);
	}

	@Override
	public ShiroAuthen selectIDOr(String key,String value) {
		return shiroAuthenMapper.selectIDOr(key,value);
	}

	@Override
	public int deleteBykey(String key) {
		return shiroAuthenMapper.deleteBykey(key);
	}
}
