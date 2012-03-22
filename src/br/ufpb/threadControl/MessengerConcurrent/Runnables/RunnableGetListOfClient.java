package br.ufpb.threadControl.MessengerConcurrent.Runnables;

import java.util.concurrent.BlockingQueue;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ManagerClient;

/**
 * Runnable Get List Of Client.
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class RunnableGetListOfClient implements Runnable {
	private ManagerClient clientManager;
	private BlockingQueue<BlockingQueue<Client>> list;

	public RunnableGetListOfClient(ManagerClient manager,
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