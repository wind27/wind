package com.noriental.security.service;


import java.util.List;

import com.noriental.security.domain.UserLinkSubject;

/**
 * 组科目关系业务处理接口.
 * 
 * @author 肖诚
 * 
 */
public interface UserLinkSubjectService {

	List<UserLinkSubject> findByUserIdAndUserType(Long userId, int userType);

	boolean deleteByUserIdAndUserType(long userId, int userType);

	boolean batchCreate(List<UserLinkSubject> usList);
}
