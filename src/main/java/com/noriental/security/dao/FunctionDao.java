package com.noriental.security.dao;

import java.util.List;
import java.util.Map;

import com.noriental.security.domain.Function;

/**
 * 功能模块信息数据操作.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
public interface FunctionDao {

	List<Function> find(Map<String, Object> params);

	List<Function> findByRoleId(Long roleId);

	List<Function> findByUserId(Map<String, Object> params);

	List<Function> findByGroupId(Long id);

	Function create(Function funcInfo);

	boolean update(Function funcInfo);

	boolean delete(Long id);

	Function findById(Long id);

	boolean updateFunction(Function functionInfo);

	boolean updateStatsByfuncIds(List<Long> funcIds, int stats);

	List<Function> findFuncForPage(Map<String, Object> params);

	Integer countFuncForPage(Map<String, Object> params);
	
	List<Function> findUserFuncList(Map<String, Object> params);
}
