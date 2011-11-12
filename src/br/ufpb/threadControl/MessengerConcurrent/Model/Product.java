package br.ufpb.threadControl.MessengerConcurrent.Model;

/**
 * Entity Product
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 0.0.1
 * Copyright (C) 2011 Diego Sousa de Azevedo
 */

public class Product {

	private String name;
	private double code;
	private double price;
	private double quantity;

	public Product(String name, double code, double price, double quantity) {
		this.name = name;
		this.code = code;
		this.price = price;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCode() {
		return code;
	}

	public void setCode(double code) {
		this.code = code;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public String toString() {
		return "Name: " + this.name + "Code: " + this.code + "Price: "
				+ this.price + "Quantity: " + this.quantity;

	}

}
