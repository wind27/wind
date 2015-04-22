package com.noriental.security.service;

import com.noriental.security.domain.Domain;

/**
 * 域信息 Service 接口.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
public interface DomainService {
	public Domain findByUserIdAndUserType(long userId, int userType);
	public boolean create(Domain domain);
}
