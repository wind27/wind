package com.noriental.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.noriental.security.dao.UserLinkSubjectDao;
import com.noriental.security.domain.UserLinkSubject;
import com.noriental.security.service.UserLinkSubjectService;

/**
 * 组科目关系业务处理接口实现类.
 * 
 * @author 钱春
 * 
 */
@Service("userLinkSubjectService")
public class UserLinkSubjectServiceImpl implements UserLinkSubjectService {

	private static final Logger logger = LoggerFactory.getLogger(UserLinkSubjectServiceImpl.class);

	@Autowired(required = true)
	private UserLinkSubjectDao userLinkSubjectDao;


	@Override
	public List<UserLinkSubject> findByUserIdAndUserType(Long userId, int userType) {
		return userLinkSubjectDao.findByUserIdAndUserType(userId, userType);
	}

	@Override
	public boolean deleteByUserIdAndUserType(long userId, int userType) {
		return userLinkSubjectDao.deleteByUserIdAndUserType(userId, userType);
	}

	@Override
	public boolean batchCreate(List<UserLinkSubject> usList) {
		return userLinkSubjectDao.batchCreate(usList);
	}

	public UserLinkSubjectDao getUserLinkSubjectDao() {
		return userLinkSubjectDao;
	}

	public void setUserLinkSubjectDao(UserLinkSubjectDao userLinkSubjectDao) {
		this.userLinkSubjectDao = userLinkSubjectDao;
	}
}
