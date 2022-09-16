package com.dityudha.spring.data.model;

import javax.persistence.*;
@Entity
@Table(name = "bikes")

public class Tutorial {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "brand")
	private String brand;
	@Column(name = "type")
	private String type;
	@Column(name = "sold")
	private boolean sold;
	public Tutorial() {
	}
	
	public Tutorial(String brand, String type, boolean sold) {
		this.brand = brand;
		this.type = type;
		this.sold = sold;
	}
	
	public long getId() {
	return id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isSold() {
		return sold;
	}
	public void setSold(boolean isSold) {
		this.sold = isSold;
	}
	
	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", brand=" + brand + ", type=" + type + ", sold=" + sold + "]";
	}

//	public long getId() {
//		return id;
//	}
//	public String getTitle() {
//		return title;
//	}
//	public void setTitle(String title) {
//		this.title = title;
//	}
//	public String getDescription() {
//		return description;
//	}
//	public void setDescription(String description) {
//		this.description = description;
//	}
//	public boolean isPublished() {
//		return published;
//	}
//	public void setPublished(boolean isPublished) {
//		this.published = isPublished;
//	}
}