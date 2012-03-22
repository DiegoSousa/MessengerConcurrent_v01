/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.Runnables;

import java.util.concurrent.BlockingQueue;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ManagerClient;

/**
 * Runnable Search Client
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class RunnableSearchClient implements Runnable {
	private ManagerClient clientManager;
	private String nome;
	private BlockingQueue<Client> list;

	public RunnableSearchClient(ManagerClient manager, String nome,
			BlockingQueue<Client> list) {
		this.clientManager = manager;
		this.nome = nome;
		this.list = list;
	}

	@Override
	public void run() {
		try {
			list.put(clientManager.searchClient(nome));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
