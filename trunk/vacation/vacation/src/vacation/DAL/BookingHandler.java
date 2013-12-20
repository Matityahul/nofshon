package vacation.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vacation.model.Booking;

public class BookingHandler {
	
	public static List<Booking> GetBookingsOfOrder(int orderID)
	{
		List<Booking> Bookings = new ArrayList<Booking>();
		Connection conn = DBConn.getConnection();
		String sql = "SELECT * FROM bookings where order_id = ?";
		
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.clearParameters();
			st.setInt(1,orderID);
			ResultSet rs = st.executeQuery();
			Bookings = extractBookingsFromRS(rs);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return Bookings;
	}
	
	public static Booking GetBookingByID(int id)
	{
		List<Booking> Bookings = new ArrayList<Booking>();
		Connection conn = DBConn.getConnection();
		String sql = "SELECT * FROM bookings where id = ?";
		
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.clearParameters();
			st.setInt(1,id);
			ResultSet rs = st.executeQuery();
			Bookings = extractBookingsFromRS(rs);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return Bookings.get(0);
	}
	
	public static void UpdateBooking(Booking booking)
	{
		Connection conn = DBConn.getConnection();
		String sql = "update bookings set order_id = ? ,depart_flight_id = ?,return_flight_id = ? ,hotel_id = ? ,number_of_nights = ? ,name = ? ,passport_id = ? where id = ?";
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.clearParameters();
			st.setInt(1, booking.get_order_id());
			st.setInt(2, booking.get_depart_flight_id());
			st.setInt(3, booking.get_return_flight_id());
			st.setInt(4, booking.get_hotel_id());
			st.setInt(5, booking.get_number_of_nights());
			st.setString(6, booking.get_name());
			st.setInt(7, booking.get_passport_id());
			st.setInt(8, booking.get_id());
			st.executeUpdate();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public static void DeleteBookingByID(int id)
	{
		Connection conn = DBConn.getConnection();
		String sql = "DELETE FROM bookings where id = ?";
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.clearParameters();
			st.setInt(1,id);
			st.executeUpdate();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public static void AddBookingToOrder(int orderID, Booking booking)
	{
		Connection conn = DBConn.getConnection();
		String sql = "INSERT bookings(id, order_id, depart_flight_id, return_flight_id, hotel_id, number_of_nights,name,passport_id)"
				+ " values(?,?,?,?,?,?,?,?)";
		
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.clearParameters();
			st.setInt(1, booking.get_id());
			st.setInt(2,orderID);
			st.setInt(3,booking.get_depart_flight_id());
			st.setInt(4, booking.get_return_flight_id());
			st.setInt(5, booking.get_hotel_id());
			st.setInt(6, booking.get_number_of_nights());
			st.setString(7, booking.get_name());
			st.setInt(8, booking.get_passport_id());
			st.executeUpdate();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	private static List<Booking> extractBookingsFromRS(ResultSet resultSet) throws SQLException {
		List<Booking> bookings = new ArrayList<Booking>();
		
		while (resultSet.next()) {
			int booking_id = resultSet.getInt("ID");
			int order_id = resultSet.getInt("ORDER_ID");
			int depart_flight_id = resultSet.getInt("DEPART_FLIGHT_ID");
			int return_flight_id = resultSet.getInt("RETURN_FLIGHT_ID");
			int hotel_id = resultSet.getInt("HOTEL_ID");
			int nights = resultSet.getInt("NUMBER_OF_NIGHTS");
			String name = resultSet.getString("NAME");
			int passport_id = resultSet.getInt("PASSPORT_ID");
			
			Booking booking = new Booking(booking_id, order_id, depart_flight_id, return_flight_id, hotel_id, nights, name, passport_id);
			
			bookings.add(booking);
		}
		
		return bookings;
	}

}
