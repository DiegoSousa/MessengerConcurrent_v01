package br.ufpb.threadControl.MessengerConcurrent.Managers;

/**
 * Promotion Manager
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 2.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Product;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Promotion;

public class ManagerPromotion {

	private static ManagerPromotion promotionManager;
	private BlockingQueue<Promotion> listPromotion;
	private Logger logger;

	private ManagerPromotion() {
		this.listPromotion = new LinkedBlockingQueue<Promotion>();
		this.logger = Logger
				.getLogger("br.ufpb.threadControl.MessageManager.Model.PromotionManager");
	}

	/*
	 * Singleton
	 */

	public static synchronized ManagerPromotion getInstance() {
		if (promotionManager == null) {
			promotionManager = new ManagerPromotion();
		}
		return promotionManager;
	}

	public synchronized Promotion addPromotion(Promotion promotion) {
		if (promotion != null) {
			try {
				listPromotion.put(promotion);
				logger.info("Created promotion successfully! Promotion Code: "
						+ promotion.getPromotionCode());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return promotion;
		} else {
			return null;
		}
	}

	public synchronized Promotion removePromotion(Promotion promotion) {
		if (promotion != null) {
			for (Promotion promotionAux : listPromotion) {
				if (promotionAux.getPromotionCode() == promotion
						.getPromotionCode()) {
					listPromotion.remove(promotionAux);
					logger.info("Promotion successfully Removed! Promotion Code: "
							+ promotionAux.getPromotionCode());
					return promotionAux;
				}
			}
		}
		logger.info("Error removing promotion: Promotion is not added!");
		return null;
	}

	public synchronized Promotion editPromotion(Promotion promotion) {

		Promotion promotionAux = searchPromotion(promotion.getPromotionCode());

		if (promotionAux != null) {
			listPromotion.remove(promotionAux);
			listPromotion.add(promotion);
			logger.info("Updated with success Promotion!");
			return promotion;
		} else {
			logger.info("Error updating promotion: Promotion has not been updated!");
			return null;
		}
	}

	public synchronized Promotion searchPromotion(double promotionCode) {

		for (Promotion promotion : listPromotion) {
			if (promotion.getPromotionCode() == promotionCode) {
				return promotion;
			}
		}
		logger.info("Promotion not found!");
		return null;
	}

	public synchronized Promotion searchPromotion(Product product) {

		for (Promotion promotion : listPromotion) {
			if (promotion.getProduct() == product) {
				return promotion;
			}
		}
		logger.info("Promotion not found!");
		return null;
	}

	public synchronized BlockingQueue<Promotion> getListPromotion() {
		return listPromotion;
	}

}
