package com.noriental.security.dao;

import java.util.List;
import java.util.Map;

import com.noriental.security.domain.TGrade;

/**
 * 数据字典分类数据操作.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
public interface TGradeDao {

	List<TGrade> find(Map<String, Object> params);

	List<TGrade> findGradeByUserId(long userId);
}
