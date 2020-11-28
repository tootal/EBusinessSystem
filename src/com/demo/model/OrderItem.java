package com.demo.model;

/**
 * 实体类-订单商品信息
 * @author MouseHappy
 */
public class OrderItem {
	
	/**
	 * create table orderitem
	(
		id varchar(40) primary key,
		quantity int,
		price double,
		order_id varchar(40),
		clothes_id varchar(40),
		constraint order_id_FK foreign key(order_id) references orders(id),
		constraint clothes_id_FK foreign key(clothes_id) references clothes(id)
	);
	 */
	
	private String id;
	private Clothes clothes;
	private int quantity;
	private double price;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Clothes getClothes() {
		return clothes;
	}
	public void setClothes(Clothes clothes) {
		this.clothes = clothes;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
