/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ufpb.threadControl.MessengerConcurrent.Controller.Facade;
import br.ufpb.threadControl.MessengerConcurrent.Model.Client;
import br.ufpb.threadControl.MessengerConcurrent.Model.Product;
import br.ufpb.threadControl.MessengerConcurrent.Model.Promotion;

/**
 * Test case in the facade class
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 0.0.1 Copyright (C) 2011 Diego Sousa de Azevedo
 */
public class FacadeTest {

	public static Facade facade = Facade.getInstance();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Starting the test facade class...");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Finished the test facade class!");
	}

	@Before
	public void setUp() throws Exception {			
	}

	@After
	public void tearDown() throws Exception {		
		facade.getListClient().clear();
		facade.getListProduct().clear();	
		facade.getListPromotion().clear();
		facade.getListProductPreferred().clear();
	}

	@Test
	public void testGetInstance() {
		assertNotNull(Facade.getInstance());
	}

	@Test
	public void testGetExecutor() {
		assertNotNull(facade.getExecutor());
	}

	@Test
	public void testGetListClient() {
		Client client1 = new Client("Diego", "111", "diego.sousa@dce.ufpb.br",
				18, 11, 1988);
		Client client2 = new Client("Ayla", "222", "ayla@dce.ufpb.br", 18, 11,
				1988);
		Client client3 = new Client("Kawe", "333", "kawe.ramon@dce.ufpb.br",
				18, 11, 1988);

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(0, facade.getListClient().size());

		facade.addClient(client1);
		facade.addClient(client2);
		facade.addClient(client3);

		assertEquals(3, facade.getListClient().size());

	}

	@Test
	public void testGetListProduct() {
		Product product = new Product("Arroz", 1234, 2.30, 100);
		Product product2 = new Product("Feijão", 4321, 3.00, 100);
		Product product3 = new Product("Bolo", 1334, 1.20, 100);

		assertEquals(0, facade.getListProduct().size());

		facade.addProduct(product);
		facade.addProduct(product2);
		facade.addProduct(product3);

		assertEquals(3, facade.getListProduct().size());

	}

	@Test
	public void testGetListPromotion() {
		Product arroz = new Product("Arroz", 1234, 2.30, 100);
		Product feijao = new Product("Feijão", 4321, 3.00, 100);
		Product bolo = new Product("Bolo", 1334, 1.20, 100);

		facade.addProduct(arroz);
		facade.addProduct(feijao);
		facade.addProduct(bolo);

		Promotion promotion = new Promotion(arroz, 1.00, 1245);
		Promotion promotion2 = new Promotion(feijao, 1.00, 1246);
		Promotion promotion3 = new Promotion(feijao, 1.00, 1247);

		assertEquals(0, facade.getListPromotion().size());

		facade.addPromotion(promotion);
		facade.addPromotion(promotion2);
		facade.addPromotion(promotion3);

		assertEquals(3, facade.getListPromotion().size());
	}
	
	
	@Test
	public void testGetListProductPreferred() {
		Product arroz = new Product("Arroz", 1234, 2.30, 100);
		Product feijao = new Product("Feijão", 4321, 3.00, 100);	
		
		facade.addProduct(arroz);
		facade.addProduct(feijao);
		
		Client client1 = new Client("Diego", "111", "diego.sousa@dce.ufpb.br",
				18, 11, 1988);
		Client client2 = new Client("Ayla", "222", "ayla@dce.ufpb.br", 18, 11,
				1988);
		facade.addClient(client1);
		facade.addClient(client2);
		
		facade.locateProduct(client1, 1234);
		facade.buyProduct(client1, 1234, 2);
		facade.locateProduct(client2, 1234);
		
		assertEquals(2,facade.getListProductPreferred().size());
		assertFalse(facade.getListProductPreferred().isEmpty());	
		
	}


	@Test
	public void testAddClient() {

		Client client1 = new Client("Diego", "111", "diego.sousa@dce.ufpb.br",
				18, 11, 1988);
		Client client2 = new Client("Ayla", "222", "ayla@dce.ufpb.br", 18, 11,
				1988);
		Client client3 = new Client("Kawe", "333", "kawe.ramon@dce.ufpb.br",
				18, 11, 1988);

		facade.addClient(client1);
		facade.addClient(client3);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertTrue(facade.getListClient().contains(client1));
		assertFalse(facade.getListClient().contains(client2));
		assertEquals("Diego", facade.locateClient("diego.sousa@dce.ufpb.br")
				.getName());

	}

	@Test
	public void testRemoveClient() {

		Client client1 = new Client("Diego", "111", "diego.sousa@dce.ufpb.br",
				18, 11, 1988);
		Client client3 = new Client("Kawe", "333", "kawe.ramon@dce.ufpb.br",
				18, 11, 1988);

		facade.addClient(client1);
		facade.addClient(client3);

		assertTrue(facade.getListClient().contains(client1));
		assertEquals(2, facade.getListClient().size());

		facade.removeClient("diego.sousa@dce.ufpb.br");

		assertEquals(1, facade.getListClient().size());
		assertFalse(facade.getListClient().contains(client1));

		facade.removeClient("kawe.ramon@dce.ufpb.br");
	}

	@Test
	public void testEditClient() {

		Client client1 = new Client("Diego", "111", "diego.sousa@dce.ufpb.br",
				18, 11, 1988);

		facade.addClient(client1);
		assertEquals("Diego", client1.getName());
		client1.setName("João");
		facade.editClient(client1);
		assertEquals("João", client1.getName());

	}

	@Test
	public void testLocateClient() {
		Client client1 = new Client("Diego", "111", "diego.sousa@dce.ufpb.br",
				18, 11, 1988);
		facade.addClient(client1);

		assertTrue(facade.getListClient().contains(client1));

		assertNotNull(facade.locateClient("diego.sousa@dce.ufpb.br"));

	}

	@Test
	public void testAddProduct() {
	
		Product product = new Product("Arroz", 1234, 2.30, 100);
		Product product2 = new Product("Feijão", 4321, 3.00, 100);
		Product product3 = new Product("Bolo", 1334, 1.20, 100);
		
		assertEquals(0, facade.getListProduct().size());

		facade.addProduct(product);
		facade.addProduct(product2);
		facade.addProduct(product3);

		assertEquals(3, facade.getListProduct().size());
		
		assertTrue(facade.getListProduct().contains(product));
			
	}

	@Test
	public void testRemoveProduct() {
		
		Product product = new Product("Arroz", 1234, 2.30, 100);
		Product product2 = new Product("Feijão", 4321, 3.00, 100);
		Product product3 = new Product("Bolo", 1334, 1.20, 100);
		
		assertEquals(0, facade.getListProduct().size());

		facade.addProduct(product);
		facade.addProduct(product2);
		facade.addProduct(product3);

		assertEquals(3, facade.getListProduct().size());			

		facade.removeProduct(1234);
		facade.removeProduct(4321);
		facade.removeProduct(1334);
		
		assertEquals(0, facade.getListProduct().size());
		
	}

	@Test
	public void testEditProduct() {
		
		Product product = new Product("Arroz", 1234, 2.30, 100);		
		
		facade.addProduct(product);
		assertTrue(product.getPrice() == 2.30);
		
		product.setPrice(2.45);
		facade.editProduct(product);
		assertTrue(facade.getListProduct().contains(product));
		assertTrue(product.getPrice() == 2.45);
		
	}

	@Test
	public void testLocateProduct() {
	
		Product product = new Product("Arroz", 1234, 2.30, 100);		
		Client client = new Client("Diego", "3423-1435", "bisneto@dce.ufpb.br", 25, 11, 1988);
		
		facade.addProduct(product);		
			
		assertEquals(product, facade.locateProduct(client, 1234));
		assertTrue(facade.locateProduct(client, 1234).getName().equalsIgnoreCase("Arroz"));
					
	}

	@Test
	public void testBuyProduct() {
	
		Product product = new Product("Arroz", 1234, 2.30, 100);
		Client client = new Client("Diego", "3423-1435", "bisneto@dce.ufpb.br", 25, 11, 1988);		
		
		facade.addClient(client);
		facade.addProduct(product);

		//By locating a product, it is inserted in the list of products customers want
		assertTrue(facade.locateProduct(client, 1234).getQuantity() == 100);  
		//When buying a product, it is inserted in the list of products customers want
		facade.buyProduct(client, 1234, 10);
		
		assertFalse(facade.locateProduct(client, 1234).getQuantity() == 100);
		assertTrue(facade.locateProduct(client, 1234).getQuantity() == 90);
	}

	@Test
	public void testAddPromotion() {
		
		Product arroz = new Product("Arroz", 1234, 2.30, 100);
		Product feijao = new Product("Feijão", 4321, 3.00, 100);
		Product bolo = new Product("Bolo", 1334, 1.20, 100);

		facade.addProduct(arroz);
		facade.addProduct(feijao);
		facade.addProduct(bolo);

		Promotion promotion = new Promotion(arroz, 1.00, 1245);
		Promotion promotion2 = new Promotion(feijao, 1.00, 1246);
		Promotion promotion3 = new Promotion(feijao, 1.00, 1247);

		assertEquals(0, facade.getListPromotion().size());

		facade.addPromotion(promotion);
		facade.addPromotion(promotion2);
		facade.addPromotion(promotion3);

		assertEquals(3, facade.getListPromotion().size());
		
	}

	@Test
	public void testRemovePromotion() {
		
		Product arroz = new Product("Arroz", 1234, 2.30, 100);
		Product feijao = new Product("Feijão", 4321, 3.00, 100);
		Product bolo = new Product("Bolo", 1334, 1.20, 100);

		facade.addProduct(arroz);
		facade.addProduct(feijao);
		facade.addProduct(bolo);

		Promotion promotion = new Promotion(arroz, 1.00, 1245);
		Promotion promotion2 = new Promotion(feijao, 1.00, 1246);
		Promotion promotion3 = new Promotion(feijao, 1.00, 1247);

		facade.addPromotion(promotion);
		facade.addPromotion(promotion2);
		facade.addPromotion(promotion3);

		assertEquals(3, facade.getListPromotion().size());

		facade.removePromotion(1245);
		facade.removePromotion(1246);
		facade.removePromotion(1247);
				
		assertEquals(0, facade.getListPromotion().size());
		assertFalse(facade.getListPromotion().contains(promotion));
		
		facade.removeProduct(1234);
		facade.removeProduct(4321);
		facade.removeProduct(1334);

	}

	@Test
	public void testEditPromotion() {

		Product arroz = new Product("Arroz", 1234, 2.30, 100);
		Promotion promotion = new Promotion(arroz, 1.00, 1245);
		
		facade.addProduct(arroz);		
		facade.addPromotion(promotion);
	
		assertEquals(1, facade.getListPromotion().size());
		assertTrue(facade.getListPromotion().contains(promotion));
		assertTrue(facade.locatePromotion(1245).getDiscountedPrice()==1.00);
		
		promotion.setDiscountedPrice(1.20);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		facade.editPromotion(promotion);	
	
		assertTrue(facade.locatePromotion(1245).getDiscountedPrice()==1.20);
		assertFalse(facade.locatePromotion(1245).getDiscountedPrice()==1.00);
		
	}

	@Test
	public void testLocatePromotion() {
		
		Product arroz = new Product("Arroz", 1234, 2.30, 100);
		Promotion promotion = new Promotion(arroz, 1.00, 1245);
		
		facade.addProduct(arroz);		
		facade.addPromotion(promotion);
			
		assertTrue(facade.locatePromotion(1245).getDiscountedPrice()==1.00);
		assertNull(facade.locatePromotion(1342));
	}
	
	@Test
	public void testAddPreferencesClient(){
				
		Product arroz = new Product("Arroz", 1234, 2.30, 100);
		Product feijao = new Product("Feijão", 4321, 3.00, 100);	
		
		facade.addProduct(arroz);
		facade.addProduct(feijao);
		
		Client client1 = new Client("Diego", "111", "diego.sousa@dce.ufpb.br",
				18, 11, 1988);
			
				
		facade.addClient(client1);		
		
		client1.addListPreference(arroz);
		client1.addListPreference(feijao);
		
		facade.addPreferencesClient(client1);
		
		assertTrue(facade.getListProductPreferred().containsKey(client1));
		assertEquals(1,facade.getListProductPreferred().size());
		
	}
	
	
	@Test
	public void testremovePreferencesClient(){
		
		Product arroz = new Product("Arroz", 1234, 2.30, 100);
		Product feijao = new Product("Feijão", 4321, 3.00, 100);	
		
		facade.addProduct(arroz);
		facade.addProduct(feijao);
		
		Client client1 = new Client("Diego", "111", "diego.sousa@dce.ufpb.br",
				18, 11, 1988);
							
		facade.addClient(client1);		
		
		client1.addListPreference(arroz);
		client1.addListPreference(feijao);
		
		facade.addPreferencesClient(client1);
		
		assertTrue(facade.getListProductPreferred().containsKey(client1));
		assertEquals(1,facade.getListProductPreferred().size());
		
		facade.removePreferencesClient(client1);
		
		assertFalse(facade.getListProductPreferred().containsKey(client1));
		assertEquals(0,facade.getListProductPreferred().size());
		
	}
	
}
