/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.Runnables;

import java.util.concurrent.BlockingQueue;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Promotion;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ManagerPromotion;

/**
 * Runnable Search Promotion
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class RunnableSearchPromotion implements Runnable {
	private ManagerPromotion promotionManager;
	private double code;
	private BlockingQueue<Promotion> list;

	public RunnableSearchPromotion(ManagerPromotion manager, double code, BlockingQueue<Promotion> list) {
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
