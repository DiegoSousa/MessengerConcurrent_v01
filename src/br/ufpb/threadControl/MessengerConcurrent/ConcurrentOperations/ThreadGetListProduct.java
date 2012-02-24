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

public class ThreadGetListProduct implements Runnable {
	private ProductManager productManager;
	private BlockingQueue<BlockingQueue<Product>> list;

	public ThreadGetListProduct(ProductManager manager,
			BlockingQueue<BlockingQueue<Product>> listProduct) {
		this.productManager = manager;
		this.list = listProduct;
	}

	@Override
	public void run() {
		try {
			list.put(this.productManager.getListProduct());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}