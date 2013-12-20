package vacation.model;

import java.sql.Date;

public class Order {
        int _id;
        int _method_id;
        Date _order_time;
        
        public Order(int id, int method_id, Date order_time) {
        	super();
            _id = id;
            _method_id = method_id;
            _order_time = order_time;
		}

		public int get_id() {
                return _id;
        }

        public int get_method_id() {
                return _method_id;
        }
        
        public Date get_order_time() {
                return _order_time;
        }

}
