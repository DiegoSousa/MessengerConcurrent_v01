/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.ConcurrentOperations;

import java.util.logging.Level;
import java.util.logging.Logger;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Product;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ProductBuyManager;

/**
 * Description of the class
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class ThreadBuyProduct implements Runnable {

	private Client client;
	private Product product;
	private int quantityOfProductsToBuy;						
	private ProductBuyManager productBuyManager;	

	public ThreadBuyProduct(Client client, Product product,
			int quantityOfProductsToBuy) {
		
		this.client = client;
		this.product = product;
		this.quantityOfProductsToBuy = quantityOfProductsToBuy;			
		this.productBuyManager= ProductBuyManager.getInstance();		
	}

	public void run() {						
		if ((product != null)
				&& (product.getQuantity() >= quantityOfProductsToBuy)) {
			
			productBuyManager.buyProduct(client, product, quantityOfProductsToBuy);

			Logger.getLogger("Facade").log(Level.INFO,
					"\nSale of the product:\n"+product.toString()+".\n\nTo the client: \n"+client.toString()+"\nCompleted Successfully.\n");
		} else {
			Logger.getLogger("Facade")
					.log(Level.INFO,
							"Product not found or greater than the quantity of product in stock.");
		}
	}
}