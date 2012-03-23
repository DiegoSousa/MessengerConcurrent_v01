package br.ufpb.threadControl.MessengerConcurrent.Controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Product;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Promotion;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ManagerClient;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ManagerProduct;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ManagerProductPreferences;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ManagerPromotion;
import br.ufpb.threadControl.MessengerConcurrent.Runnables.RunnableAddClient;
import br.ufpb.threadControl.MessengerConcurrent.Runnables.RunnableAddPreferencesClient;
import br.ufpb.threadControl.MessengerConcurrent.Runnables.RunnableAddProduct;
import br.ufpb.threadControl.MessengerConcurrent.Runnables.RunnableAddPromotion;
import br.ufpb.threadControl.MessengerConcurrent.Runnables.RunnableEditClient;
import br.ufpb.threadControl.MessengerConcurrent.Runnables.RunnableEditProduct;
import br.ufpb.threadControl.MessengerConcurrent.Runnables.RunnableEditPromotion;
import br.ufpb.threadControl.MessengerConcurrent.Runnables.RunnableGetHistoricalOfProductsPurchasedOfAllCustomers;
import br.ufpb.threadControl.MessengerConcurrent.Runnables.RunnableGetHistoricalOfProductsPurchasedOfCustomers;
import br.ufpb.threadControl.MessengerConcurrent.Runnables.RunnableGetListOfClient;
import br.ufpb.threadControl.MessengerConcurrent.Runnables.RunnableGetListOfPreferredProductsOfAllCustomers;
import br.ufpb.threadControl.MessengerConcurrent.Runnables.RunnableGetListOfPreferredProductsOfCustomers;
import br.ufpb.threadControl.MessengerConcurrent.Runnables.RunnableGetListProduct;
import br.ufpb.threadControl.MessengerConcurrent.Runnables.RunnableGetListPromotion;
import br.ufpb.threadControl.MessengerConcurrent.Runnables.RunnablePurchaseProduct;
import br.ufpb.threadControl.MessengerConcurrent.Runnables.RunnableRemoveClient;
import br.ufpb.threadControl.MessengerConcurrent.Runnables.RunnableRemoveHistoricalOfProductsPurchasedOfCustomers;
import br.ufpb.threadControl.MessengerConcurrent.Runnables.RunnableRemovePreferencesClient;
import br.ufpb.threadControl.MessengerConcurrent.Runnables.RunnableRemoveProduct;
import br.ufpb.threadControl.MessengerConcurrent.Runnables.RunnableRemovePromotion;
import br.ufpb.threadControl.MessengerConcurrent.Runnables.RunnableSearchClient;
import br.ufpb.threadControl.MessengerConcurrent.Runnables.RunnableSearchProduct;
import br.ufpb.threadControl.MessengerConcurrent.Runnables.RunnableSearchPromotion;

/**
 * Facade
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 2.0 Copyright (C) 2012 Diego Sousa de Azevedo.
 */

public class Facade{

	private ManagerClient managerClient;
	private ManagerProduct managerProduct;
	private ManagerPromotion managerPromotion;
	private ManagerProductPreferences managerProductPreferences;
	private ExecutorService executor;
	private static Facade facade;

	private Facade() {
		this.managerClient = ManagerClient.getInstance();
		this.managerProduct = ManagerProduct.getInstance();
		this.managerPromotion = ManagerPromotion.getInstance();
		this.managerProductPreferences = ManagerProductPreferences.getInstance();
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

	// ------------------------------------------------------------------
	// methods concurrent CRUD Clients

	public void addClient(Client client) {
		executor.execute(new RunnableAddClient(managerClient, client));
	}

	public void removeClient(Client client) {
		executor.execute(new RunnableRemoveClient(managerClient, client));
	}

	public void editClient(Client client) {
		executor.execute(new RunnableEditClient(managerClient, client));
	}

	public void searchClient(String nome, BlockingQueue<Client> list) {
		executor.execute(new RunnableSearchClient(managerClient, nome, list));
	}

	public void getListOfClient(BlockingQueue<BlockingQueue<Client>> listClient) {
		executor.execute(new RunnableGetListOfClient(managerClient, listClient));
	}

	// -------------------------------------------------------------------------
	// methods concurrent CRUD Products

	public void addProduct(Product product) {
		executor.execute(new RunnableAddProduct(managerProduct, product));
	}

	public void removeProduct(Product product) {
		executor.execute(new RunnableRemoveProduct(managerProduct, product));
	}

	public void editProduct(Product product) {
		executor.execute(new RunnableEditProduct(managerProduct, product));
	}

	public void searchProduct(double codeProduct, BlockingQueue<Product> list) {
		executor.execute(new RunnableSearchProduct(managerProduct, codeProduct,
				list));
	}

	public void getListProduct(BlockingQueue<BlockingQueue<Product>> listProduct) {
		executor.execute(new RunnableGetListProduct(managerProduct, listProduct));
	}

	// -------------------------------------------------------------------------
	// methods concurrent CRUD Promotion

	public void addPromotion(Promotion promotion) {
		executor.execute(new RunnableAddPromotion(managerPromotion, promotion));
	}

	public void removePromotion(Promotion promotion) {
		executor.execute(new RunnableRemovePromotion(managerPromotion, promotion));
	}

	public void editPromotion(Promotion promotion) {
		executor.execute(new RunnableEditPromotion(managerPromotion, promotion));
	}

	public void searchPromotion(double code, BlockingQueue<Promotion> list) {
		executor.execute(new RunnableSearchPromotion(managerPromotion, code, list));
	}

	public void getListPromotion(
			BlockingQueue<BlockingQueue<Promotion>> listPromotion) {
		executor.execute(new RunnableGetListPromotion(managerPromotion,
				listPromotion));
	}

	// -------------------------------------------------------------------------
	// methods concurrent CRUD ProductPreferred

	public void addPreferencesClient(Client client, Product product) {
		executor.execute(new RunnableAddPreferencesClient(
				managerProductPreferences, client, product));
	}

	public void removeAllPreferencesClient(Client client) {
		executor.execute(new RunnableRemovePreferencesClient(
				managerProductPreferences, client));
	}

	public void getListProductPreferredClient(
			BlockingQueue<List<Product>> list, Client client) {
		executor.execute(new RunnableGetListOfPreferredProductsOfCustomers(
				managerProductPreferences, list, client));
	}

	public void getListProductPreferredAllClients(
			BlockingQueue<Map<Client, List<Product>>> list) {
		executor.execute(new RunnableGetListOfPreferredProductsOfAllCustomers(
				managerProductPreferences, list));
	}

	// -------------------------------------------------------------------------
	// methods concurrent of ManagerPurchasesOfProducts

	public void buyProduct(Client client, Product product, int quantity) {
		executor.execute(new RunnablePurchaseProduct(client, product, quantity));
	}	
	
	public void removeHistoricalCustomerPurchase(Client client){
		executor.execute(new RunnableRemoveHistoricalOfProductsPurchasedOfCustomers(client));
	}
	
	public void getHistoricalCustomerPurchase(Client client,
			BlockingQueue<List<Product>> list) {
		executor.execute(new RunnableGetHistoricalOfProductsPurchasedOfCustomers(client, list));
	}
	
	public void getHistoricalPurchasesOfAllCustomer(
			BlockingQueue<Map<Client, List<Product>>> list) {
		executor.execute(new RunnableGetHistoricalOfProductsPurchasedOfAllCustomers(list));
	}
}
