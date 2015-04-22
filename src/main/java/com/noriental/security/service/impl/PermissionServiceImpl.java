package com.noriental.security.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.noriental.security.dao.PermissionDao;
import com.noriental.security.domain.Permission;
import com.noriental.security.service.PermissionService;

/**
 * 功能资源业务操作接口实现.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

	private static final Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);
	
	
	@Autowired
	private PermissionDao premissionDao;

	@Override
	public List<Permission> findByFuncIds(List<Long> funcIds) {
		return premissionDao.findByFuncIds(funcIds);
	}

	@Override
	public boolean create(Permission permission) {
		return premissionDao.create(permission);
	}

	@Override
	public boolean delete(Long id) {
		return premissionDao.delete(id);
	}

	@Override
	public Permission findById(Long id) {
		return premissionDao.findById(id);
	}

	@Override
	public boolean updatePermission(Permission permission) {
		return premissionDao.updatePermission(permission);
	}

	@Override
	public List<Permission> findPermissionForPage(
			Map<String, Object> params) {
		return premissionDao.findPermissionForPage(params);
	}

	@Override
	public Integer countPermissionForPage(Map<String, Object> params) {
		return premissionDao.countPermissionForPage(params);
	}
	@Override
    public List<Permission> findByGroupId(long groupId) {
        return premissionDao.findByGroupId(groupId);
    }
}
