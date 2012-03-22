/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.Runnables;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ManagerClient;

/**
 * Runnable Edit Client
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class RunnableEditClient implements Runnable {
	private ManagerClient clientManager;
	private Client client;

	public RunnableEditClient(ManagerClient manager, Client client) {
		this.clientManager = manager;
		this.client = client;
	}

	@Override
	public void run() {
		this.clientManager.editClient(client);
	}
}