package com.noriental.security.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.noriental.common.BaseDaoImpl;
import com.noriental.security.dao.UserLinkSubjectDao;
import com.noriental.security.domain.UserLinkSubject;

/**
 * 组科目关系数据操作.
 * 
 * @author 钱春
 * 
 */
@Repository("userLinkSubjectDao")
public class UserLinkSubjectDaoImpl extends BaseDaoImpl<UserLinkSubject, Long> implements UserLinkSubjectDao{
	private final String namespace=UserLinkSubject.class.getName();
	
	@Override
	public List<UserLinkSubject> findByUserIdAndUserType(Long userId, int userType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personId", userId);
		params.put("userType", userType);
		return super.findList(namespace+".findByUserIdAndUserType", params);
	}

	@Override
	public boolean deleteByUserIdAndUserType(Long userId, int userType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personId", userId);
		params.put("userType", userType);
		return super.delete(namespace+".deleteByUserIdAndUserType", params);
	}

	@Override
	public boolean batchCreate(List<UserLinkSubject> usList) {
		boolean flag = true;
		if(usList!=null && usList.size()>0) {
			flag =  super.insert(namespace+".batchInsert", usList);
		}
		return flag;
	}

}
