package com.noriental.security.dao;

import java.util.List;

import com.noriental.security.domain.GroupLinkUser;

/**
 * 角色人员关系数据操作.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
public interface GroupLinkUserDao {

	List<GroupLinkUser> find(GroupLinkUser gu);

	boolean create(GroupLinkUser newGlu);

	boolean batchCreate(List<GroupLinkUser> guList);

	boolean deleteByUserIdAndType(long userId, int userType);
}
