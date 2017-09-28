package com.dhf.base;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * BaseDaoImpl
 * @author denghf
 * 2017年7月9日 下午3:26:14
 * @param <T>
 * @param <PK>
 */
public abstract class BaseDaoImpl<T extends Serializable,T1,PK extends Serializable> 
		implements BaseDao<T,T1,PK>{

	@Autowired  
    private SqlSessionFactory sqlSessionFactory;
	
	private Class<?> entityMapperClass;
	
	protected BaseDao<T,T1, PK> getBaseMapper() {  
        SqlSession session = sqlSessionFactory.openSession();  
        entityMapperClass = ReflectionUtil.getMatcherMapper(getClass());  
        @SuppressWarnings("unchecked")  
        BaseDao<T,T1, PK> baseMapper = (BaseDao<T,T1, PK>) session  
                .getMapper(entityMapperClass);  
        return baseMapper;  
    }  
	
	public SqlSessionFactory getSqlSessionFactory() {  
        return sqlSessionFactory;  
    }  
  
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {  
        this.sqlSessionFactory = sqlSessionFactory;  
    } 
    
	public int countByExample(T1 t){
		return getBaseMapper().countByExample(t);
	}
	
	public int deleteByExample(T1 t){
		return getBaseMapper().deleteByExample(t);
	}
	
	public int deleteByPrimaryKey(PK id){
		return getBaseMapper().deleteByPrimaryKey(id);
	}
	
	public int insert(T t){
		return getBaseMapper().insert(t);
	}
	
	public int insertSelective(T t){
		return getBaseMapper().insertSelective(t);
	}
	
	public List<T> selectByExample(T1 t){
		return getBaseMapper().selectByExample(t);
	}
	
	public T selectByPrimaryKey(PK id){
		return getBaseMapper().selectByPrimaryKey(id);
	}
	
	public int updateByExampleSelective(@Param("record")T t1,@Param("example")T1 t2){
		return getBaseMapper().updateByExampleSelective(t1, t2);
	}
	
	public int updateByExample(@Param("record")T t1,@Param("example")T1 t2){
		return getBaseMapper().updateByExample(t1, t2);
	}
	
	public int updateByPrimaryKeySelective(T t){
		return getBaseMapper().updateByPrimaryKeySelective(t);
	}
	
	public int updateByPrimaryKey(T t){
		return getBaseMapper().updateByPrimaryKey(t);
	}
}
