package com.demo.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 实体类-订单
 * @author MouseHappy
 */
public class Order {
	
	/**
	 * create table orders
	(
		id varchar(40) primary key,
		ordertime datetime not null,
		price double not null,
		state boolean,
		user_id varchar(40),
		constraint user_id_FK foreign key(user_id) references user(id)
	);
	 */
	private String id;
	private Date orderTime;
	private double price;
	private boolean state;
	private User user;//记住订单属于哪个用户   user_id
	private Set<OrderItem> orderItems = new HashSet<OrderItem>(); //用来保存订单单项的集合
	
	
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
