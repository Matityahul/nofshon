package vacation.model;

public class Country {
    int _id;
    String _name;
    
    

    public Country(int id, String name)
                    {
            super();
            _id = id;
            _name = name;
    }
    
    public int get_id() {
            return _id;
    }

    public String getName() {
            return _name;
    }
}