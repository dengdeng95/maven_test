package com.dhf.model;

import java.io.Serializable;

public class User implements Serializable{
	private int id;
	private String name;
	private String age;
	private String card;
	private String password;

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAge() {
		return age;
	}

	public String getCard() {
		return card;
	}

	public String getPassword() {
		return password;
	}
}
