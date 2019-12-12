package com.damain;

public class Area {

	private Integer id;
	private String areacode;
	private String province;
	private String city;
	private String distrcit;
	private String postcode;
	private String citycode;
	private String shortcode;
	
	private String showname;
	
	public String getShowname() {
		return province+"-"+city+"-"+distrcit;
	}
	public void setShowname(String showname) {
		this.showname = showname;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAreacode() {
		return areacode;
	}
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrcit() {
		return distrcit;
	}
	public void setDistrcit(String distrcit) {
		this.distrcit = distrcit;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getCitycode() {
		return citycode;
	}
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	public String getShortcode() {
		return shortcode;
	}
	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}
	
	@Override
	public String toString() {
		return "Area [id=" + id + ", areacode=" + areacode + ", province=" + province + ", city=" + city + ", distrcit="
				+ distrcit + ", postcode=" + postcode + ", citycode=" + citycode + ", shortcode=" + shortcode + "]";
	}
	
	
}
