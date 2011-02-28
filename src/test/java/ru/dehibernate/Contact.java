package ru.dehibernate;

import java.util.List;

public class Contact {
	private String name;
	private Company worksIn;
	private List<String> phones;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Company getWorksIn() {
		return worksIn;
	}

	public void setWorksIn(Company worksIn) {
		this.worksIn = worksIn;
	}

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}
}
