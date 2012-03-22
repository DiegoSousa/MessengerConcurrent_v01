/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.Runnables;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Product;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ManagerProduct;

/**
 * Runnable Remove Product
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0
 * Copyright (C) 2012 Diego Sousa de Azevedo
 */
public class RunnableRemoveProduct implements Runnable {
	private ManagerProduct productManager;
	private Product product;

	public RunnableRemoveProduct(ManagerProduct manager, Product product) {
		this.productManager = manager;	
		this.product = product;
	}

	@Override
	public void run() {
		productManager.removeProduct(product);
	}
}
