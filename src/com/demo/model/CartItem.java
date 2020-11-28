package com.demo.model;

/**
 * 实体类-购物车单项
 * @author MouseHappy
 */
public class CartItem {
	private Clothes clothes;
	private int quantity;//数量
	private double price;//总价
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
