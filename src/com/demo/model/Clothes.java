package com.demo.model;

/**
 * 实体类-商品
 * @author MouseHappy
 */
public class Clothes {
	
	/**
	 * create table clothes
	   (
	  		id varchar(40) primary key,
	  		name varchar(100) not null unique,
	  		brand varchar(100) not null,
	  		price double not null,
	 		image varchar(100),
	  		description varchar(255),
	  		category_id varchar(40),
	  		constraint category_id_FK foreign key(category_id) references category(id)
	   );
	 */
	
	private String id;
	private String name;
	private String brand;
	private double price;
	private String image;//记住图片的名称
	private String description;
	private String categoryId;//维护所属分类的id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
}
