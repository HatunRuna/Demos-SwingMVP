package com.codenough.demos.swingmvp.models;

public class ClientModel {
	private int id;
	private String name;
	private int age;
	private String gender;
	private String email;
	
	public ClientModel() {}
	
	public ClientModel(int id, String name, int age, String gender, String email) {
		this.setId(id);
		this.setName(name);
		this.setAge(age);
		this.setGender(gender);
		this.setEmail(email);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}	
	
	@Override
	public String toString() {
		return this.getName();
	}
}
