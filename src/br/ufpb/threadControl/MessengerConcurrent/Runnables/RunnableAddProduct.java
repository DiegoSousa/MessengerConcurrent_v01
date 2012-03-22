/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.Runnables;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Product;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ManagerProduct;

/**
 * Runnable Adder Product
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class RunnableAddProduct implements Runnable {
	private ManagerProduct productManager;
	private Product product;

	public RunnableAddProduct(ManagerProduct manager, Product product) {
		this.productManager = manager;
		this.product = product;
	}

	@Override
	public void run() {
		this.productManager.addProduct(product);
	}
}
