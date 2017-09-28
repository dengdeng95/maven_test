package com.dhf.service;

import com.dhf.model.ObSystemAccount;

public interface ObSystemAccountService {
	
	ObSystemAccount selectInvestId(int investpersonId);

	int insert(ObSystemAccount obsystemaccout);
	
	int updateByPrimaryKeySelective(ObSystemAccount record);
	
	ObSystemAccount selectByPrimaryKey(Long accountid);

}
