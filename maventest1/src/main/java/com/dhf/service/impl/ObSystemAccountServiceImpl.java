package com.dhf.service.impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhf.dao.ObSystemAccountMapper;
import com.dhf.model.ObSystemAccount;
import com.dhf.service.ObSystemAccountService;

import javax.annotation.Resource;
import java.io.Serializable;


@Service
@Transactional
public class ObSystemAccountServiceImpl implements ObSystemAccountService{
	@Resource
	public ObSystemAccountMapper obSystemAccountMapper;
	
	@Resource
	public RedisTemplate<Serializable, Serializable> redisTemplate;
	
	
	public ObSystemAccount selectInvestId(int investpersonId){
		return obSystemAccountMapper.selectInvestId(investpersonId);
	}
	
	public int insert(ObSystemAccount obsystemaccout){
		return obSystemAccountMapper.insert(obsystemaccout);
	}
	
	public int updateByPrimaryKeySelective(ObSystemAccount record){
		return obSystemAccountMapper.updateByPrimaryKeySelective(record);
	}
	
	public ObSystemAccount selectByPrimaryKey(Long accountid){
		return obSystemAccountMapper.selectByPrimaryKey(accountid);
	}
}
