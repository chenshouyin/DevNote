package com.csy.struct2.ognl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionSupport;

public class OgnlAction extends ActionSupport {
	private Cat cat;
	private Map<String, Dog> dogMap = new HashMap<String, Dog>();

	private Set<Dog> dogs = new HashSet<Dog>();

	private String password;

	private User user;
	private String username;

	private List<User> users = new ArrayList<User>();

	public OgnlAction() {
		users.add(new User(1));
		users.add(new User(2));
		users.add(new User(3));

		dogs.add(new Dog("dog1"));
		dogs.add(new Dog("dog2"));
		dogs.add(new Dog("dog3"));
		
		dogMap.put("dog100", new Dog("dog100"));
		dogMap.put("dog101", new Dog("dog101"));
		dogMap.put("dog102", new Dog("dog102"));
		System.out.println("===>OgnlAction");
	}

	public String execute() {
		System.out.println("===>execute");

		return SUCCESS;
	}

	public Cat getCat() {
		System.out.println("===>getCat");

		return cat;
	}
	
	public Map<String, Dog> getDogMap() {
		System.out.println("===>getDogMap");

		return dogMap;
	}

	public Set<Dog> getDogs() {
		System.out.println("===>getDogs");

		return dogs;
	}
	
	public String getPassword() {
		System.out.println("===>getPassword");

		return password;
	}
	
	public User getUser() {
		System.out.println("===>getUser");

		return user;
	}

	public String getUsername() {
		System.out.println("===>getUsername");

		return username;
	}

	public List<User> getUsers() {
		System.out.println("===>getUsers");

		return users;
	}

	public String m() {
		System.out.println("===>m");

		return "hello";
	}

	public void setCat(Cat cat) {
		System.out.println("===>setCat");

		this.cat = cat;
	}
	
	public void setDogMap(Map<String, Dog> dogMap) {
		System.out.println("===>setDogMap");

		this.dogMap = dogMap;
	}

	public void setDogs(Set<Dog> dogs) {
		System.out.println("===>setDogs");

		this.dogs = dogs;
	}

	public void setPassword(String password) {
		System.out.println("===>setPassword");

		this.password = password;
	}

	public void setUser(User user) {
		System.out.println("===>setUser");

		this.user = user;
	}

	public void setUsername(String username) {
		System.out.println("===>setUsername");

		this.username = username;
	}

	public void setUsers(List<User> users) {
		System.out.println("===>setUsers");

		this.users = users;
	}
}
