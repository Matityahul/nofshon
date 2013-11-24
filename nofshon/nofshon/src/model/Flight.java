package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Flight {
	int id;
	Date departure_time;
	Date arrival_time;
	int airline_id;
	int cost;
	int from_airport;
	int to_airport;
	
	
	

	public Flight(int id, int airline_id, int cost,
			int from_airport, int to_airport, Date departure_time,
			Date arrival_time) {
		super();
		this.id = id;
		this.airline_id = airline_id;
		this.cost = cost;
		this.from_airport = from_airport;
		this.to_airport = to_airport;
		this.departure_time = departure_time;
		this.arrival_time = arrival_time;
	}
	
	public int get_id() {
		return id;
	}


	public int getAirline_id() {
		return airline_id;
	}


	public int get_cost() {
		return cost;
	}


	public int get_from_airport() {
		return from_airport;
	}


	public int get_to_airport() {
		return to_airport;
	}


	public Date get_departure_time() {
		return departure_time;
	}


	public Date get_arrival_time() {
		return arrival_time;
	}

	
	
}
