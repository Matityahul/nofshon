package vacation.DAL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import vacation.model.*;


public class FlightsHandler {
	
	/**
	 * Get all the flight from the DB
	 * @return All the flights
	 */
	public static List<Flight> GetAllFlights()
	{
		List<Flight> Flights = new ArrayList<Flight>();
		Connection conn = DBConn.getConnection();
		String sql = "SELECT * FROM flights";
		
		try (java.sql.Statement st = conn.createStatement()) {
			ResultSet rs = st.executeQuery(sql);
			Flights = extractFlightsFromRS(rs);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
		return Flights;
	}
	
	
	/**
	 * Get filtered flights from the DB
	 * @return The relevant flights  
	 */
	public static List<Flight> GetFilterdFlights(Date startDate, Date endDate, Integer 
			fromAirPort, Integer toAirPort, Integer bottomCost, Integer topCost)
	{
		List<Flight> Flights = new ArrayList<Flight>();
		Connection conn = DBConn.getConnection();
		String sql = "SELECT distinct f.* FROM flight f WHERE 1=1";
		
		int    startDatePos =0;
		int    endDatePos =0;
		int    fromAirportPos =0;
		int    toAirportPos =0;
		int    bottomCostPos =0;
		int    topCostPos =0;
		int conditionCounter=0;
		
		if (startDate != null)
		{
			startDatePos = ++conditionCounter;
			sql = sql +" AND f.Departure_Time >= ? ";
		}
		if (endDate != null)
		{
			endDatePos = ++conditionCounter;
			sql = sql +" AND f.Departure_Time <= ? ";
		}
		if (fromAirPort != null)
		{
			fromAirportPos = ++conditionCounter;
			sql = sql +" AND f.From_Airport = ? ";
		}
		if (toAirPort != null)
		{
			toAirportPos = ++conditionCounter;
			sql = sql + " AND f.To_Airport = ? ";
		}
		if (bottomCost != null)
		{
			bottomCostPos = ++conditionCounter;
			sql = sql + " AND f.cost >= ? ";
		}
		if (topCost != null)
		{
			topCostPos = ++conditionCounter;
			sql = sql + " AND f.cost <= ? ";
		}


		
		try (java.sql.PreparedStatement st = conn.prepareStatement(sql)) {
			
			st.clearParameters();
			
			if (startDatePos>0)
			{
				st.setDate(startDatePos,startDate);
				
			}
			if (endDatePos>0)
			{
				st.setDate(endDatePos,endDate);
			}
			if (fromAirportPos>0)
			{
				st.setInt(fromAirportPos,fromAirPort);
			}
			if (toAirportPos>0)
			{
				st.setInt(toAirportPos,toAirPort);
			}
			if (bottomCostPos>0)
			{
				st.setInt(bottomCostPos,bottomCost);
			}
			if (topCostPos>0)
			{
				st.setInt(topCostPos,topCost);
			}
			
			ResultSet rs = st.executeQuery();
			Flights = extractFlightsFromRS(rs);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
		return Flights;
	}
	
	private static List<Flight> extractFlightsFromRS(ResultSet resultSet) throws SQLException {
		List<Flight> flights = new ArrayList<Flight>();
		
		while (resultSet.next()) {
			int flight_id=resultSet.getInt("ID");
			int airline_id=resultSet.getInt("Airline_ID");
			int cost = resultSet.getInt("Cost");
			int from_airport = resultSet.getInt("From_Airport");
			int to_airport = resultSet.getInt("To_Airport");
			Date departure_time=resultSet.getDate("Departure_Time");
			Date arrival_time=resultSet.getDate("Arrival_Time");
			
			Flight flight = new Flight(flight_id, airline_id, cost, from_airport, to_airport, departure_time, arrival_time);
			flights.add(flight);
		}
		
		return flights;
	}
}
