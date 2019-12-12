package com.damain.system;

import com.alibaba.fastjson.annotation.JSONField;

public class Resourcee {

	private Integer id;
	private String name;
	private String grantKey;
	@JSONField(name="pageUrl")
	private String page;
	private String seq;
	private String resourceType;
	private String icon;
	@JSONField(name="_parentId")
	private Integer pId;
	private Integer open;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrantKey() {
		return grantKey;
	}
	public void setGrantKey(String grantKey) {
		this.grantKey = grantKey;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	public Integer getOpen() {
		return open;
	}
	public void setOpen(Integer open) {
		this.open = open;
	}
	@Override
	public String toString() {
		return "Resourcee [id=" + id + ", name=" + name + ", grantKey=" + grantKey + ", page=" + page + ", seq=" + seq
				+ ", resourceType=" + resourceType + ", icon=" + icon + ", pId=" + pId + ", open=" + open + "]";
	}
	
	
	
}
