package com.noriental.security.service;

import java.util.List;

import com.noriental.security.domain.GroupLinkUser;

/**
 * 组人员关联业务操作接口.
 * 
 * @author 肖诚
 * @version 1.0
 * 
 */
public interface GroupLinkUserService {

	List<GroupLinkUser> find(GroupLinkUser gu);
	boolean batchCreate(List<GroupLinkUser> groupLinkUsList);
	boolean deleteByUserIdAndType(long userId, int userType);
}
