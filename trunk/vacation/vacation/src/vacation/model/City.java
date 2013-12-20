package vacation.model;

public class City {
    int _id;
    int _country_id;
    String _name;
    
    

    public City(int id, int country_id, String name)
                    {
            super();
            _id = id;
            _country_id = country_id;
            _name = name;
    }
    
    public int get_id() {
            return _id;
    }

    public int get_country_id() {
            return _country_id;
    }


    public String getName() {
            return _name;
    }
}