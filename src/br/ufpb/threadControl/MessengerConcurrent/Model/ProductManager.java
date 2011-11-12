package br.ufpb.threadControl.MessengerConcurrent.Model;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

/**
 * Product Manager
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 0.0.1 Copyright (C) 2011 Diego Sousa de Azevedo
 */

public class ProductManager {

	private LinkedBlockingQueue<Product> listProduct = new LinkedBlockingQueue<Product>();	
	private Logger logger = Logger.getLogger("br.ufpb.threadControl.birthdayMessage.Model.ProductManager");
	private static ProductManager productManager;
	
	private ProductManager() {}
	
	/*
	 * Singleton
	 */
	
	public static ProductManager getIntance(){
		if(productManager.equals(null)){
			productManager = new ProductManager();
			return productManager;
		}else{
			return productManager;
		}		
	}
	

	public void addProduct(Product product) {
		try {
			listProduct.put(product);
			logger.info("Product: " + product.getName() + " Code: "
					+ product.getCode() + " ,successfully added!");
		} catch (InterruptedException e) {
			e.getMessage();
		} catch (NullPointerException e) {
			e.getMessage();
		}
	}

	public Product removeProduct(double code) {
		Product productAux = locateProduct(code);

		if (productAux != null) {
			listProduct.remove(productAux);
			logger.info("Product: " + productAux.getName() + " Code: "
					+ productAux.getCode() + " ,successfully removed!");
			return productAux;
		} else {
			logger.info("Product not found!");
			return null;
		}
	}

	public Product editProduct(Product product) {

		for (Product productAux : listProduct) {
			if (productAux.getCode() == product.getCode()) {
				listProduct.remove(productAux);
				try {
					listProduct.put(product);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (NullPointerException e) {
					e.getMessage();
				}
				logger.info("Product: " + product.getName() + " Code: "
						+ productAux.getCode() + " updated!");
				return product;
			}
		}

		logger.info("Product not found!");
		return null;
	}

	public Product locateProduct(double code) {

		for (Product product : listProduct) {
			if (product.getCode() == code) {
				logger.info("Product: " + product.getName() + " Code: "
						+ product.getCode() + " found!");
				return product;
			}
		}
		logger.info("Product not Found");
		return null;
	}

	public LinkedBlockingQueue<Product> getListProduct() {
		return listProduct;
	}

}
