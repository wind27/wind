package com.noriental.security.service;


import java.util.List;
import java.util.Map;

import com.noriental.security.domain.TSubject;

public interface TSubjectService {
	
	public List<TSubject> find(Map<String, Object> params);

	List<TSubject> findSubjectByUserId(long userId);

}
