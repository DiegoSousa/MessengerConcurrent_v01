/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.ConcurrentOperations;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ClientManager;

/**
 * Adder class of clients
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class ThreadAddClient implements Runnable {
	private ClientManager clientManager;
	private Client client;

	public ThreadAddClient(ClientManager manager, Client client) {
		this.clientManager = manager;
		this.client = client;
	}

	@Override
	public void run() {
		this.clientManager.addClient(client);
	}
}