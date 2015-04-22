package com.noriental.security.dao.impl;

import org.springframework.stereotype.Repository;

import com.noriental.common.BaseDaoImpl;
import com.noriental.security.dao.DomainLinkUserDao;
import com.noriental.security.domain.DomainLinkUser;

/**
 * 域、用户关系数据操作.
 * 
 * @author 钱春
 * 
 */
@Repository("domainLinkUserDao")
public class DomainLinkUserDaoImpl extends BaseDaoImpl<DomainLinkUser, Long> implements DomainLinkUserDao{
	private final String namespace=DomainLinkUser.class.getName();

	@Override
	public boolean create(DomainLinkUser du) {
		return super.insert(namespace+".create", du);
	}
	

}
