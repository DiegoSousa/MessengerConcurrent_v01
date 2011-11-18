package br.ufpb.threadControl.MessengerConcurrent.Model;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

/**
 * Client Manager
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 0.0.1
 * Copyright (C) 2011 Diego Sousa de Azevedo
 */

public class ClientManager {

	private LinkedBlockingQueue<Client> listClient;
	private Logger logger;
	private static ClientManager clientManager;
	
	private ClientManager() {
		this.listClient = new LinkedBlockingQueue<Client>();
		this.logger = Logger.getLogger("br.ufpb.threadControl.birthdayMessage.Model.ClientManager");
	}
	
	/*
	 * Singleton
	 */
	
	public static ClientManager getInstance(){
		if(clientManager == null){
			clientManager = new ClientManager();
			return clientManager;			
		}else{
			return clientManager;
		}				
	}
		
	public void addClient(Client client) {
		try {
			listClient.put(client);
			logger.info("Client: " + client.getName() + " successfully added!");
		} catch (InterruptedException e) {
			e.getMessage();
		} catch (NullPointerException e) {
			e.getMessage();
		}
	}

	public Client removeClient(String mail) {
		Client clientAux = locateClient(mail);

		if (clientAux != null) {
			listClient.remove(clientAux);
			logger.info(clientAux.getMail() + " successfully removed!");
			return clientAux;
		} else {
			return null;
		}
	}

	public Client editClient(Client client) {

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
		return null;
	}

	public Client locateClient(String mail) {

		for (Client client : listClient) {
			if (client.getMail().equals(mail)) {
				logger.info("Client: "+ client.getName() + " found!");
				return client;
			}
		}
		logger.info("Client not found!");
		return null;
	}

	public LinkedBlockingQueue<Client> getListClient() {
		return listClient;
	}
}
