package com.dhf.dao;

import com.dhf.model.ObSystemAccount;

public interface ObSystemAccountMapper {
    int deleteByPrimaryKey(Long accountid);

    int insert(ObSystemAccount record);

    int insertSelective(ObSystemAccount record);

    ObSystemAccount selectByPrimaryKey(Long accountid);

    int updateByPrimaryKeySelective(ObSystemAccount record);

    int updateByPrimaryKey(ObSystemAccount record);
    
    ObSystemAccount selectInvestId(int investpersonId);
}