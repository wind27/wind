package com.noriental.security.dao;

import java.util.List;
import java.util.Map;

import com.noriental.security.domain.TSubject;

/**
 * 数据字典分类数据操作.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
public interface TSubjectDao {

	List<TSubject> find(Map<String, Object> params);

	List<TSubject> findSubjectByUserId(long userId);
}
