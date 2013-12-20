package vacation.model;

public class Hotel {
        int _id;
        int _city_id;
        String _name;
        String _address;
        String _phone;
        int _cost;
        
        

        public Hotel(int id, int city_id, String name, String address, String phone, int cost)
                        {
                super();
                _id = id;
                _city_id = city_id;
                _name = name;
                _address = address;
                _phone = phone;
                _cost = cost;
        }
        
        public int get_id() {
                return _id;
        }

        public int get_CityID() {
                return _city_id;
        }


        public String getName() {
                return _name;
        }
        
        public String get_address() {
                return _address;
        }

        public String get_phone() {
                return _phone;
        }


        public int get_cost() {
                return _cost;
        }
}
