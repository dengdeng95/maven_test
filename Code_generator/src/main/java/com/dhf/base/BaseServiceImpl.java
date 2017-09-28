package com.dhf.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;



/**
 * 
 * BaseServiceImpl
 * @author denghf
 * 2017年7月9日 下午4:28:24
 * @param <T>
 * @param <PK>
 */
@Service("baseService")
public abstract class BaseServiceImpl<T extends Serializable,T1,PK extends Serializable> 
		implements BaseService<T, T1, PK>{
	
	protected BaseDao<T,T1, PK> dao;
	
	/**
	 * 抽象方法，需要子类提供dao
	 * 
	 * @param dao
	 */
	public abstract void setDao(BaseDao<T,T1, PK> dao);

	public int countByExample(T1 t){
		return dao.countByExample(t);
	}
	
	public int deleteByExample(T1 t){
		return dao.deleteByExample(t);
	}
	
	public int deleteByPrimaryKey(PK id){
		return dao.deleteByPrimaryKey(id);
	}
	
	public int insert(T t){
		return dao.insert(t);
	}
	
	public int insertSelective(T t){
		return dao.insertSelective(t);
	}
	
	public List<T> selectByExample(T1 t){
		return dao.selectByExample(t);
	}
	
	public T selectByPrimaryKey(PK id){
		return dao.selectByPrimaryKey(id);
	}
	
	public int updateByExampleSelective(T t1,T1 t2){
		return dao.updateByExampleSelective(t1, t2);
	}
	
	public int updateByExample(T t1,T1 t2){
		return dao.updateByExample(t1, t2);
	}
	
	public int updateByPrimaryKeySelective(T t){
		return dao.updateByPrimaryKeySelective(t);
	}
	
	public int updateByPrimaryKey(T t){
		return dao.updateByPrimaryKey(t);
	}
	
}
