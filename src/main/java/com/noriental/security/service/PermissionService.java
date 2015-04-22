package com.noriental.security.service;


import java.util.List;
import java.util.Map;

import com.noriental.security.domain.Permission;

/**
 * 功能资源业务操作接口.
 * 
 * @author 肖诚
 * @version 1.0
 * 
 */
public interface PermissionService {

	List<Permission> findByFuncIds(List<Long> funcIds);

	boolean create(Permission Permission);

	boolean delete(Long id);

	Permission findById(Long id);

	boolean updatePermission(Permission PermissionceInfo);

	List<Permission> findPermissionForPage(Map<String, Object> params);

	Integer countPermissionForPage(Map<String, Object> params);

    List<Permission> findByGroupId(long groupId);
}
