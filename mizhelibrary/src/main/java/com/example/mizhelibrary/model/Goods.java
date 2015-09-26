package com.example.mizhelibrary.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Goods implements Serializable{

	private String url_goods;
	private String url_big_iamge;
	private String describe;
	private String price;
	private String org_price;
	private String people_num;
	
	
	public Goods(String url_goods, String url_big_iamge, String describe, String price, String org_price,
			String people_num) {
		super();
		this.url_goods = url_goods;
		this.url_big_iamge = url_big_iamge;
		this.describe = describe;
		this.price = price;
		this.org_price = org_price;
		this.people_num = people_num;
	}
	public String getUrl_goods() {
		return url_goods;
	}
	public void setUrl_goods(String url_goods) {
		this.url_goods = url_goods;
	}
	public String getUrl_big_iamge() {
		return url_big_iamge;
	}
	public void setUrl_big_iamge(String url_big_iamge) {
		this.url_big_iamge = url_big_iamge;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getOrg_price() {
		return org_price;
	}
	public void setOrg_price(String org_price) {
		this.org_price = org_price;
	}
	public String getPeople_num() {
		return people_num;
	}
	public void setPeople_num(String people_num) {
		this.people_num = people_num;
	}
	@Override
	public String toString() {
		return "Goods [url_goods=" + url_goods + ", url_big_iamge=" + url_big_iamge + ", describe=" + describe
				+ ", price=" + price + ", org_price=" + org_price + ", people_num=" + people_num + "]";
	}
	
	
	
}
