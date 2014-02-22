package vacation.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import vacation.model.*;


public class FlightsHandler {
	
	/**
	 * Get all the flight from the DB
	 * @return All the flights
	 */
	public static List<Flight> GetAllFlights(boolean closeConnection)
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
		finally
		{
			if (closeConnection)
			{
				DBConn.CloseConnection();
			}
		}
		
		return Flights;
	}
	
	public static String GetAirlineNameByID(int id, boolean closeConnection)
	{
		Connection conn = DBConn.getConnection();
		String sql = "SELECT Name FROM airlines where id = ?";
		String name = "";
		
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.clearParameters();
			st.setInt(1,id);
			ResultSet rs = st.executeQuery();
			rs.next();
			name = rs.getString("Name");
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		finally
		{
			if (closeConnection)
			{
				DBConn.CloseConnection();
			}
		}
		
		return name;
	}
	
	public static String GetAirportNameByID(int id, boolean closeConnection)
	{
		Connection conn = DBConn.getConnection();
		String sql = "SELECT Name FROM airports where id = ?";
		String name = "";
		
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.clearParameters();
			st.setInt(1,id);
			ResultSet rs = st.executeQuery();
			rs.next();
			name = rs.getString("Name");
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		finally
		{
			if (closeConnection)
			{
				DBConn.CloseConnection();
			}
		}
		
		return name;
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
		String sql = "SELECT distinct f.* FROM flights f WHERE 1=1";
		
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
		finally
		{
			DBConn.CloseConnection();
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
			java.util.Date departure_time=resultSet.getTimestamp("Departure_Time");
			Date arrival_time=resultSet.getDate("Arrival_Time");
			
			Flight flight = new Flight(flight_id, airline_id, cost, from_airport, to_airport, departure_time, arrival_time);
			flights.add(flight);
		}
		
		return flights;
	}


	public static List<Flight> GetFlightsByOrder(int orderID) {
		List<Flight> flights = new ArrayList<Flight>();
		Connection conn = DBConn.getConnection();
		String sql = "SELECT distinct f.* " +
					 "FROM flights f JOIN bookings b ON (f.ID = b.Depart_Flight_ID OR f.ID = b.Return_Flight_ID) " +
					 "WHERE b.Order_ID = ?";
		
		try (java.sql.PreparedStatement st = conn.prepareStatement(sql)) 
		{
			st.clearParameters();
			st.setInt(1,orderID);
			ResultSet rs = st.executeQuery();
			flights = extractFlightsFromRS(rs);
		} 
		catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		finally
		{
			DBConn.CloseConnection();
		}
		
		return flights;
	}
	
	public static List<Flight> GetReturnFlights(int flightID, int nightsNumber) {
		List<Flight> flights = new ArrayList<Flight>();
		Connection conn = DBConn.getConnection();
		String sql = "select f1.* from flights f1, flights f2 " +
					 "where f2.ID = ? AND f2.To_Airport = f1.From_Airport AND " +
					 "DATEDIFF(f1.Departure_Time,f2.Arrival_Time) = ?";
		
		try (java.sql.PreparedStatement st = conn.prepareStatement(sql)) 
		{
			st.clearParameters();
			st.setInt(1,flightID);
			st.setInt(2,nightsNumber);
			ResultSet rs = st.executeQuery();
			flights = extractFlightsFromRS(rs);
		} 
		catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		finally
		{
			DBConn.CloseConnection();
		}
		
		return flights;
	}
}
