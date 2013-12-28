package vacation.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import vacation.DAL.FlightsHandler;

public class Flight {
        int _id;
        Date _departure_time;
        Date _arrival_time;
        int _airline_id;
        int _cost;
        int _from_airport;
        int _to_airport;
        String _name;
        String _depart_name;
        String _arrival_name;
        String _long_format_time;
        
        

        public Flight(int id, int airline_id, int cost,
                        int from_airport, int to_airport, Date departure_time2,
                        Date arrival_time2) {
                super();
                _id = id;
                _airline_id = airline_id;
                _cost = cost;
                _from_airport = from_airport;
                _to_airport = to_airport;
                _departure_time = departure_time2;
                _arrival_time = arrival_time2;
                _name = FlightsHandler.GetAirlineNameByID(airline_id);
                _depart_name = FlightsHandler.GetAirportNameByID(from_airport);
                _arrival_name = FlightsHandler.GetAirportNameByID(to_airport);
                
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                _long_format_time = df.format(departure_time2);
        }
        
        public int get_id() {
                return _id;
        }


        public int getAirline_id() {
                return _airline_id;
        }


        public int get_cost() {
                return _cost;
        }


        public int get_from_airport() {
                return _from_airport;
        }


        public int get_to_airport() {
                return _to_airport;
        }


        public Date get_departure_time() {
                return _departure_time;
        }


        public Date get_arrival_time() {
                return _arrival_time;
        }
        

        
        
}