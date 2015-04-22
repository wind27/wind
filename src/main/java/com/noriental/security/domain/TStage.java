package com.noriental.security.domain;



import java.io.Serializable;

/**
 * 数据字典分类表.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
public class TStage implements Serializable {

	private static final long serialVersionUID = -1368470199343379266L;
	
	public static int JUNIOR_HIGH_SCHOOL = 1;
	public static int SENIOR_MIDDLE_SCHOOL = 2;
	public static int PRIMARY_SCHOOL = 3;
	
	private Long id;
	
	private String name;
	
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

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}
	
}
