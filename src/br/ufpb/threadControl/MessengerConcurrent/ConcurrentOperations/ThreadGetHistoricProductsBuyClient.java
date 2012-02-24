package br.ufpb.threadControl.MessengerConcurrent.ConcurrentOperations;

import java.util.List;
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

public class ThreadGetHistoricProductsBuyClient implements Runnable {

	private ProductBuyManager productBuyManager;
	private BlockingQueue<List<Product>> list;
	private Client client;

	public ThreadGetHistoricProductsBuyClient(Client client,
			BlockingQueue<List<Product>> list) {
		this.client = client;
		this.list = list;
		this.productBuyManager = ProductBuyManager.getInstance();
	}

	@Override
	public void run() {
		list.add(productBuyManager.getHistoricBuyClient(client));
	}

}
