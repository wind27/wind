package com.noriental.security.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.noriental.security.dao.TSubjectDao;
import com.noriental.security.domain.TSubject;
import com.noriental.security.service.TSubjectService;

/**
 * 学科信息service实现
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
@Service("tSubjectService")
public class TSubjectServiceImpl implements TSubjectService {
	private static final Logger logger = LoggerFactory.getLogger(TSubjectServiceImpl.class);

    @Autowired
    private TSubjectDao tSubjectDao;

	@Override
	public List<TSubject> find(Map<String, Object> params) {
		return tSubjectDao.find(params);
	}

	public TSubjectDao gettSubjectDao() {
		return tSubjectDao;
	}

	public void settSubjectDao(TSubjectDao tSubjectDao) {
		this.tSubjectDao = tSubjectDao;
	}
	@Override
	public List<TSubject> findSubjectByUserId(long userId) {
		return tSubjectDao.findSubjectByUserId(userId);
	}
}
