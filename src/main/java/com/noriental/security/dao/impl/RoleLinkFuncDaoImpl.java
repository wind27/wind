package com.noriental.security.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.noriental.common.BaseDaoImpl;
import com.noriental.security.dao.RoleLinkFuncDao;
import com.noriental.security.domain.RoleLinkFunc;

/**
 * 功能角色关联数据操作.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
@Repository("roleFuncInfoDao")
public class RoleLinkFuncDaoImpl extends BaseDaoImpl<RoleLinkFunc, Long> implements RoleLinkFuncDao{
	private final String namespace=RoleLinkFunc.class.getName();
	
	@Override
	public boolean batchCreate(List<RoleLinkFunc> rflist) {
		boolean flag = true;
		if(rflist != null && rflist.size()>0) {
			flag = super.insert(namespace+".batchInsert", rflist);
		}
		return flag;
	}

	@Override
	public List<RoleLinkFunc> findByRoleId(Long roleId) {
		return super.findList(namespace+".findByRoleId", roleId);
	}

	@Override
	public boolean batchDeleteByRoleIds(List<Long> roleIds) {
		boolean flag = true;
		if(roleIds != null && roleIds.size()>0) {
			flag = super.delete(namespace+".batchDelete", roleIds);
		}
		return flag;
	}

}
