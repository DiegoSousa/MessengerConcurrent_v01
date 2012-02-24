package br.ufpb.threadControl.MessengerConcurrent.Controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import br.ufpb.threadControl.MessengerConcurrent.ConcurrentOperations.*;
import br.ufpb.threadControl.MessengerConcurrent.Entity.*;
import br.ufpb.threadControl.MessengerConcurrent.Managers.*;

/**
 * Facade
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 2.0 Copyright (C) 2012 Diego Sousa de Azevedo.
 */

public class Facade {

	private ClientManager clientManager;
	private ProductManager productManager;
	private PromotionManager promotionManager;
	private ProductPreferencesManager productPreferencesManager;
	private ExecutorService executor;
	private static Facade facade;

	private Facade() {
		this.clientManager = ClientManager.getInstance();
		this.productManager = ProductManager.getInstance();
		this.promotionManager = PromotionManager.getInstance();
		this.productPreferencesManager = ProductPreferencesManager.getInstance();
		this.executor = Executors.newFixedThreadPool(6);
	}

	/*
	 * Singleton facade.
	 */

	public static synchronized Facade getInstance() {
		if (facade == null) {
			facade = new Facade();
		}
		return facade;
	}

	public ExecutorService getExecutor() {
		return executor;
	}

	// -------------------------------------------------------------------------
	// methods concurrent CRUD Clients

	public void addClient(Client client) {
		executor.execute(new ThreadAddClient(clientManager, client));
	}

	public void removeClient(Client client) {
		executor.execute(new ThreadRemoveClient(clientManager, client));
	}

	public void editClient(Client client) {
		executor.execute(new ThreadEditClient(clientManager, client));
	}

	public void searchClient(String nome, BlockingQueue<Client> list) {
		executor.execute(new ThreadSearchClient(clientManager, nome, list));
	}

	public void getListOfClient(BlockingQueue<BlockingQueue<Client>> listClient) {
		executor.execute(new ThreadGetListOfClient(clientManager, listClient));
	}

	// -------------------------------------------------------------------------
	// methods concurrent CRUD Products

	public void addProduct(Product product) {
		executor.execute(new ThreadAddProduct(productManager, product));
	}

	public void removeProduct(Product product) {
		executor.execute(new ThreadRemoveProduct(productManager, product));
	}

	public void editProduct(Product product) {
		executor.execute(new ThreadEditProduct(productManager, product));
	}

	public void searchProduct(double codeProduct, BlockingQueue<Product> list) {
		executor.execute(new ThreadSearchProduct(productManager, codeProduct,
				list));
	}

	public void getListProduct(BlockingQueue<BlockingQueue<Product>> listProduct) {
		executor.execute(new ThreadGetListProduct(productManager, listProduct));
	}

	// -------------------------------------------------------------------------
	// methods concurrent CRUD Promotion

	public void addPromotion(Promotion promotion) {
		executor.execute(new ThreadAddPromotion(promotionManager, promotion));
	}

	public void removePromotion(Promotion promotion) {
		executor.execute(new ThreadRemovePromotion(promotionManager, promotion));
	}

	public void editPromotion(Promotion promotion) {
		executor.execute(new ThreadEditPromotion(promotionManager, promotion));
	}

	public void searchPromotion(double code, BlockingQueue<Promotion> list) {
		executor.execute(new ThreadSearchPromotion(promotionManager, code, list));
	}

	public void getListPromotion(
			BlockingQueue<BlockingQueue<Promotion>> listPromotion) {
		executor.execute(new ThreadGetListPromotion(promotionManager,
				listPromotion));
	}

	// -------------------------------------------------------------------------
	// methods concurrent CRUD ProductPreferred

	public void addPreferencesClient(Client client, Product product) {
		executor.execute(new ThreadAddPreferencesClient(
				productPreferencesManager, client, product));
	}

	public void removeAllPreferencesClient(Client client) {
		executor.execute(new ThreadRemovePreferencesClient(
				productPreferencesManager, client));
	}

	public void getListProductPreferredClient(
			BlockingQueue<List<Product>> list, Client client) {
		executor.execute(new ThreadGetListProductPreferencesClient(
				productPreferencesManager, list, client));
	}

	public void getListProductPreferredAllClients(
			BlockingQueue<Map<Client, List<Product>>> list) {
		executor.execute(new ThreadGetListProductPreferencesAllClients(
				productPreferencesManager, list));
	}

	// -------------------------------------------------------------------------
	// methods concurrent of ProductBuyManager

	public void buyProduct(Client client, Product product, int quantity) {
		executor.execute(new ThreadBuyProduct(client, product, quantity));
	}	
	
	public void removeHistoricBuyClient(Client client){
		executor.execute(new ThreadRemoveHistoricBuyClient(client));
	}
	
	public void getHistoricProductsBuyClient(Client client,
			BlockingQueue<List<Product>> list) {
		executor.execute(new ThreadGetHistoricProductsBuyClient(client, list));
	}
	
	public void getHistoricProductsBuyAllClient(
			BlockingQueue<Map<Client, List<Product>>> list) {
		executor.execute(new ThreadGetHistoricProductsBuyAllClient(list));
	}
}