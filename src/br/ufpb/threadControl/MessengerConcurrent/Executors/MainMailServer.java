package br.ufpb.threadControl.MessengerConcurrent.Executors;


import br.ufpb.threadControl.MessengerConcurrent.Controller.Facade;
import br.ufpb.threadControl.MessengerConcurrent.Controller.MailServer;
import br.ufpb.threadControl.MessengerConcurrent.Model.Client;
/**
 * @author Diego Sousa - www.diegosousa.com
 *
 */

public class MainMailServer {
	
	public static void main(String[] args){
		
		MailServer m = new MailServer();
		Facade fachada = Facade.getInstance();
		
		Client client = new Client("Diego","223433","duduwe@nejg",10,11,2011);
		Client client2 = new Client("Diego2","223343","dudduwe@nejg",10,11,2011);
		Client client3 = new Client("Diego3","23332343","dduduwe@nejg",10,11,2011);
		
		fachada.addClient(client);
		fachada.addClient(client2);	
		fachada.addClient(client3);			
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}			
		fachada.getExecutor().execute(m);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		fachada.getExecutor().shutdown();
		
	}

}
