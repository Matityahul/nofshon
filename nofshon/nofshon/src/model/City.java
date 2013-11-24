package model;

public class City {
	int id;
	int country_id;
	String Name;
	
	

	public City(int id, int country_id, String name)
			{
		super();
		this.id = id;
		this.country_id = country_id;
		this.Name = name;
	}
	
	public int get_id() {
		return id;
	}

	public int get_country_id() {
		return country_id;
	}


	public String getName() {
		return Name;
	}
}
