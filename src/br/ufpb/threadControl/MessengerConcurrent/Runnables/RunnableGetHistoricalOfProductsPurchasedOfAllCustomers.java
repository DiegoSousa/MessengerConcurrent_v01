/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.ConcurrentOperations;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Product;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ProductBuyManager;

/**
 * Description of the class
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class ThreadGetHistoricProductsBuyAllClient implements Runnable {

	private ProductBuyManager productBuyManager;
	private BlockingQueue<Map<Client, List<Product>>> list;	

	public ThreadGetHistoricProductsBuyAllClient(
			BlockingQueue<Map<Client, List<Product>>> list) {
		this.productBuyManager = ProductBuyManager.getInstance();
		this.list = list;
	}

	@Override
	public void run() {
		try {
			list.put(productBuyManager.getHistoricBuyAllCLient());
		} catch (InterruptedException e) {
			e.getMessage();
		}
	}
}