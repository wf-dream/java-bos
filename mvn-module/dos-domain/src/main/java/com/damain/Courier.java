package com.damain;

public class Courier {
	private Integer id;
	private String courierNum;
	private String cname;
	private String telephone;
	private String PDA;
	private String checkPwd;
	private String company;
	private Integer standardId;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCourierNum() {
		return courierNum;
	}
	public void setCourierNum(String courierNum) {
		this.courierNum = courierNum;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPDA() {
		return PDA;
	}
	public void setPDA(String pDA) {
		PDA = pDA;
	}
	public String getCheckPwd() {
		return checkPwd;
	}
	public void setCheckPwd(String checkPwd) {
		this.checkPwd = checkPwd;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Integer getStandardId() {
		return standardId;
	}
	public void setStandardId(Integer standardId) {
		this.standardId = standardId;
	}
	
	@Override
	public String toString() {
		return "Courier [id=" + id + ", courierNum=" + courierNum + ", cname=" + cname + ", telephone=" + telephone
				+ ", PDA=" + PDA + ", checkPwd=" + checkPwd + ", company=" + company + ", standardId=" + standardId
				+ "]";
	}
		
}
