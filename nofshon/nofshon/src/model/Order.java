package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Order {
	int id;
	int user_id;
	int method_id;
	Date order_time;
	

	public Order(int id, int user_id, int method_id, Date order_time)
			{
		super();
		this.id = id;
		this.user_id = user_id;
		this.method_id = method_id;
		this.order_time = order_time;
	}
	
	public int get_id() {
		return id;
	}

	public int get_user_id() {
		return user_id;
	}


	public int get_method_id() {
		return method_id;
	}
	
	public Date get_order_time() {
		return order_time;
	}

}
