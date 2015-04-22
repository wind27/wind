package com.noriental.security.service;


import java.util.List;

import com.noriental.security.domain.GroupLinkRole;

/**
 * 组人员关联业务操作接口.
 * 
 * @author 肖诚
 * @version 1.0
 * 
 */
public interface GroupLinkRoleService {

	List<GroupLinkRole> findByRoleId(Long roleId);
}
