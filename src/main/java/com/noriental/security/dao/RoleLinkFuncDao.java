package com.noriental.security.dao;

import java.util.List;

import com.noriental.security.domain.RoleLinkFunc;

/**
 * 功能角色关联数据操作.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
public interface RoleLinkFuncDao {

	boolean batchCreate(List<RoleLinkFunc> rflist);

	List<RoleLinkFunc> findByRoleId(Long roleId);

	boolean batchDeleteByRoleIds(List<Long> ids);

}
