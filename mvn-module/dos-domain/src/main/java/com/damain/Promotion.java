package com.damain;


import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Promotion {

	private Integer id;
	private String title;
	private String titleImg;
	private String activeScope;
	private Date startDate;
	private Date endDate;
	private Integer status;
	private String description;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitleImg() {
		return titleImg;
	}
	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}
	public String getActiveScope() {
		return activeScope;
	}
	public void setActiveScope(String activeScope) {
		this.activeScope = activeScope;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Promotion [id=" + id + ", title=" + title + ", titleImg=" + titleImg + ", activeScope=" + activeScope
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + ", description="
				+ description + "]";
	}
	
	
}
