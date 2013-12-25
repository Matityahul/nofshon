package vacation.model;

public class Airport {
        int _id;
        int _city_id;
        String _name;
        
        

        public Airport(int id, int city_id, String name)
                        {
                super();
                _id = id;
                _city_id = city_id;
                _name = name;
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
        
        public String toString()
        {
        	return _name;
        }
}
