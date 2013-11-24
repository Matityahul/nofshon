package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class User {
	int id;
	String userName;
	String password;
	Date reg_date;
	String firstName;
	String lastName;
	String address;
	String phone;
	String email;
	
	

	public User(int id, String userName, String password, String address, String phone, Date reg_date, String firstName, String lastName, String email)
			{
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.address = address;
		this.phone = phone;
		this.reg_date = reg_date;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public int get_id() {
		return id;
	}

	public String get_userName() {
		return userName;
	}


	public String get_password() {
		return password;
	}
	
	public String get_address() {
		return address;
	}

	public String get_phone() {
		return phone;
	}


	public Date get_reg_date() {
		return reg_date;
	}
	
	public String get_firstName() {
		return firstName;
	}
	
	public String get_lastName() {
		return lastName;
	}

	public String get_email() {
		return email;
	}

}
