package com.noriental.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noriental.security.dao.DomainLinkUserDao;
import com.noriental.security.domain.DomainLinkUser;
import com.noriental.security.service.DomainLinkUserService;

/**
 * 域、用户关系接口实现.
 * 
 * @author 钱春
 * 
 */
@Service("domainLinkUserService")
public class DomainLinkUserServiceImpl implements DomainLinkUserService {

	@Autowired(required = true)
	private DomainLinkUserDao domainLinkUserDao;


	@Override
	public boolean create(DomainLinkUser du) {
		return domainLinkUserDao.create(du);
	}
}
