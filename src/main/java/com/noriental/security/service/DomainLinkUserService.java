package com.noriental.security.service;

import com.noriental.security.domain.DomainLinkUser;

/**
 * 域、用户关系接口.
 * 
 * @author 钱春
 * 
 */
public interface DomainLinkUserService {
	
	public boolean create(DomainLinkUser du);
}
