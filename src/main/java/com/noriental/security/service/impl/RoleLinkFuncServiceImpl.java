package com.noriental.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.noriental.security.dao.RoleLinkFuncDao;
import com.noriental.security.domain.RoleLinkFunc;
import com.noriental.security.service.RoleLinkFuncService;

/**
 * 功能角色关联业务操作接口实现.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
@Service("roleLinkFuncService")
public class RoleLinkFuncServiceImpl implements RoleLinkFuncService {
	private static final Logger logger = LoggerFactory.getLogger(RoleLinkFuncServiceImpl.class);

	@Autowired(required = true)
	private RoleLinkFuncDao roleFuncInfoDao;

	
	@Override
	public boolean batchCreate(List<RoleLinkFunc> rflist) {
		return roleFuncInfoDao.batchCreate(rflist);
	}

	@Override
	public List<RoleLinkFunc> findByRoleId(Long roleId) {
		return roleFuncInfoDao.findByRoleId(roleId);
	}

	@Override
	public boolean batchDeleteByRoleIds(List<Long> ids) {
		return roleFuncInfoDao.batchDeleteByRoleIds(ids);
	}

	public RoleLinkFuncDao getRoleFuncInfoDao() {
		return roleFuncInfoDao;
	}

	public void setRoleFuncInfoDao(RoleLinkFuncDao roleFuncInfoDao) {
		this.roleFuncInfoDao = roleFuncInfoDao;
	}
}
