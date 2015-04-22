package com.noriental.security.service;


import java.util.List;
import java.util.Map;

import com.noriental.security.domain.Group;
import com.sumory.mybatis.pagination.result.PageResult;

/**
 * 人员组信息业务操作接口.
 * 
 * @author 肖诚
 * @version 1.0
 * 
 */
public interface GroupService {

	List<Group> find(Map<String, Object> params);
	
	boolean updateGroup(Group group, Long[] roleIds);

	boolean delete(Long groupId);

	List<Group> findByUserIdAndUserType(Long userId, int userType);

	PageResult<Group> findForPage(Map<String, Object> params);

	boolean create(Group group, Long[] roleIds);

	Group findById(Long groupId);
}
