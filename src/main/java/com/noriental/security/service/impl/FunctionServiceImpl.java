package com.noriental.security.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.noriental.security.dao.FunctionDao;
import com.noriental.security.domain.Function;
import com.noriental.security.service.FunctionService;

/**
 * 功能模块信息业务操作接口实现.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
@Service("functionService")
public class FunctionServiceImpl implements FunctionService {

	private static final Logger logger = LoggerFactory.getLogger(FunctionServiceImpl.class);

	@Autowired(required = true)
	private FunctionDao functionDao;

	@Override
	public List<Function> find(Map<String, Object> params) {
		return functionDao.find(params);
	}

	@Override
	public List<Function> findByRoleId(Long roleId) {
		return functionDao.findByRoleId(roleId);
	}

	@Override
	public List<Function> findByUserId(Map<String, Object> params) {
		return functionDao.findByUserId(params);
	}

	@Override
	public Function create(Function funcInfo) {
		return functionDao.create(funcInfo);
	}

	@Override
	public boolean update(Function funcInfo) {
		return functionDao.update(funcInfo);
	}

	@Override
	public boolean delete(Long id) {
		return functionDao.delete(id);
	}

	@Override
	public Function findById(Long id) {
		return functionDao.findById(id);
	}

	@Override
	public boolean updateFunction(Function functionInfo) {
		return functionDao.updateFunction(functionInfo);
	}

	@Override
	public boolean updateStatsByfuncIds(List<Long> funcIds, int stats) {
		return functionDao.updateStatsByfuncIds(funcIds, stats);
	}

	@Override
	public List<Function> findFuncForPage(Map<String, Object> params) {
		return functionDao.findFuncForPage(params);
	}

	@Override
	public Integer countFuncForPage(Map<String, Object> params) {
		return functionDao.countFuncForPage(params);
	}

	@Override
	public List<Function> findByGroupId(long groupId) {
		return functionDao.findByGroupId(groupId);
	}

	public FunctionDao getFunctionDao() {
		return functionDao;
	}

	public void setFunctionDao(FunctionDao functionDao) {
		this.functionDao = functionDao;
	}

	@Override
	public List<Function> findUserFuncList(Map<String, Object> params) {
		return functionDao.findUserFuncList(params);
	}
}
