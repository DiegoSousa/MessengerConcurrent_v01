package br.ufpb.threadControl.MessengerConcurrent.Executors;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

import br.ufpb.threadControl.MessengerConcurrent.Controller.Facade;
import br.ufpb.threadControl.MessengerConcurrent.Model.Client;
import br.ufpb.threadControl.MessengerConcurrent.Model.Product;
import br.ufpb.threadControl.MessengerConcurrent.Model.ProductPreferredManager;

/**
 * @author Diego Sousa - www.diegosousa.com
 *
 */


public class MainCookie {

	public static void main(String[] args){
	
		Client client = new Client("Diego","2222","kfwnsjfn@non",11,4,11);		
		Facade fachada = Facade.getInstance();
		ProductPreferredManager cookie = ProductPreferredManager.getInstance();
		HashMap<Client, LinkedBlockingQueue<Product>> list;
		Product product = new Product("Arroz", 222,5,3);	
		
		fachada.addProduct(product);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		fachada.addClient(client);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		fachada.locateProduct(client, 222);
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		list = cookie.getListPreference();		
				
		System.out.println(list.toString());

	
	}
	
}
