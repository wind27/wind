package com.noriental.security.dao;

import com.noriental.security.domain.DomainLinkUser;

/**
 * 域、用户关系数据操作.
 * 
 * @author 钱春
 * 
 */
public interface DomainLinkUserDao {

	boolean create(DomainLinkUser du);
}
