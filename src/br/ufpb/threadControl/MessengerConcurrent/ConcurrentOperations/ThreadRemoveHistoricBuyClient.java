package br.ufpb.threadControl.MessengerConcurrent.ConcurrentOperations;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ProductBuyManager;
/**
 * Description of the class
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class ThreadRemoveHistoricBuyClient implements Runnable {

	private Client client;
	private ProductBuyManager productBuyManager;

	public ThreadRemoveHistoricBuyClient(Client client) {
		this.client = client;
		this.productBuyManager = ProductBuyManager.getInstance();
	}

	public void run() {
		productBuyManager.removeHistoricBuyClient(client);
	}

}
