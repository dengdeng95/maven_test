package ${package!}.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${baseDao!}.BaseDao;
import ${package!}.model.${model!};
import ${package!}.model.${model!}Example;
import ${package!}.service.${model!}Service;
import ${baseServiceImpl!}.BaseServiceImpl;

/**
 * @author:      ${author!}
 * @version:     V1.0 
 * @Date:        ${date!}
 */
@Service("${model_small}Service")
@Transactional
public class ${model!}ServiceImpl extends BaseServiceImpl<${model!},${model!}Example,Long> implements ${model!}Service{

	@Resource(name = "${model_small}Dao")
	@Override
	public void setDao(BaseDao<${model!}, ${model!}Example, Long> dao) {
		super.dao = dao;
	}
}