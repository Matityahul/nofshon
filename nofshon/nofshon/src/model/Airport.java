package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Airport {
	int id;
	int city_id;
	String Name;
	
	

	public Airport(int id, int city_id, String name)
			{
		super();
		this.id = id;
		this.city_id = city_id;
		this.Name = name;
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
}
