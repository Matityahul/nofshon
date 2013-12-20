package vacation.model;

import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

public class User {
        int _id;
        String _userName;
        String _password;
        Date _reg_date;
        String _firstName;
        String _lastName;
        String _address;
        String _phone;
        String _email;
        List<Order> _orders;
        

        public User(int id, String userName, String password, String address,
        		String phone, Date reg_date, String firstName, String lastName, String email)
        {
        	this(id, userName, password, address, phone, reg_date, firstName, 
        			lastName, email, new ArrayList<Order>());
        }
        
        public User(int id, String userName, String password, String address,
        		String phone, Date reg_date, String firstName, String lastName, String email, List<Order> orders)
        {
                super();
                _id = id;
                _userName = userName;
                _password = password;
                _address = address;
                _phone = phone;
                _reg_date = reg_date;
                _firstName = firstName;
                _lastName = lastName;
                _email = email;
                _orders = orders;
        }
        
        public int get_id() {
                return _id;
        }

        public String get_userName() {
                return _userName;
        }


        public String get_password() {
                return _password;
        }
        
        public String get_address() {
                return _address;
        }

        public String get_phone() {
                return _phone;
        }


        public Date get_reg_date() {
                return _reg_date;
        }
        
        public String get_firstName() {
                return _firstName;
        }
        
        public String get_lastName() {
                return _lastName;
        }

        public String get_email() {
                return _email;
        }
        
        public void AddOrder(Order order)
    	{
    		if (_orders == null)
    		{
    			_orders = new ArrayList<Order>();
    		}
    		
    		_orders.add(order);
    	}
    	
    	public void RemoveOrder(Order order)
    	{
    		if (_orders != null)
    		{
    			_orders.remove(order);
    		}
    	}

		public List<Order> get_orders() {
			
			return _orders;
		}

}