package com.noriental.security.dao;

import java.util.List;

import com.noriental.security.domain.UserLinkSubject;

/**
 * 组科目关系数据操作.
 * 
 * @author 钱春
 * 
 */
public interface UserLinkSubjectDao {

	List<UserLinkSubject> findByUserIdAndUserType(Long userId, int userType);

	boolean deleteByUserIdAndUserType(Long userId, int userType);

	boolean batchCreate(List<UserLinkSubject> usList);
}
