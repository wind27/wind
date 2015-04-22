package com.noriental.security.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.noriental.common.BaseDaoImpl;
import com.noriental.security.dao.DomainDao;
import com.noriental.security.domain.Domain;

/**
 * 域信息 dao 实现操作.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
@Repository("domainDao")
public class DomainDaoImpl extends BaseDaoImpl<Domain, Long> implements DomainDao{
	private final String namespace=Domain.class.getName();

	@Override
	public Domain findByUserIdAndUserType(long userId, int userType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("userType", userType);
		return super.find(namespace+".findByUserIdAndUserType", params);
	}

	@Override
	public boolean create(Domain domain) {
		return super.insert(namespace+".insert", domain);
	}
}
