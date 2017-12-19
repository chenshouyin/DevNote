package com.csy.struct2.tags;

public class Dog {
	
	private String name;
	
	public Dog() {
		
	}

	public Dog(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "dog: " + name;
	}
}
