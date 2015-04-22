package com.noriental.security.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.noriental.common.BaseDaoImpl;
import com.noriental.security.dao.GroupLinkRoleDao;
import com.noriental.security.domain.GroupLinkRole;

/**
 * 角色人员关系数据操作.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
@Repository("groupLinkRoleDao")
public class GroupLinkRoleDaoImpl extends BaseDaoImpl<GroupLinkRole, Long> implements GroupLinkRoleDao{
	private final String namespace=GroupLinkRole.class.getName();

	@Override
	public boolean deleteByGroupId(Long groupId) {
		int len = this.findByGroupId(groupId).size();
		if(len > 0) {
			return super.delete(namespace+".deleteByGroupId", groupId);
		}
		return false;
	}

	@Override
	public boolean batchCreate(List<GroupLinkRole> grList) {
		return super.insert(namespace+".batchInsert", grList);
	}

	@Override
	public List<GroupLinkRole> findByRoleId(Long roleId) {
		return super.findList(namespace+".findByRoleId", roleId);
	}
	@Override
	public List<GroupLinkRole> findByGroupId(Long groupId) {
		return super.findList(namespace+".findByGroupId", groupId);
	}
	
}
