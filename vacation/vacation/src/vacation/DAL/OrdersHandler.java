package vacation.DAL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import vacation.model.*;

public class OrdersHandler {

		/**
		 * Get all the orders from the DB
		 * @return All the orders
		 */
		public static List<Order> GetAllOrders()
		{
			List<Order> Orders = new ArrayList<Order>();
			Connection conn = DBConn.getConnection();
			String sql = "SELECT * FROM orders";
			
			try (java.sql.Statement st = conn.createStatement()) {
				ResultSet rs = st.executeQuery(sql);
				Orders = extractOrdersFromRS(rs);
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
			
			return Orders;
		}
		
		public static List<PaymentMethod> GetPaymentMethods()
		{
			List<PaymentMethod> Methods = new ArrayList<PaymentMethod>();
			Connection conn = DBConn.getConnection();
			String sql = "SELECT * FROM payment_methods";
			
			try (java.sql.Statement st = conn.createStatement()) {
				ResultSet rs = st.executeQuery(sql);
				Methods = extractMethodsFromRS(rs);
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
			
			return Methods;
		}
	
	public static Order GetOrderByID(int id)
	{
		List<Order> Orders = new ArrayList<Order>();
		Connection conn = DBConn.getConnection();
		String sql = "SELECT * FROM orders where id=?";
		
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.clearParameters();
			st.setInt(1,id);
			ResultSet rs = st.executeQuery();
			Orders = extractOrdersFromRS(rs);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return Orders.get(0);
		
	}
	
	public static List<Order> GetOrdersOfUser(int userId)
	{
		List<Order> Orders = new ArrayList<Order>();
		Connection conn = DBConn.getConnection();
		String sql = "SELECT * FROM orders where user_id=?";
		
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.clearParameters();
			st.setInt(1,userId);
			ResultSet rs = st.executeQuery();
			Orders = extractOrdersFromRS(rs);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return Orders;
		
	}
	 
	public static void AddOrder(int id, int user_id, List<Booking> bookings, int method)
	{
		Connection conn = DBConn.getConnection();
		String sql = "INSERT orders(id,user_id,method_id,order_time)"
				+ " values(?,?,?,CURRENT_DATE())";
		
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.clearParameters();
			st.setInt(1, id);
			st.setInt(2,user_id);
			st.setInt(3,method);
			st.executeUpdate();
			for (int i = 0; i<bookings.size();i++)
			{
				BookingHandler.AddBookingToOrder(id, bookings.get(i));
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public static int DeleteOrderByID(int id)
	{
		String sql = "DELETE FROM orders where ID = ?";

		return DeleteOrders(id, sql);
	}
	
	public static int DeleteOrdersByUserID(int userID)
	{
		String sql = "DELETE FROM orders where User_ID = ?";

		return DeleteOrders(userID, sql);
	}
	
	
	/**
	 * Delete orders by order/user id from the DB
	 * @param id The relevant id (order or user)
	 * @param sql 'Delete' sql statement
	 * @return
	 */
	private static int DeleteOrders(int id, String sql)
	{
		Connection conn = DBConn.getConnection();
		
		int rows = 0;
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.clearParameters();
			st.setInt(1,id);
			rows = st.executeUpdate();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
		return rows;
	}
	
	private static List<Order> extractOrdersFromRS(ResultSet resultSet) throws SQLException {
		List<Order> orders = new ArrayList<Order>();
		
		while (resultSet.next()) {
			int order_id = resultSet.getInt("ID");
			int method = resultSet.getInt("METHOD_ID");
			Date time = resultSet.getDate("ORDER_TIME");
			
			Order order = new Order(order_id, method, time);
			
			orders.add(order);
		}
		
		return orders;
	}
	
	private static List<PaymentMethod> extractMethodsFromRS(ResultSet resultSet) throws SQLException {
		List<PaymentMethod> methods = new ArrayList<PaymentMethod>();
		
		while (resultSet.next()) {
			int method_id = resultSet.getInt("ID");
			String method_name = resultSet.getString("NAME");
			
			PaymentMethod method = new PaymentMethod(method_id, method_name);
			
			methods.add(method);
		}
		
		return methods;
	}
	
}
