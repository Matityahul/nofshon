package vacation.model;

public class PaymentMethod {
        int _id;
        String _name;
        
        

        public PaymentMethod(int id, String name)
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
        
        public String toString()
        {
        	return _name;
        }
}
