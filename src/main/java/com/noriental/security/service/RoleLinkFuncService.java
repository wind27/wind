package com.noriental.security.service;


import java.util.List;

import com.noriental.security.domain.RoleLinkFunc;

/**
 * 功能角色关联业务操作接口.
 * 
 * @author 肖诚
 * @version 1.0
 * 
 */
public interface RoleLinkFuncService {

	boolean batchCreate(List<RoleLinkFunc> rflist);

	List<RoleLinkFunc> findByRoleId(Long parseLong);

	boolean batchDeleteByRoleIds(List<Long> ids);


}
