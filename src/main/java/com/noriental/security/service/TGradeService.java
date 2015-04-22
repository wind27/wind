package com.noriental.security.service;


import java.util.List;
import java.util.Map;

import com.noriental.security.domain.TGrade;

public interface TGradeService {
	public List<TGrade> find(Map<String, Object> params);

	List<TGrade> findGradeByUserId(long userId);
}
