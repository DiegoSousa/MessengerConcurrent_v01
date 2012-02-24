/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.ConcurrentOperations;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Product;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ProductManager;

/**
 * Description of the class
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0
 * Copyright (C) 2012 Diego Sousa de Azevedo
 */
public class ThreadRemoveProduct implements Runnable {
	private ProductManager productManager;
	private Product product;

	public ThreadRemoveProduct(ProductManager manager, Product product) {
		this.productManager = manager;	
		this.product = product;
	}

	@Override
	public void run() {
		productManager.removeProduct(product);
	}
}
