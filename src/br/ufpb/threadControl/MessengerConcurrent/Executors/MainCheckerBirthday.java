package br.ufpb.threadControl.MessengerConcurrent.Executors;

import br.ufpb.threadControl.MessengerConcurrent.Controller.CheckerBirthday;
import br.ufpb.threadControl.MessengerConcurrent.Controller.Facade;
import br.ufpb.threadControl.MessengerConcurrent.Model.Client;

/**
 * @author Diego Sousa - www.diegosousa.com
 *
 */

public class MainCheckerBirthday {
		
	public static void main(){
		Facade fachada = Facade.getInstance();
		CheckerBirthday c = new CheckerBirthday();
		CheckerBirthday c2 = new CheckerBirthday(6,11);
		
		Client client = new Client("Diego2","23432","diego@diego.com",05,11,2011);
		Client client2 = new Client("Diego3","234324","diego@diehgo.com",05,11,2011);
		Client client3 = new Client("Diego4","2d34324","diegoe@diehgo.com",06,11,2011);
		
		fachada.addClient(client);
		fachada.addClient(client2);
		fachada.addClient(client3);
		
		System.out.println("Tamanho da Lista da Fachada  "+fachada.getListClient().size());
		
		fachada.getExecutor().execute(c);
		fachada.getExecutor().execute(c2);
		
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Quant. de Email de Aniversariante do dia 05: "+c.getListClientOfBirthdays().size());
		System.out.println("Quant. de Email de Aniversariante do dia 06: "+c2.getListClientOfBirthdays().size());
		fachada.getExecutor().shutdown();
		
//		try {
//			Thread.sleep(4000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}		
//		
//		fachada.getExecutor().shutdown();
		
//		fachada.addClient(client);
//		fachada.getExecutor().shutdown();
//		try {
//			Thread.sleep(4000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}			
//		new Thread(c).start();		
//		try {
//			Thread.sleep(4000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}	
//		
//		LinkedBlockingQueue<Client> list = c.getListClientAux();
//		try {
//			Thread.sleep(4000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}	
//		System.out.println(list.size());	
//		
	}
}
