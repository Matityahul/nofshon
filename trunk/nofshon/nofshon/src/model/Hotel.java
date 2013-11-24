package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
	int id;
	int city_id;
	String Name;
	String address;
	String phone;
	int cost;
	
	

	public Hotel(int id, int city_id, String name, String address, String phone, int cost)
			{
		super();
		this.id = id;
		this.city_id = city_id;
		this.Name = name;
		this.address = address;
		this.phone = phone;
		this.cost = cost;
	}
	
	public int get_id() {
		return id;
	}

	public int get_CityID() {
		return city_id;
	}


	public String getName() {
		return Name;
	}
	
	public String get_address() {
		return address;
	}

	public String get_phone() {
		return phone;
	}


	public int get_cost() {
		return cost;
	}
}
