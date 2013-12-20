package vacation.model;


public class Booking {
        int _id;
        int _order_id;
        int _depart_flight_id;
        int _return_flight_id;
        int _hotel_id;
        int _number_of_nights;
        String _name;
        int _passport_id;
        
        

        public Booking(int booking_id, int order_id, int depart_flight_id,
                        int return_flight_id, int hotel_id, int number_of_nights,
                        String name, int passport_id) {
                super();
                _id = booking_id;
                _order_id = order_id;
                _depart_flight_id = depart_flight_id;
                _return_flight_id = return_flight_id;
                _hotel_id = hotel_id;
                _number_of_nights = number_of_nights;
                _name = name;
                _passport_id = passport_id;
        }
        
        public int get_id() {
                return _id;
        }

        public int get_order_id() {
                return _order_id;
        }
        
        public void set_order_id(int value)
        {
        	_order_id = value;
        }


        public int get_depart_flight_id() {
                return _depart_flight_id;
        }
        
        public void set_depart_flight_id(int value)
        {
        	_depart_flight_id = value;
        }


        public int get_return_flight_id() {
                return _return_flight_id;
        }
        

        public void set_return_flight_id(int value) {
                 _return_flight_id = value;
        }


        public int get_hotel_id() {
                return _hotel_id;
        }
        
        public void set_hotel_id(int value) {
             _hotel_id = value;
    }


        public int get_number_of_nights() {
                return _number_of_nights;
        }
        
        public void set_number_of_nights(int value) {
             _number_of_nights = value;
    }


        public String get_name() {
                return _name;
        }
        
        public void set_name(String value) {
           _name = value;
    }
        
        public int get_passport_id() {
            return _passport_id;
    }
        
        public void set_passport_id(int value) {
                _passport_id = value;
        }
}
