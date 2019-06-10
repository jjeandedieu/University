package com.teachers;

public class Teachers {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;;
	private String address;
	private String seniority;
	
	private String username;
	private String password;
	
	

	public Teachers(int id, String firstName, String lastName, String email, String phoneNumber, String address,
			String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.password = password;
	}

	public Teachers(String firstName, String lastName, String email, String phoneNumber, String address, String seniority) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.seniority = seniority;
	}

	
	public Teachers(int id, String firstName, String lastName, String email, String phoneNumber, String address,
			String seniority, String username, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.seniority = seniority;
		this.username = username;
		this.password = password;
	}

	public Teachers(String firstName, String lastName, String email, String phoneNumber, String address,
			String seniority, String username, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.seniority = seniority;
		this.username = username;
		this.password = password;
	}

	public String getSeniority() {
		return seniority;
	}

	public void setSeniority(String seniority) {
		this.seniority = seniority;
	}

	public Teachers() {
		// TODO Auto-generated constructor stub
	}

	public Teachers(int id2, String firstName2, String lastName2, String email2, String phoneNumber2, String address2,
			String seniority, String password2) {
		this.id = id2;
		this.firstName = firstName2;
		this.lastName = lastName2;
		this.email = email2;
		this.phoneNumber = phoneNumber2;
		this.address = address2;
		this.seniority = seniority;
		this.password = password2;
	}

	public Teachers(int id, String firstName, String lastName, String email, String phoneNumber, String address,
			String grade, String seniority, String username, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.seniority = seniority;
		this.seniority = seniority;
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}

}

