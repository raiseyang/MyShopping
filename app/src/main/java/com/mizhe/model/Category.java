package com.mizhe.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Category implements Serializable{

	private String url;
	private String name;
	
	
	public Category(String url, String name) {
		super();
		this.url = url;
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "TeMaiCategory [url=" + url + ", name=" + name + "]";
	}
	
	
}
