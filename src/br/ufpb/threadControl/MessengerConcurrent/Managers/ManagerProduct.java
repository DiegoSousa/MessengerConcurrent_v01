package br.ufpb.threadControl.MessengerConcurrent.Managers;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Product;

/**
 * Product Manager
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 2.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class ManagerProduct {

	private static ManagerProduct productManager;
	private BlockingQueue<Product> listProduct;
	private Logger logger;

	private ManagerProduct() {
		this.listProduct = new LinkedBlockingQueue<Product>();
		this.logger = Logger
				.getLogger("br.ufpb.threadControl.birthdayMessage.Model.ProductManager");
	}

	/*
	 * Singleton
	 */

	public static synchronized ManagerProduct getInstance() {
		if (productManager == null) {
			productManager = new ManagerProduct();
		}
		return productManager;
	}

	public synchronized Product addProduct(Product product) {
		if (product != null) {
			try {
				listProduct.put(product);
				logger.info("Product: " + product.getName() + " Code: "
						+ product.getCode() + " ,successfully added!");
			} catch (InterruptedException e) {
				e.getMessage();
			} catch (NullPointerException e) {
				e.getMessage();
			}
			return product;

		} else {
			return null;
		}
	}

	public synchronized Product removeProduct(Product product) {

		if (product != null) {
			listProduct.remove(product);
			logger.info("Product: " + product.getName() + " Code: "
					+ product.getCode() + " ,successfully removed!");
			return product;
		} else {
			logger.info("Product not found!");
			return null;
		}
	}

	public synchronized Product editProduct(Product product) {

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

	public synchronized Product searchProduct(double code) {

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

	public synchronized BlockingQueue<Product> getListProduct() {
		return listProduct;
	}

}
