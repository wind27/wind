package com.noriental.security.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.noriental.security.dao.TStageDao;
import com.noriental.security.domain.TStage;
import com.noriental.security.service.TStageService;

/**
 * 学科信息service实现
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
@Service("tStageService")
public class TStageServiceImpl implements TStageService {
	private static final Logger logger = LoggerFactory.getLogger(TStageServiceImpl.class);

    @Autowired
    private TStageDao tStageDao;

	@Override
	public TStage find(Map<String, Object> params) {
		return tStageDao.find(params);
	}
	public TStageDao gettStageDao() {
		return tStageDao;
	}
	
	public void settStageDao(TStageDao tStageDao) {
		this.tStageDao = tStageDao;
	}
	@Override
	public List<TStage> findStageByUserId(long userId) {
		return tStageDao.findStageByUserId(userId);
	}
}
