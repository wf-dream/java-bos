package com.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="customer")
public class Customer {

	private Integer id;
	private String username;
	private String password;
	private String type;
	private String sex;
	private String telephone;
	private String address;
	private String email;
	private String fixed_area_id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFixed_area_id() {
		return fixed_area_id;
	}
	public void setFixed_area_id(String fixed_area_id) {
		this.fixed_area_id = fixed_area_id;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", username=" + username + ", password=" + password + ", type=" + type + ", sex="
				+ sex + ", telephone=" + telephone + ", address=" + address + ", email=" + email + ", fixed_area_id="
				+ fixed_area_id + "]";
	}
	
	

}
