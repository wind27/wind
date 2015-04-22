package com.noriental.security.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.noriental.common.BaseDaoImpl;
import com.noriental.security.dao.TSubjectDao;
import com.noriental.security.domain.TSubject;

/**
 * 数据字典分类数据操作.
 * 
 * @author 肖诚
 * @version 1.0
 * 
 */
@Repository("tSubjectDao")
public class TSubjectDaoImpl extends BaseDaoImpl<TSubject, Long> implements TSubjectDao{
	private final String namespace = TSubject.class.getName();
	
	@Override
	public List<TSubject> find(Map<String, Object> params) {
		return super.findList(namespace+".find", params);
	}
	@Override
	public List<TSubject> findSubjectByUserId(long userId) {
		return (List<TSubject>) super.findList(namespace+".findSubjectByUserId", userId);
	}
}
