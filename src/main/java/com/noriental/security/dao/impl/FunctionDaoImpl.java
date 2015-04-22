package com.noriental.security.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.noriental.common.BaseDaoImpl;
import com.noriental.security.dao.FunctionDao;
import com.noriental.security.domain.Function;

/**
 * 功能模块信息数据操作.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
@Repository("functionDao")
public class FunctionDaoImpl extends BaseDaoImpl<Function, Long> implements FunctionDao{
	private final String namespace = Function.class.getName();
	
	@Override
	public List<Function> find(Map<String, Object> params) {
		return super.findList(namespace+".find", params);
	}
	@Override
	public List<Function> findByRoleId(Long roleId) {
		return super.findList(namespace+".findByRoleId", roleId);
	}
	@Override
	public List<Function> findByUserId(Map<String, Object> params) {
		return super.findList(namespace+".findByUserId", params);
	}
	@Override
	public List<Function> findByGroupId(Long groupId) {
		return super.findList(namespace+".findByGroupId", groupId);
	}
	@Override
	public Function create(Function func) {
		return super.insertGetId(func);
	}
	@Override
	public boolean delete(Long id) {
		return super.delete(Function.class, id);
	}
	@Override
	public Function findById(Long id) {
		return super.findById(Function.class, id);
	}
	@Override
	public boolean updateFunction(Function func) {
		return super.update(func);
	}
	@Override
	public boolean updateStatsByfuncIds(List<Long> funcIds, int stats) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("funcIds", funcIds);
		params.put("stats", stats);
		
		return super.update(namespace+".updateStatsByfuncIds", params);
	}
	@Override
	public List<Function> findFuncForPage(Map<String, Object> params) {
		PageBounds pageBounds = (PageBounds) params.get("pager");
		
		return super.findList(namespace+".findFuncForPage", params);
//		return super.findPagedList(namespace+".findFuncForPage", params, pageBounds);
	}
	@Override
	public Integer countFuncForPage(Map<String, Object> params) {
		return super.findList(namespace+".countFuncForPage", params).size();
	}
	@Override
	public List<Function> findUserFuncList(Map<String, Object> params) {
		return super.findList(namespace+".findUserFuncList", params);
	}
}
