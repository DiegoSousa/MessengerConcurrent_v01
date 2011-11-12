package br.ufpb.threadControl.MessengerConcurrent.Model;

/**
 * Promotion Manager
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 0.0.1
 * Copyright (C) 2011 Diego Sousa de Azevedo
 */

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

public class PromotionManager {

	private LinkedBlockingQueue<Promotion> listPromotion = new LinkedBlockingQueue<Promotion>();
	private Logger logger = Logger.getLogger("br.ufpb.threadControl.MessageManager.Model.PromotionManager"); 
	private static PromotionManager promotionManager;
	
	private PromotionManager() {}
	
	/*
	 * Singleton
	 */		
	
	public static PromotionManager getIntance(){
		if(promotionManager.equals(null)){
			promotionManager = new PromotionManager();
			return promotionManager;
		}else{
			return promotionManager;
		}		
	}
	

	public void addPromotion(Promotion promotion) {
		try {
			listPromotion.put(promotion);
			logger.info("Created promotion successfully!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Promotion removePromotion(double code) {
		Promotion promotionAux = locatePromotion(code);
		for (Promotion promotion : listPromotion) {
			if (promotion.getPromotionCode() == promotionAux.getPromotionCode()) {
				listPromotion.remove(promotion);
				logger.info("Promotion successfully Removed !");
				return promotionAux;
			}
		}
		logger.info("Error adding promotion: Promotion is not added!");
		return null;
	}

	public Promotion locatePromotion(double promotionCode) {

		for (Promotion promotion : listPromotion) {
			if (promotion.getPromotionCode() == promotionCode) {
				logger.info("Promotion successfully added !");
				return promotion;
			}
		}
		logger.info("Promotion not found!");
		return null;
	}

	public Promotion editPromotion(Promotion promotion) {

		Promotion promotionAux = locatePromotion(promotion.getPromotionCode());

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

	public LinkedBlockingQueue<Promotion> getListPromotion() {
		return listPromotion;
	}
	
}
