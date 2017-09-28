package com.dhf.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhf.dao.BaseDao;
import com.dhf.model.TestGenerator;
import com.dhf.model.TestGeneratorExample;
import com.dhf.service.TestGeneratorService;
import com.dhf.service.impl.BaseServiceImpl;

/**
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2017-09-05 16:29:23
 */
@Service("testGeneratorService")
@Transactional
public class TestGeneratorServiceImpl extends BaseServiceImpl<TestGenerator,TestGeneratorExample,Long> implements TestGeneratorService{

	@Resource(name = "testGeneratorDao")
	@Override
	public void setDao(BaseDao<TestGenerator, TestGeneratorExample, Long> dao) {
		super.dao = dao;
	}
}