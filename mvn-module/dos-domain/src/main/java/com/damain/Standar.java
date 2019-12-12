package com.damain;

public class Standar {
	private Integer id;
	private String name;
	private String min_weight;
	private String max_weight;
	private String min_length;
	private String max_length;
	
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
	public String getMin_weight() {
		return min_weight;
	}
	public void setMin_weight(String min_weight) {
		this.min_weight = min_weight;
	}
	public String getMax_weight() {
		return max_weight;
	}
	public void setMax_weight(String max_weight) {
		this.max_weight = max_weight;
	}
	public String getMin_length() {
		return min_length;
	}
	public void setMin_length(String min_length) {
		this.min_length = min_length;
	}
	public String getMax_length() {
		return max_length;
	}
	public void setMax_length(String max_length) {
		this.max_length = max_length;
	}
	
	@Override
	public String toString() {
		return "Standard [id=" + id + ", name=" + name + ", min_weight=" + min_weight + ", max_weight=" + max_weight
				+ ", min_length=" + min_length + ", max_length=" + max_length + "]";
	}

}
