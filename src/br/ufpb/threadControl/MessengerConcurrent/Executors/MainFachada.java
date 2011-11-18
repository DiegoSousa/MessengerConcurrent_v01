package br.ufpb.threadControl.MessengerConcurrent.Executors;

import br.ufpb.threadControl.MessengerConcurrent.Controller.Facade;
import br.ufpb.threadControl.MessengerConcurrent.Model.Client;
import br.ufpb.threadControl.MessengerConcurrent.Model.Product;

/**
 * @author Diego Sousa - www.diegosousa.com
 *
 */
public class MainFachada {
	
	public static void main () throws InterruptedException{
		
		Facade facade = Facade.getInstance();
//----------------------------------------------------		
//Criação de Objetos Client
		
		Client client1 = new Client("Diego", "111", "diego.sousa@dce.ufpb.br", 28, 10, 1988);
		Client client2 = new Client("Ayla", "222", "ayla@dce.ufpb.br", 28, 10, 1988);
		Client client3 = new Client("Kawe", "333", "kawe.ramon@dce.ufpb.br", 28, 10, 1988);
		//Client client4 = new Client("Rodrigo", "333", "rodrigor@dce.ufpb.br", 29, 10, 1988);

//----------------------------------------------------	
//Criação da thread getListClient()
		
		System.out.println(facade.getListClient().size());
//----------------------------------------------------	
//Criação de 3 thread addClient()		
		
		Thread.sleep(4000);	
		facade.addClient(client1);
		facade.addClient(client2);	
		facade.addClient(client3);				
		
//----------------------------------------------------	
//Criação de 2 thread editClient()				
		
		Thread.sleep(4000);		
		client2.setName("Thiago");
		client1.setName("Maria");
		facade.editClient(client2);
		facade.editClient(client1);
		
//----------------------------------------------------	
//Criação novamente de 2 thread getListClient()		
		Thread.sleep(4000);
		System.out.println(facade.getListClient().size());		
		System.out.println(facade.getListClient().toString());	
				
//----------------------------------------------------	
//Criação de 1 thread removeClient()
		Thread.sleep(4000);
		facade.removeClient("diego.sousa@dce.ufpb.br");

//----------------------------------------------------	
//Criação de 1 thread getListClient()
		Thread.sleep(4000);
		System.out.println(facade.getListClient().size());
		
//----------------------------------------------------	
//Criação de 2 thread locateClient()		
		Thread.sleep(4000);
		Client client = facade.locateClient("kawe.ramon@dce.ufpb.br");						
		Client client6 = facade.locateClient("ayla@dce.ufpb.br");
		
		Thread.sleep(4000);
		System.out.println(client.getName());
		System.out.println(client6.getName());
		
		facade.getExecutor().shutdown();
	
//----------------------------------------------------	
//Criação de 2 compras
		
		Product product1 = new Product("Arroz", 123, 15.00, 2);
		facade.addProduct(product1);
		facade.buyProduct(client6, 123, 1);
		
	}
}