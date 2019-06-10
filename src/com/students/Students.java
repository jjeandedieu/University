package com.students;

public class Students {
	private int id;
	private int classId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;;
	private String address;
	private String grade;
	
	private String username;
	private String password;
	
	
	
	public Students(int id, String firstName, String lastName, String email, String phoneNumber, String address,
			String grade) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.grade = grade;
	}

	public Students(String firstName, String lastName, String email, String phoneNumber, String address, String grade) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.grade = grade;
	}

	
	public Students(int id, String firstName, String lastName, String email, String phoneNumber, String address,
			String grade, String username, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.grade = grade;
		this.username = username;
		this.password = password;
	}

	public Students(String firstName, String lastName, String email, String phoneNumber, String address,
			String grade, String username, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.grade = grade;
		this.username = username;
		this.password = password;
	}

	public Students() {
		// TODO Auto-generated constructor stub
	}

	public Students(int id2, String firstName2, String lastName2, String email2, String phoneNumber2, String address2,
			String grade2, String password2) {
		this.id = id2;
		this.firstName = firstName2;
		this.lastName = lastName2;
		this.email = email2;
		this.phoneNumber = phoneNumber2;
		this.address = address2;
		this.grade = grade2;
		this.password = password2;
	}

	public Students(int classId, int id2,  String firstName2, String lastName2, String email2) {
		this.classId = classId;	
		this.id = id2;
		this.firstName = firstName2;
		this.lastName = lastName2;
		this.email = email2;
		
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
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

	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}

}
