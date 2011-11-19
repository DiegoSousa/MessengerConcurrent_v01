package br.ufpb.threadControl.MessengerConcurrent.Model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Entity Client
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 0.0.1
 * Copyright (C) 2011 Diego Sousa de Azevedo
 */

public class Client {

	private String name;
	private String phone;
	private String mail;
	private Calendar calendar;
	private int birthday;
	private int monthOfBirth;
	private int yearOfbirth;
	private LinkedBlockingQueue<Product> listProductPreferred;

	public Client(String name, String phone, String mail, int birthday,
			int monthOfBirth, int yearOfbirth) {
		this.name = name;
		this.phone = phone;
		this.mail = mail;
		this.calendar = new GregorianCalendar();
		this.calendar.set(yearOfbirth, monthOfBirth, birthday);
		this.listProductPreferred = new LinkedBlockingQueue<Product>();
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getMail() {
		return mail;
	}

	public int getBirthday() {
		return calendar.get(GregorianCalendar.DATE);
	}

	public int getMonthOfBirth() {
		return calendar.get(GregorianCalendar.MONTH);
	}

	public int getYearOfbirth() {
		return calendar.get(GregorianCalendar.YEAR);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setBirthday(int birthday) {
		calendar.set(Calendar.DATE, birthday);
	}

	public void setMonthOfBirth(int monthOfBirth) {
		calendar.set(Calendar.MONTH, monthOfBirth);
	}

	public void setYearOfbirth(int yearOfbirth) {
		calendar.set(Calendar.YEAR, yearOfbirth);
	}

	public void addListPreference(Product product) {
		try {
			listProductPreferred.put(product);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public LinkedBlockingQueue<Product> getListPreference() {
		return listProductPreferred;
	}

	public String toString() {
		return "Name: " + name + "\nPhone: " + phone + "\nMail: " + mail
				+ "\nDate of birth: " + birthday + "/" + monthOfBirth + "/"
				+ yearOfbirth;
	}
}
