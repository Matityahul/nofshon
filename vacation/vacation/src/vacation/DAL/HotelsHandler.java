package vacation.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vacation.model.Flight;
import vacation.model.Hotel;

public class HotelsHandler {

	/**
	 * Get all the countries from the DB
	 * @return All the countries
	 */
	public List<Hotel> GetAllHotels()
	{
		List<Hotel> Hotels = new ArrayList<Hotel>();
		Connection conn = DBConn.getConnection();
		String sql = "SELECT * FROM hotels";
		
		try (java.sql.Statement st = conn.createStatement()) {
			ResultSet rs = st.executeQuery(sql);
			Hotels = extractHotelsFromRS(rs);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
		return Hotels;
	}
	
	
	public Hotel GetHotelByID(int id)
	{
		List<Hotel> Hotels = new ArrayList<Hotel>();
		Connection conn = DBConn.getConnection();
		String sql = "SELECT * FROM hotels where id=?";
		
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.clearParameters();
			st.setInt(1,id);
			ResultSet rs = st.executeQuery();
			Hotels = extractHotelsFromRS(rs);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return Hotels.get(0);
	}

	/**
	 * Get filtered flights from the DB
	 * @return The relevant flights  
	 */
	public static List<Hotel> GetFilterdHotels(String name, Integer city, Integer bottomCost, Integer topCost)
	{
		List<Hotel> hotels = new ArrayList<Hotel>();
		Connection conn = DBConn.getConnection();
		String sql = "SELECT distinct h.* FROM hotels h WHERE 1=1";
		
		int    namePos = 0;
		int    cityPos = 0;
		int    bottomCostPos = 0;
		int    topCostPos = 0;
		int conditionCounter = 0;
		
		if (name != null)
		{
			namePos = ++conditionCounter;
			sql = sql +" AND h.Name = ? ";
		}
		
		if (city != null)
		{
			cityPos = ++conditionCounter;
			sql = sql +" AND h.City_ID = ? ";
		}

		if (bottomCost != null)
		{
			bottomCostPos = ++conditionCounter;
			sql = sql + " AND h.cost >= ? ";
		}
		
		if (topCost != null)
		{
			topCostPos = ++conditionCounter;
			sql = sql + " AND h.cost <= ? ";
		}


		
		try (java.sql.PreparedStatement st = conn.prepareStatement(sql)) {
			
			st.clearParameters();
			
			if (namePos > 0)
			{
				st.setString(namePos, name);
			}
			
			if (cityPos > 0)
			{
				st.setInt(cityPos, city);
			}
		
			if (bottomCostPos > 0)
			{
				st.setInt(bottomCostPos, bottomCost);
			}
			
			if (topCostPos > 0)
			{
				st.setInt(topCostPos, topCost);
			}
			
			ResultSet rs = st.executeQuery();
			
			hotels = extractHotelsFromRS(rs);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
		return hotels;
	}
	
	private static List<Hotel> extractHotelsFromRS(ResultSet resultSet) throws SQLException {
		List<Hotel> hotels = new ArrayList<Hotel>();
		
		while (resultSet.next()) {
			int hotel_id=resultSet.getInt("ID");
			int city_id = resultSet.getInt("CITY_ID");
			String hotel_name =resultSet.getString("NAME");
			String hotel_address =resultSet.getString("ADDRESS");
			String hotel_phone =resultSet.getString("PHONE");
			int hotel_cost = resultSet.getInt("COST");
			
			Hotel hotel = new Hotel(hotel_id, city_id, hotel_name, hotel_address, hotel_phone, hotel_cost);
			
			hotels.add(hotel);
		}
		
		return hotels;
	}


	public static List<Hotel> GetHotelsByOrder(int orderID) {
		List<Hotel> hotels = new ArrayList<Hotel>();
		Connection conn = DBConn.getConnection();
		String sql = "SELECT distinct h.* " +
					 "FROM hotels h JOIN bookings b ON h.ID = b.Hotel_ID " +
					 "WHERE b.Order_ID = ?";
		
		try (java.sql.PreparedStatement st = conn.prepareStatement(sql)) 
		{
			st.clearParameters();
			st.setInt(1,orderID);
			ResultSet rs = st.executeQuery();
			hotels = extractHotelsFromRS(rs);
		} 
		catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
		return hotels;
	}
}
