package com.noriental.security.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.noriental.security.dao.TGradeDao;
import com.noriental.security.domain.TGrade;
import com.noriental.security.service.TGradeService;


/**
 * 学科信息service实现
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
@Service("tGradeService")
public class TGradeServiceImpl implements TGradeService {
	private static final Logger logger = LoggerFactory.getLogger(TGradeServiceImpl.class);
    @Autowired
    private TGradeDao tGradeDao;


	@Override
	public List<TGrade> find(Map<String, Object> params) {
		return tGradeDao.find(params);
	}
	public TGradeDao gettGradeDao() {
		return tGradeDao;
	}
	
	public void settGradeDao(TGradeDao tGradeDao) {
		this.tGradeDao = tGradeDao;
	}
	
	@Override
	public List<TGrade> findGradeByUserId(long userId) {
		return tGradeDao.findGradeByUserId(userId);
	}
}
