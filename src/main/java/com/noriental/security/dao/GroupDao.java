package com.noriental.security.dao;

import java.util.List;
import java.util.Map;

import com.noriental.security.domain.Group;
import com.sumory.mybatis.pagination.result.PageResult;


/**
 * 人员组信息数据操作.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
public interface GroupDao {

	List<Group> find(Map<String, Object> params);

	boolean updateGroup(Group group);

	boolean delete(Long groupId);

	List<Group> findByUserIdAndUserType(Long userId, int userType);

	PageResult<Group> findForPage(Map<String, Object> params);

	Group create(Group group);

	Group findById(Long groupId);

	List<Group> findByRoleId(Long roleId);
}
