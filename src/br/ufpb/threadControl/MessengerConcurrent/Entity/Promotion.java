package br.ufpb.threadControl.MessengerConcurrent.Entity;

/**
 * Entity Promotion
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 0.0.1
 * Copyright (C) 2011 Diego Sousa de Azevedo
 */

public class Promotion {

	private Product product;	
	private double discountedPrice;
	private double promotionCode;

	public Promotion(Product product, double discountedPrice, double promotionCode) {
		this.product = product;
		this.discountedPrice = discountedPrice;
		this.promotionCode = promotionCode;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public double getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(double promotionCode) {
		this.promotionCode = promotionCode;
	}

	public String toString() {
		return "Product: " + product.getName() + " DiscountedPrice: " + discountedPrice
				+ " PromotionCode: " + promotionCode;
	}



}
