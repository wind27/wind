package com.noriental.security.service;


import java.util.List;
import java.util.Map;

import com.noriental.security.domain.TStage;

public interface TStageService {
	public TStage find(Map<String, Object> params);

	List<TStage> findStageByUserId(long userId);
}
