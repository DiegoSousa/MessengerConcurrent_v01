/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.Runnables;

import java.util.concurrent.BlockingQueue;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Promotion;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ManagerPromotion;

/**
 * Runnable Get List Promotion
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class RunnableGetListPromotion implements Runnable {
	private ManagerPromotion promotionManager;
	private BlockingQueue<BlockingQueue<Promotion>> list;

	public RunnableGetListPromotion(ManagerPromotion manager,
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