package com.noriental.security.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.noriental.security.dao.AdminDao;
import com.noriental.security.dao.DomainLinkUserDao;
import com.noriental.security.dao.GroupDao;
import com.noriental.security.dao.GroupLinkUserDao;
import com.noriental.security.dao.TGradeDao;
import com.noriental.security.dao.TStageDao;
import com.noriental.security.dao.TSubjectDao;
import com.noriental.security.dao.UserLinkSubjectDao;
import com.noriental.security.domain.Admin;
import com.noriental.security.domain.DomainLinkUser;
import com.noriental.security.domain.Group;
import com.noriental.security.domain.GroupLinkUser;
import com.noriental.security.domain.TGrade;
import com.noriental.security.domain.TStage;
import com.noriental.security.domain.TSubject;
import com.noriental.security.domain.UserLinkSubject;
import com.noriental.security.service.AdminService;
import com.noriental.utils.Encrypt;
import com.sumory.mybatis.pagination.result.PageResult;

/**
 * 人员信息业务操作接口实现.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {

	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired(required = true)
	private AdminDao adminDao;
	
	@Autowired(required = true)
	private GroupLinkUserDao groupLinkUserDao;
	
	@Autowired(required = true)
	private UserLinkSubjectDao userLinkSubjectDao;
	
	@Autowired(required = true)
	private GroupDao groupDao;
	
	@Autowired(required = true)
	private TSubjectDao tSubjectDao;
	
	@Autowired(required = true)
	private TGradeDao tGradeDao;
	
	@Autowired(required = true)
	private TStageDao tStageDao;
	
	@Autowired(required = true)
	private DomainLinkUserDao domainLinkUserDao;
	
	/**
	 * 
	 * 
	 * */
	@Override
	public List<Admin> find(Map<String, Object> params) {
		return adminDao.find(params);
	}
	
	/**
	 * 
	 * 
	 * */
	@Override
	public Admin findById(Long id) {
		return adminDao.findById(id);
	}

	/***
	 * 
	 * 
	 * */
	@Override
	public boolean create(Admin admin, Long groupId, long domainId,
			Long[] primarySubject, Long[] juniorSubject, Long[] seniorSubject) {
		boolean flag = true;
		
		//创建用户
    	Encrypt encrypt = new Encrypt();
    	String password = encrypt.encrypt(admin.getLoginPass(), "MD5");
    	try {
			password = Base64.encodeBase64String(password.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	admin.setLoginPass(password);
        Admin k = adminDao.create(admin);
        
        if (null == k) {
        	logger.error("新增用户：新增admin失败！");
        	return false;
        }
        
        //创建用户与组的关系
        GroupLinkUser gu = new GroupLinkUser();
        gu.setGroupId(groupId);
        gu.setPersonId(k.getId());
        gu.setDomainId(domainId);
        gu.setUserType(Admin.USER_TYPE);
        flag = groupLinkUserDao.create(gu);
        
        //创建用户与学科之间的关系
        List<UserLinkSubject> usList = new ArrayList<UserLinkSubject>();
        if(juniorSubject!=null && juniorSubject.length>0) {
        	for(Long subjectId : juniorSubject){
        		UserLinkSubject us = new UserLinkSubject();
        		us.setGradeType(TStage.JUNIOR_HIGH_SCHOOL);
        		us.setPersonId(k.getId());
        		us.setSubjectId(subjectId);
        		us.setDomainId(domainId);
        		usList.add(us);
        	}
        }
        if(seniorSubject!=null && seniorSubject.length>0) {
        	for(Long subjectId : seniorSubject){
        		UserLinkSubject us = new UserLinkSubject();
        		us.setGradeType(TStage.SENIOR_MIDDLE_SCHOOL);
        		us.setPersonId(k.getId());
        		us.setSubjectId(subjectId);
        		us.setDomainId(domainId);
        		usList.add(us);
        	}
        }
        if(primarySubject!=null && primarySubject.length>0) {
        	for(Long subjectId : primarySubject){
        		UserLinkSubject us = new UserLinkSubject();
        		us.setGradeType(TStage.PRIMARY_SCHOOL);
        		us.setPersonId(k.getId());
        		us.setSubjectId(subjectId);
        		us.setDomainId(domainId);
        		usList.add(us);
        	}
        }
        
        if(usList!=null && usList.size()>0) {
        	flag = userLinkSubjectDao.batchCreate(usList);
        } 
        DomainLinkUser du = new DomainLinkUser();
        du.setDomainId(domainId);
        du.setUserId(k.getId());
        du.setUserType(Admin.USER_TYPE);
        
        flag = domainLinkUserDao.create(du);
		return flag;
	}

	@Override
	public boolean updateAdmin(Map<String, Object> params) {
		return adminDao.updateAdmin(params);
	}

	@Override
	public PageResult<Admin> findForPage(Map<String, Object> params) {
		return adminDao.findForPage(params);
	}

	@Override
	public boolean updatepasswd(Map<String, Object> params) {
		return adminDao.updatepasswd(params);
	}

	@Override
	public List<Admin> findByGroupId(Long groupId) {
		return adminDao.findByGroupId(groupId);
	}

	@Override
	public boolean updateAdmin(Admin admin, Long groupId,
			Long[] primarySubject, Long[] juniorSubject, Long[] seniorSubject) {
		boolean flag = true;
		
		Group group = groupDao.findById(groupId);
		
    	//建立新的用户与组的关系
    	flag = groupLinkUserDao.deleteByUserIdAndType(admin.getId(), Admin.USER_TYPE);
    	
    	GroupLinkUser gu = new GroupLinkUser();
    	gu.setGroupId(groupId);
    	gu.setPersonId(admin.getId());
    	gu.setDomainId(group.getDomainId());
    	gu.setUserType(Admin.USER_TYPE);
    	flag = groupLinkUserDao.create(gu);
    	
    	//建立用户与学科的新关系
    	flag = userLinkSubjectDao.deleteByUserIdAndUserType(admin.getId(), Admin.USER_TYPE);
    	
    	List<Long> ids = new ArrayList<Long>();//记录subjectIds
    	List<UserLinkSubject> usList = new ArrayList<UserLinkSubject>();
    	if(juniorSubject!=null && juniorSubject.length>0) {
    		for(Long subjectId : juniorSubject){
    			UserLinkSubject us = new UserLinkSubject();
    			us.setGradeType(TStage.JUNIOR_HIGH_SCHOOL);
    			us.setPersonId(admin.getId());
    			us.setSubjectId(subjectId);
    			us.setDomainId(group.getDomainId());
    			us.setUserType(Admin.USER_TYPE);
    			usList.add(us);
    			ids.add(subjectId);
    		}
    	}
    	if(seniorSubject!=null && seniorSubject.length>0) {
    		for(Long subjectId : seniorSubject){
    			UserLinkSubject us = new UserLinkSubject();
    			us.setGradeType(TStage.SENIOR_MIDDLE_SCHOOL);
    			us.setPersonId(admin.getId());
    			us.setSubjectId(subjectId);
    			us.setDomainId(group.getDomainId());
    			us.setUserType(Admin.USER_TYPE);
    			usList.add(us);
    			ids.add(subjectId);
    		}
    	}
    	if(primarySubject!=null && primarySubject.length>0) {
    		for(Long subjectId : primarySubject){
    			UserLinkSubject us = new UserLinkSubject();
    			us.setGradeType(TStage.PRIMARY_SCHOOL);
        		us.setPersonId(admin.getId());
        		us.setSubjectId(subjectId);
        		us.setDomainId(group.getDomainId());
    			us.setUserType(Admin.USER_TYPE);
        		usList.add(us);
    			ids.add(subjectId);
    		}
    	}
    	flag = userLinkSubjectDao.batchCreate(usList);
    	
    	//获取最新的用户数据权限
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("ids", ids);
    	List<TSubject> tSubjectList = tSubjectDao.find(params);
    	List<TStage> tStageList = new ArrayList<TStage>();
    	List<TGrade> tGradeList = new ArrayList<TGrade>();
    	
    	TStage tStage = null;
    	TGrade tGrade = null;
    	for(TSubject subject : tSubjectList) {
    		params.clear();
    		params.put("stageId", subject.getStageId());
    		tStage = tStageDao.find(params);
    		List<TGrade> tmpGradeList = tGradeDao.find(params);
    		setGradeList(tGradeList, tmpGradeList);
    		tStageList.add(tStage);
    		tGradeList.add(tGrade);
    	}
    	
    	//修改用户信息
    	admin.setUpdateTime(new Date());
		flag = adminDao.updateAdmin(admin);
    			
		return flag;
	}
	
	/**
	 * 
	 * 添加grade到缓存gradeList中
	 * 
	 * */
    public void setGradeList(List<TGrade> gradeList, List<TGrade> tGrades) {
		boolean flag = false;
		if(tGrades==null || tGrades.size()<=0) {
			return ;
		}
		
		for(TGrade t1 : tGrades) {
			if(gradeList != null && gradeList.size()>0) {
				for(TGrade t2 : gradeList) {
					if(t2 == null ) {
						continue;
					}
					if(t1 == null) {
						continue;
					}
					if(t1.getId() == t2.getId()) {
						flag = true;
						break;
					}
				}
			}
			if(flag == false) {
				gradeList.add(t1);
			}
			flag = false;
		}
	}

	public AdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public GroupLinkUserDao getGroupLinkUserDao() {
		return groupLinkUserDao;
	}

	public void setGroupLinkUserDao(GroupLinkUserDao groupLinkUserDao) {
		this.groupLinkUserDao = groupLinkUserDao;
	}

	public UserLinkSubjectDao getUserLinkSubjectDao() {
		return userLinkSubjectDao;
	}

	public void setUserLinkSubjectDao(
			UserLinkSubjectDao userLinkSubjectDao) {
		this.userLinkSubjectDao = userLinkSubjectDao;
	}

	public GroupDao getGroupDao() {
		return groupDao;
	}

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	public TSubjectDao gettSubjectDao() {
		return tSubjectDao;
	}

	public void settSubjectDao(TSubjectDao tSubjectDao) {
		this.tSubjectDao = tSubjectDao;
	}

	public TGradeDao gettGradeDao() {
		return tGradeDao;
	}

	public void settGradeDao(TGradeDao tGradeDao) {
		this.tGradeDao = tGradeDao;
	}

	public TStageDao gettStageDao() {
		return tStageDao;
	}

	public void settStageDao(TStageDao tStageDao) {
		this.tStageDao = tStageDao;
	}

	@Override
	public boolean updateAdmin(Admin admin) {
		return adminDao.updateAdmin(admin);
	}

	@Override
	public int findSuperNum(Map<String, Object> params) {
		return adminDao.findSuperNum(params);
	}
}
