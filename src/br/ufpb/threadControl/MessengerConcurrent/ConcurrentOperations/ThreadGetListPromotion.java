/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.ConcurrentOperations;

import java.util.concurrent.BlockingQueue;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Promotion;
import br.ufpb.threadControl.MessengerConcurrent.Managers.PromotionManager;

/**
 * Description of the class
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class ThreadGetListPromotion implements Runnable {
	private PromotionManager promotionManager;
	private BlockingQueue<BlockingQueue<Promotion>> list;

	public ThreadGetListPromotion(PromotionManager manager,
			BlockingQueue<BlockingQueue<Promotion>> listPromotion) {
		this.promotionManager = manager;
		this.list = listPromotion;
	}

	@Override
	public void run() {
		try {
			list.put(promotionManager.getListPromotion());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}