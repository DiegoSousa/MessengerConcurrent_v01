/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.ConcurrentOperations;

import java.util.concurrent.BlockingQueue;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Product;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ProductManager;

/**
 * Description of the class
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class ThreadSearchProduct implements Runnable {
	private ProductManager productManager;
	private double code;
	private BlockingQueue<Product> list;

	public ThreadSearchProduct(ProductManager manager, double code,
			BlockingQueue<Product> list) {
		this.productManager = manager;		
		this.code = code;
		this.list = list;
	}

	@Override
	public void run() {
		try {
			list.put(productManager.searchProduct(code));		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
}
