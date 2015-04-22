package com.noriental.security.domain;



import java.io.Serializable;

/**
 * 数据字典分类表.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
public class TSubject implements Serializable {

	private static final long serialVersionUID = -1368470199343379266L;
	
	private Long id;
	
	private String name;
	
	private Long semesterId;
	
	private Long stageId;
	
	private String fullname;
	
	private int seq;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getSemesterId() {
		return semesterId;
	}
	public void setSemesterId(Long semesterId) {
		this.semesterId = semesterId;
	}
	public Long getStageId() {
		return stageId;
	}
	public void setStageId(Long stageId) {
		this.stageId = stageId;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
}
