package com.noriental.security.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.noriental.common.BaseDaoImpl;
import com.noriental.security.dao.GroupDao;
import com.noriental.security.domain.Group;
import com.sumory.mybatis.pagination.result.PageResult;

/**
 * 人员组信息数据操作.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
@Repository("groupDao")
public class GroupDaoImpl extends BaseDaoImpl<Group, Long> implements GroupDao {
	private final String namespace = Group.class.getName();
	
	@Override
	public List<Group> find(Map<String, Object> params) {
		return super.findList(namespace+".find", params);
	}

	@Override
	public boolean updateGroup(Group group) {
		return super.update(group);
	}

	@Override
	public boolean delete(Long id) {
		return super.delete(Group.class, id);
	}

	@Override
	public List<Group> findByUserIdAndUserType(Long userId, int userType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personId", userId);
		params.put("userType", userType);
		
		return super.findList(namespace+".findByUserIdAndUserType", params);
	}

	@Override
	public PageResult<Group> findForPage(Map<String, Object> params) {
		PageBounds pageBounds = (PageBounds) params.get("pager");
		return super.findPageResult(namespace+".findForPage", params, pageBounds);
	}

	@Override
	public Group create(Group group) {
		return super.insertGetId(group);
	}

	@Override
	public Group findById(Long groupId) {
		return super.findById(Group.class, groupId);
	}

	@Override
	public List<Group> findByRoleId(Long roleId) {
		return super.findList(namespace+".findByRoleId", roleId);
	}
}
