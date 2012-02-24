package br.ufpb.threadControl.MessengerConcurrent.ConcurrentOperations;

import java.util.concurrent.BlockingQueue;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ClientManager;

/**
 * Description of the class
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class ThreadGetListOfClient implements Runnable {
	private ClientManager clientManager;
	private BlockingQueue<BlockingQueue<Client>> list;

	public ThreadGetListOfClient(ClientManager manager,
			BlockingQueue<BlockingQueue<Client>> listClient) {
		this.clientManager = manager;
		this.list = listClient;
	}

	@Override
	public void run() {
		try {
			list.put(this.clientManager.getListOfClient());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}