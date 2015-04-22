package com.noriental.security.domain;



import java.io.Serializable;

/**
 * 数据字典分类表.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
public class TSubjectVo implements Serializable {

	private static final long serialVersionUID = -1368470199343379266L;
	
	private TSubject tSubject;
	
	private TStage tStage;

	public TSubject gettSubject() {
		return tSubject;
	}

	public void settSubject(TSubject tSubject) {
		this.tSubject = tSubject;
	}

	public TStage gettStage() {
		return tStage;
	}

	public void settStage(TStage tStage) {
		this.tStage = tStage;
	}

}
