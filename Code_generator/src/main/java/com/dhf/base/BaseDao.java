package com.dhf.base;

import java.io.Serializable;
import java.util.List;

/**
 * 详见博客  http://blog.csdn.net/yf275908654/article/details/50171607
 * BaseDao
 * @author denghf
 * 2017年7月9日 下午4:27:01
 * @param <T>
 * @param <PK>
 */
public interface BaseDao<T extends Serializable,T1,PK extends Serializable>{

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
}
