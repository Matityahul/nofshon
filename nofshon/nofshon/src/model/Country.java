package model;

public class Country {
	int id;
	String Name;
	
	

	public Country(int id, String name)
			{
		super();
		this.id = id;
		this.Name = name;
	}
	
	public int get_id() {
		return id;
	}

	public String getName() {
		return Name;
	}
}
