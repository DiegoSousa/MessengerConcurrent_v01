package br.ufpb.threadControl.MessengerConcurrent.Managers;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;

/**
 * Client Manager
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 2.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class ClientManager {

	private static ClientManager clientManager;
	private ProductPreferencesManager productPreferencesManager;
	private ProductBuyManager productBuyManager;
	private BlockingQueue<Client> listClient;
	private Logger logger;
	
	private ClientManager() {
		this.productPreferencesManager = ProductPreferencesManager.getInstance();
		this.productBuyManager = ProductBuyManager.getInstance();
		this.listClient = new LinkedBlockingQueue<Client>();
		this.logger = Logger
				.getLogger("br.ufpb.threadControl.birthdayMessage.Model.ClientManager");		
	}

	/*
	 * Singleton
	 */

	public static synchronized ClientManager getInstance() {
		if (clientManager == null) {
			clientManager = new ClientManager();
		}
		return clientManager;
	}

	public Client addClient(Client client) {
		if (client != null) {
			try {
				listClient.put(client);
				logger.info("Client: " + client.getName()
						+ " successfully added!");
			} catch (InterruptedException e) {
				e.getMessage();
			} catch (NullPointerException e) {
				e.getMessage();
			}
			return client;
		} else {
			return null;
		}
	}

	public Client removeClient(Client client) {

		if (client != null) {
			listClient.remove(client);
			productPreferencesManager.removeAllPreferencesClient(client);
			productBuyManager.removeHistoricBuyClient(client);
			logger.info(client.getMail() + " successfully removed!");
			return client;
		} else {
			return null;
		}
	}

	public Client editClient(Client client) {
		if (client != null) {
			for (Client clientAux : listClient) {

				if (clientAux.getMail().equals(client.getMail())) {
					listClient.remove(clientAux);					
					try {
						listClient.put(client);						
					} catch (InterruptedException e) {
						e.getMessage();
					} catch (NullPointerException e) {
						e.getMessage();
					}
					logger.info("Client: " + client.getName() + " atualizado!");
					return clientAux;
				}
			}
		}
		return null;
	}

	public Client searchClient(String nome) {

		for (Client client : listClient) {
			if (client.getName().equals(nome)) {
				logger.info("Client: " + client.getName() + " found!");
				return client;
			}
		}
		logger.info("Client not found!");
		return null;
	}

	public synchronized BlockingQueue<Client> getListOfClient() {
		return listClient;
	}
}
