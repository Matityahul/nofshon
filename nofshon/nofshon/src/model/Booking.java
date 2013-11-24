package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Booking {
	int id;
	int order_id;
	int depart_flight_id;
	int return_flight_id;
	int hotel_id;
	int number_of_nights;
	String name;
	int passport_id;
	
	

	public Booking(int booking_id, int order_id, int depart_flight_id,
			int return_flight_id, int hotel_id, int number_of_nights,
			String name, int passport_id) {
		super();
		this.id = booking_id;
		this.order_id = order_id;
		this.depart_flight_id = depart_flight_id;
		this.return_flight_id = return_flight_id;
		this.hotel_id = hotel_id;
		this.number_of_nights = number_of_nights;
		this.name = name;
		this.passport_id = passport_id;
	}
	
	public int get_id() {
		return id;
	}

	public int get_order_id() {
		return order_id;
	}


	public int get_depart_flight_id() {
		return depart_flight_id;
	}


	public int get_return_flight_id() {
		return return_flight_id;
	}


	public int get_hotel_id() {
		return hotel_id;
	}


	public int get_number_of_nights() {
		return number_of_nights;
	}


	public String get_name() {
		return name;
	}
	
	public int get_passport_id() {
		return passport_id;
	}
}
