package com.damain;

public class Fixed {

	private Integer id;
	private String FixedAreaName;
	private Integer FixedAreaLeader;
	private String telephone;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFixedAreaName() {
		return FixedAreaName;
	}
	public void setFixedAreaName(String fixedAreaName) {
		FixedAreaName = fixedAreaName;
	}
	public Integer getFixedAreaLeader() {
		return FixedAreaLeader;
	}
	public void setFixedAreaLeader(Integer fixedAreaLeader) {
		FixedAreaLeader = fixedAreaLeader;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Override
	public String toString() {
		return "Fixed [id=" + id + ", FixedAreaName=" + FixedAreaName + ", FixedAreaLeader=" + FixedAreaLeader
				+ ", telephone=" + telephone + "]";
	}
		
}
