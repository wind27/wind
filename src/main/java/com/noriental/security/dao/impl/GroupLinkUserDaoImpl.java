package com.noriental.security.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.noriental.common.BaseDaoImpl;
import com.noriental.security.dao.GroupLinkUserDao;
import com.noriental.security.domain.GroupLinkUser;

/**
 * 角色人员关系数据操作.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
@Repository("groupLinkUserDao")
public class GroupLinkUserDaoImpl extends BaseDaoImpl<GroupLinkUser, Long> implements GroupLinkUserDao{
	private final String namespace=GroupLinkUser.class.getName();
	
	@Override
	public List<GroupLinkUser> find(GroupLinkUser gu) {
		return super.findList(namespace+".find", gu);
	}

	@Override
	public boolean create(GroupLinkUser groupLinkUser) {
		return super.insert(groupLinkUser);
	}

	@Override
	public boolean batchCreate(List<GroupLinkUser> guList) {
		return super.insert(namespace+".batchInsert", guList);
	}

	@Override
	public boolean deleteByUserIdAndType(long userId, int userType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personId", userId);
		params.put("userType", userType);
		return super.delete(namespace+".deleteByUserIdAndType", params);
	}
}
