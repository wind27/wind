package com.noriental.security.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.noriental.security.dao.DomainDao;
import com.noriental.security.domain.Domain;
import com.noriental.security.service.DomainService;

/**
 * 域信息 Service 接口实现.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
@Service("domainService")
public class DomainServiceImpl implements DomainService {

	private static final Logger logger = LoggerFactory.getLogger(DomainServiceImpl.class);

	@Autowired
	private DomainDao domainDao;

	@Override
	public Domain findByUserIdAndUserType(long userId, int userType) {
		return domainDao.findByUserIdAndUserType(userId, userType);
	}

	@Override
	public boolean create(Domain domain) {
		return domainDao.create(domain);
	}
}
