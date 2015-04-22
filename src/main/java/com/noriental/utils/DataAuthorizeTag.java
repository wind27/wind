package com.noriental.utils;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.beans.factory.annotation.Autowired;

import com.noriental.common.Constants.LoginType;
import com.noriental.security.domain.TSubject;
import com.noriental.security.domain.User;

public class DataAuthorizeTag extends TagSupport {
	@Autowired
	private UserUtils userUtils;
	
	private Integer gradeId = 0;
	private Integer subjectId = 0;
	private HttpServletRequest request;
	
	private static int[] subjectIds = new int[9];
	private static int[] gradeIds = new int[2];
	
	static {
		for(int i=0; i<9; i++) {
			subjectIds[i] = i+1;
		}
		gradeIds[0] = 2;
		gradeIds[1] = 3; 
	}
	
	//判断用户是否拥有该功能权限
	private boolean hasAuthorize() {
		if(!isLogin()) {
			return false;
		}
		//格式化请求信息
		List<TSubject> subjectVoList = getSubjectList();
		
		//判断用户是否登录	
		if(subjectVoList == null || subjectVoList.size() <= 0) {
			return false;
		}
		return checkRequstDataAuthorize();
		
	}
	//判断用户是否登录
	public boolean isLogin() {
		User currentUser = userUtils.getUser(LoginType.school, request);

		if(currentUser == null || currentUser.getId() > 0) {
			return false;
		}
		return true;
	}
	public List<TSubject> getSubjectList() {
//		List<Subject> subjectList = SubjectUtils.getSubject(ids);
//		List<Stage> stageList = StageUtils.getStage(subjectList);
//		List<Grade> gradeList = GradeUtils.getGrade(stageList);
//		return SessionUtils.getSubjectList(request.getSession());
		return null;
	}
	
	public void setSubjectAndGrade(List<TSubject> tSubjectList, Set<Long> subjectSet, Set<Long> gradeSet) {
		for(TSubject s : tSubjectList) {
			subjectSet.add(s.getId());
			gradeSet.add(s.getStageId());
		}
	}
	//检查是否具有数据权限
	public boolean checkRequstDataAuthorize() {
		Set<Long> subjectSet = new HashSet<Long>();
		Set<Long> gradeSet = new HashSet<Long>();
		//获取用户的数据权限
		List<TSubject> subjectList = getSubjectList();
		setSubjectAndGrade(subjectList, subjectSet, gradeSet);
		
		boolean flag = true;
		//学科检查
		if(subjectId > 0) {
			flag = subjectSet.contains(subjectId);
		} else {
			for(int id : subjectIds) {
				if(subjectSet.contains(id)) {
					continue;
				} else {
					return false;
				}
			}
		}
		if(flag == false) return false;
		//学段检查
		if(gradeId > 0) {
			flag = gradeSet.contains(subjectId);
		} else {
			for(int id : gradeIds) {
				if(gradeSet.contains(id)) {
					continue;
				} else {
					return false;
				}
			}
		}
		return flag;
	}
	@Override
	public int doStartTag() throws JspException {
		if(hasAuthorize()==true) {
			return EVAL_BODY_INCLUDE;
		} else {
			return SKIP_BODY;
		}
	}
	
	@Override
	public int doAfterBody() throws JspException {
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
}
