package com.noriental.security.service;


import java.util.List;
import java.util.Map;

import com.noriental.security.domain.Admin;
import com.sumory.mybatis.pagination.result.PageResult;

/**
 * 人员信息业务操作接口.
 * 
 * @author 肖诚
 * @version 1.0
 * 
 */
public interface AdminService {

	List<Admin> find(Map<String, Object> params);

	Admin findById(Long id);

	boolean updateAdmin(Admin admin, Long groupId, Long[] primarySubject, Long[] juniorSubject, Long[] seniorSubject);
	boolean updateAdmin(Admin admin);
	boolean updateAdmin(Map<String, Object> params);

	PageResult<Admin> findForPage(Map<String, Object> params);

	boolean updatepasswd(Map<String, Object> params);

	List<Admin> findByGroupId(Long parseLong);

	boolean create(Admin admin, Long groupId, long domainId,
			Long[] primarySubject, Long[] juniorSubject, Long[] seniorSubject);

	int findSuperNum(Map<String, Object> params);
}
