/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.Runnables;

import java.util.concurrent.BlockingQueue;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Product;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ManagerProduct;

/**
 * Runnable Get List Product
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class RunnableGetListProduct implements Runnable {
	private ManagerProduct productManager;
	private BlockingQueue<BlockingQueue<Product>> list;

	public RunnableGetListProduct(ManagerProduct manager,
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