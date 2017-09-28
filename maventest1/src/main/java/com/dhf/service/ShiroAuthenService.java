package com.dhf.service;

import java.util.List;

import com.dhf.model.ShiroAuthen;

public interface ShiroAuthenService {
	int deleteByPrimaryKey(Long id);

    int insert(ShiroAuthen record);

    int insertSelective(ShiroAuthen record);

    ShiroAuthen selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShiroAuthen record);

    int updateByPrimaryKey(ShiroAuthen record);
    
    List<ShiroAuthen> selectAll();
    
    ShiroAuthen selectIDAnd(String key,String value);
    
    ShiroAuthen selectIDOr(String key,String value);
    
    int deleteBykey(String key);
}
