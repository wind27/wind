package com.noriental.security.service;


import java.util.List;
import java.util.Map;

import com.noriental.security.domain.Function;

/**
 * 功能模块信息业务操作接口.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
public interface FunctionService {

	List<Function> find(Map<String, Object> params);

	List<Function> findByRoleId(Long roleId);

	List<Function> findByUserId(Map<String, Object> params);

	Function create(Function funcInfo);

	boolean update(Function funcInfo);

	boolean delete(Long id);

	Function findById(Long id);

	boolean updateFunction(Function functionInfo);

	boolean updateStatsByfuncIds(List<Long> funcIds, int stats);

	List<Function> findFuncForPage(Map<String, Object> params);

	Integer countFuncForPage(Map<String, Object> params);
	
	List<Function> findByGroupId(long groupId);
	
	List<Function> findUserFuncList(Map<String, Object> params);
}
