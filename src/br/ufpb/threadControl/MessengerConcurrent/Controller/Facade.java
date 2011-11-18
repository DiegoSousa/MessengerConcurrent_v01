package br.ufpb.threadControl.MessengerConcurrent.Controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.ufpb.threadControl.MessengerConcurrent.Model.Client;
import br.ufpb.threadControl.MessengerConcurrent.Model.ClientManager;
import br.ufpb.threadControl.MessengerConcurrent.Model.Product;
import br.ufpb.threadControl.MessengerConcurrent.Model.ProductManager;
import br.ufpb.threadControl.MessengerConcurrent.Model.ProductPreferredManager;
import br.ufpb.threadControl.MessengerConcurrent.Model.Promotion;
import br.ufpb.threadControl.MessengerConcurrent.Model.PromotionManager;

/**
 * Facade
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 0.0.1 Copyright (C) 2011 Diego Sousa de Azevedo
 */

public class Facade {

	private ClientManager clientManager;
	private ProductManager productManager;
	private PromotionManager promotionManager;
	private ProductPreferredManager productPreferredManager;
	private ExecutorService executor;
	private static Facade facade;

	private Facade() {
		this.clientManager = ClientManager.getInstance();
		this.productManager = ProductManager.getIntance();
		this.promotionManager = PromotionManager.getIntance();
		this.productPreferredManager = ProductPreferredManager.getInstance();
		this.executor = Executors.newFixedThreadPool(3);
	}

	/*
	 * Singleton facade.
	 */

	public static Facade getInstance() {
		if (facade == null) {
			facade = new Facade();
			return facade;
		} else {
			return facade;
		}
	}

	public ExecutorService getExecutor() {
		return executor;
	}

	// -------------------------------------------------------------------------
	// getList Client, Product, Promotion

	public LinkedBlockingQueue<Client> getListClient() {
		ThreadGetListClient threadGetListClient = new ThreadGetListClient(
				clientManager);
		executor.execute(threadGetListClient);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		LinkedBlockingQueue<Client> list = threadGetListClient.getList();
		return list;
	}

	public LinkedBlockingQueue<Product> getListProduct() {
		ThreadGetListProduct threadGetListProduct = new ThreadGetListProduct(
				productManager);
		executor.execute(threadGetListProduct);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		LinkedBlockingQueue<Product> list = threadGetListProduct.getList();
		return list;
	}

	public LinkedBlockingQueue<Promotion> getListPromotion() {
		ThreadGetListPromotion threadGetListPromotion = new ThreadGetListPromotion(
				promotionManager);
		executor.execute(threadGetListPromotion);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		LinkedBlockingQueue<Promotion> list = threadGetListPromotion.getList();
		return list;
	}

	// -------------------------------------------------------------------------
	// methods concurrent CRUD Clients

	public void addClient(Client client) {
		executor.execute(new ThreadAddClient(clientManager, client));
	}

	public void removeClient(String mail) {
		executor.execute(new ThreadRemoveClient(clientManager, mail));
	}

	public void editClient(Client client) {
		executor.execute(new ThreadEditClient(clientManager, client));
	}

	public Client locateClient(String email) {
		ThreadLocateClient threadLocateClient = new ThreadLocateClient(
				clientManager, email);
		executor.execute(threadLocateClient);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Client client = threadLocateClient.getClient();
		return client;
	}

	// -------------------------------------------------------------------------
	// methods concurrent CRUD Products

	public void addProduct(Product product) {
		executor.execute(new ThreadAddProduct(productManager, product));
	}

	public void removeProduct(double code) {
		executor.execute(new ThreadRemoveProduct(productManager, code));
	}

	public void editProduct(Product product) {
		executor.execute(new ThreadEditProduct(productManager, product));
	}

	public Product locateProduct(Client requestClient, double codeProduct) {
		ThreadLocateProduct threadLocateProduct = new ThreadLocateProduct(
				productManager, requestClient, codeProduct);
		executor.execute(threadLocateProduct);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Product product = threadLocateProduct.getProduct();

		return product;
	}

	public boolean buyProduct(Client client, double codeProduct, int quantity) {		
		ThreadBuyProduct threadBuyProduct = new ThreadBuyProduct(client, codeProduct,
				quantity);
		boolean validation;
		
		executor.execute(threadBuyProduct);		
		try{
			Thread.sleep(5000);
		}catch(Exception e){
			e.getMessage();
		}
		
		validation = threadBuyProduct.validation;
		return validation;
	}

	// -------------------------------------------------------------------------
	// methods concurrent CRUD Promotion

	public void addPromotion(Promotion promotion) {
		executor.execute(new ThreadAddPromotion(promotionManager, promotion));
	}

	public void removePromotion(double code) {
		executor.execute(new ThreadRemovePromotion(promotionManager, code));
	}

	public void editPromotion(Promotion promotion) {
		executor.execute(new ThreadEditPromotion(promotionManager, promotion));
	}

	public Promotion locatePromotion(double code) {
		ThreadLocatePromotion threadLocatePromotion = new ThreadLocatePromotion(
				promotionManager, code);
		executor.execute(threadLocatePromotion);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Promotion promotion = threadLocatePromotion.getPromotion();

		return promotion;
	}

	// -------------------------------------------------------------------------
	// Inner class of the concurrent Clients

	class ThreadAddClient implements Runnable {
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

	class ThreadRemoveClient implements Runnable {
		private ClientManager clientManager;
		private String mail;
		Client client;

		public ThreadRemoveClient(ClientManager manager, String mail) {
			this.clientManager = manager;
			this.mail = mail;
		}

		@Override
		public void run() {
			this.client = clientManager.removeClient(mail);
		}

		public Client getClient() {

			return this.client;
		}

	}

	class ThreadEditClient implements Runnable {
		private ClientManager clientManager;
		private Client client;

		public ThreadEditClient(ClientManager manager, Client client) {
			this.clientManager = manager;
			this.client = client;
		}

		@Override
		public void run() {
			this.clientManager.editClient(client);
		}
	}

	class ThreadGetListClient implements Runnable {
		private ClientManager clientManager;
		private LinkedBlockingQueue<Client> list;

		public ThreadGetListClient(ClientManager manager) {
			this.clientManager = manager;
		}

		@Override
		public void run() {
			list = this.clientManager.getListClient();
		}

		public LinkedBlockingQueue<Client> getList() {
			return list;
		}

	}

	class ThreadLocateClient implements Runnable {
		private ClientManager clientManager;
		private String mail;
		private Client client;

		public ThreadLocateClient(ClientManager manager, String mail) {
			this.clientManager = manager;
			this.mail = mail;
		}

		@Override
		public void run() {
			client = clientManager.locateClient(mail);
		}

		public Client getClient() {

			if (this.client != null) {
				return this.client;
			} else {
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return this.client;
			}

		}

	}

	// --------------------------------------------------------------------
	// Inner class of the concurrent Products

	class ThreadAddProduct implements Runnable {
		private ProductManager productManager;
		private Product product;

		public ThreadAddProduct(ProductManager manager, Product product) {
			this.productManager = manager;
			this.product = product;
		}

		@Override
		public void run() {
			this.productManager.addProduct(product);
		}
	}

	class ThreadRemoveProduct implements Runnable {
		private ProductManager productManager;
		private double code;
		Product product;

		public ThreadRemoveProduct(ProductManager manager, double code) {
			this.productManager = manager;
			this.code = code;
		}

		@Override
		public void run() {
			this.product = productManager.removeProduct(code);
		}

		public Product getProduct() {
			return this.product;
		}

	}

	class ThreadEditProduct implements Runnable {
		private ProductManager productManager;
		private Product product;

		public ThreadEditProduct(ProductManager manager, Product product) {
			this.productManager = manager;
			this.product = product;
		}

		@Override
		public void run() {
			this.productManager.editProduct(product);
		}
	}

	class ThreadGetListProduct implements Runnable {
		private ProductManager productManager;
		private LinkedBlockingQueue<Product> list;

		public ThreadGetListProduct(ProductManager manager) {
			this.productManager = manager;
		}

		@Override
		public void run() {
			list = this.productManager.getListProduct();
		}

		public LinkedBlockingQueue<Product> getList() {
			return list;
		}

	}

	class ThreadLocateProduct implements Runnable {
		private ProductManager productManager;
		private double code;
		private Product product;
		private Client client;

		public ThreadLocateProduct(ProductManager manager, Client client,
				double code) {
			this.productManager = manager;
			this.client = client;
			this.code = code;
		}

		@Override
		public void run() {
			this.product = productManager.locateProduct(code);
			client.addListPreference(product);
			productPreferredManager.addPreferencesClient(client);
		}

		public Product getProduct() {

			while (this.product==null) {
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return this.product;
		}
	}

	class ThreadBuyProduct implements Runnable {

		private Client client;
		private double codeProduct;
		private int quantityOfProductsToBuy;
		private boolean validation;

		public ThreadBuyProduct(Client client, double codeProduct,
				int quantityOfProductsToBuy) {
			this.client = client;
			this.codeProduct = codeProduct;
			this.quantityOfProductsToBuy = quantityOfProductsToBuy;
		}

		public void run() {
			Product product = locateProduct(client, codeProduct);

			if (product != null && product.getQuantity() <= quantityOfProductsToBuy) {
				product.setQuantity(product.getQuantity()- quantityOfProductsToBuy);
				this.validation = true;
				Logger.getLogger("Facade").log(Level.INFO,
						"Purchase completed successfully.");
			} else {
				this.validation = false;
			}
		}

		public Boolean getValidationBuy() {
			return validation;
		}
	}

	// --------------------------------------------------------------------
	// Inner class of the concurrent Promotion

	class ThreadAddPromotion implements Runnable {
		private PromotionManager promotionManager;
		private Promotion promotion;

		public ThreadAddPromotion(PromotionManager manager, Promotion promotion) {
			this.promotionManager = manager;
			this.promotion = promotion;
		}

		@Override
		public void run() {
			this.promotionManager.addPromotion(promotion);
		}
	}

	class ThreadRemovePromotion implements Runnable {
		private PromotionManager promotionManager;
		private double code;
		Promotion promotion;

		public ThreadRemovePromotion(PromotionManager manager, double code) {
			this.promotionManager = manager;
			this.code = code;
		}

		@Override
		public void run() {
			this.promotion = promotionManager.removePromotion(code);
		}

		public Promotion getProduct() {
			return this.promotion;
		}

	}

	class ThreadEditPromotion implements Runnable {
		private PromotionManager promotionManager;
		private Promotion promotion;

		public ThreadEditPromotion(PromotionManager manager, Promotion promotion) {
			this.promotionManager = manager;
			this.promotion = promotion;
		}

		@Override
		public void run() {
			this.promotionManager.editPromotion(promotion);
		}
	}

	class ThreadGetListPromotion implements Runnable {
		private PromotionManager promotionManager;
		private LinkedBlockingQueue<Promotion> list;

		public ThreadGetListPromotion(PromotionManager manager) {
			this.promotionManager = manager;
		}

		@Override
		public void run() {
			list = promotionManager.getListPromotion();
		}

		public LinkedBlockingQueue<Promotion> getList() {
			return list;
		}

	}

	class ThreadLocatePromotion implements Runnable {
		private PromotionManager promotionManager;
		private double code;
		private Promotion promotion;

		public ThreadLocatePromotion(PromotionManager manager, double code) {
			this.promotionManager = manager;
			this.code = code;
		}

		@Override
		public void run() {
			this.promotion = promotionManager.locatePromotion(code);
		}

		public Promotion getPromotion() {

			if (this.promotion != null) {
				return this.promotion;
			} else {
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return this.promotion;
			}

		}
	}
}
