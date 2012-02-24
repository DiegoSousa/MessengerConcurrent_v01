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

public class ThreadSearchPromotion implements Runnable {
	private PromotionManager promotionManager;
	private double code;
	private BlockingQueue<Promotion> list;

	public ThreadSearchPromotion(PromotionManager manager, double code, BlockingQueue<Promotion> list) {
		this.promotionManager = manager;
		this.code = code;
		this.list = list;
	}

	@Override
	public void run() {
		try {
			list.put(promotionManager.searchPromotion(code));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
