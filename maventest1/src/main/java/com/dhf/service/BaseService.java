package com.dhf.service;

import java.io.Serializable;
import java.util.List;

/**
 * BaseService
 * @author denghf
 * 2017年7月9日 下午4:28:33
 * @param <T>
 * @param <PK>
 */
public interface BaseService<T extends Serializable,T1,PK extends Serializable> {

	int countByExample(T1 t);

    int deleteByExample(T1 t);

    int deleteByPrimaryKey(PK id);

    int insert(T t);

    int insertSelective(T t);

    List<T> selectByExample(T1 t);

    T selectByPrimaryKey(PK id);

    int updateByExampleSelective(T t1,T1 t2);

    int updateByExample(T t1,T1 t2);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);
    
    List<T> selectAll();
}
