package com.damain;

public class SubAreaFixed {

	private Integer id;
	private String StartNum;
	private String EndNum;
	private String KeyWords;
	private String AssitKeyWords;
	private String AreaId;
	private String FixedAreaId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStartNum() {
		return StartNum;
	}
	public void setStartNum(String startNum) {
		StartNum = startNum;
	}
	public String getEndNum() {
		return EndNum;
	}
	public void setEndNum(String endNum) {
		EndNum = endNum;
	}
	public String getKeyWords() {
		return KeyWords;
	}
	public void setKeyWords(String keyWords) {
		KeyWords = keyWords;
	}
	public String getAssitKeyWords() {
		return AssitKeyWords;
	}
	public void setAssitKeyWords(String assitKeyWords) {
		AssitKeyWords = assitKeyWords;
	}
	public String getAreaId() {
		return AreaId;
	}
	public void setAreaId(String areaId) {
		AreaId = areaId;
	}
	public String getFixedAreaId() {
		return FixedAreaId;
	}
	public void setFixedAreaId(String fixedAreaId) {
		FixedAreaId = fixedAreaId;
	}
	@Override
	public String toString() {
		return "SubAreaFixed [id=" + id + ", StartNum=" + StartNum + ", EndNum=" + EndNum + ", KeyWords=" + KeyWords
				+ ", AssitKeyWords=" + AssitKeyWords + ", AreaId=" + AreaId + ", FixedAreaId=" + FixedAreaId + "]";
	}
	
	
}
