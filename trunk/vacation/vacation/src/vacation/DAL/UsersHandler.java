package vacation.DAL;

import vacation.model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.json.JSONException;

import redis.clients.jedis.Jedis;

public class UsersHandler {

	/**
	 * Add user to the DB
	 */
	public static void AddUser(User user)
	{
		Connection conn = DBConn.getConnection();
		
		String sql = "insert into users(ID,UserName,Password,Registration_Date,First_Name,Last_Name,Address,Email,Phone) values (?,?,?,?,?,?,?,?,?)";
		
		try (java.sql.PreparedStatement st = conn.prepareStatement(sql)) {
			st.clearParameters();
			st.setInt(1, user.get_id());
			st.setString(2,user.get_userName());
			st.setString(3, user.get_password());
			st.setDate(4, user.get_reg_date());
			st.setString(5, user.get_firstName());
			st.setString(6, user.get_lastName());
			st.setString(7, user.get_address());
			st.setString(8, user.get_email());
			st.setString(9, user.get_phone());
			
			st.execute();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		finally
		{
			DBConn.CloseConnection();
		}
	}
	
	/**
	 * Update the given user in the DB
	 */
	public static void UpdateUser(User user)
	{
		Connection conn = DBConn.getConnection();
		
		String sql = "Update users set UserName = ? , Password = ? , First_Name = ? , Last_Name = ? , Address = ? , Email = ?, Phone = ? where ID = ?";
		
		try (java.sql.PreparedStatement st = conn.prepareStatement(sql)) {
			st.clearParameters();
			st.setString(1,user.get_userName());
			st.setString(2, user.get_password());
			st.setString(3, user.get_firstName());
			st.setString(4, user.get_lastName());
			st.setString(5, user.get_address());
			st.setString(6, user.get_email());
			st.setString(7, user.get_phone());
			st.setInt(8, user.get_id());
			
			st.executeUpdate();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		finally
		{
			DBConn.CloseConnection();
		}
	}
	
	/**
	 * Delete the given user from the DB
	 */
	public static int DeleteUser(User user)
	{
		int rows = OrdersHandler.DeleteOrdersByUserID(user.get_id(), false);
		
		Connection conn = DBConn.getConnection();
		String sql = "DELETE FROM users WHERE ID=?";
		
		try (java.sql.PreparedStatement st = conn.prepareStatement(sql)) {
			st.clearParameters();
			st.setInt(1,user.get_id());
			rows += st.executeUpdate();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		finally
		{
			DBConn.CloseConnection();
		}
		
		return rows;
	}
	
	
	public static User GetUser(Integer id, boolean closeConnection)
	{
		User user = null;
		boolean firstRow = true;
		
		Connection conn = DBConn.getConnection();
		String sql = "SELECT u.ID User_ID, u.UserName, u.Password, u.Registration_Date, " + 
							"u.First_Name, u.Last_Name, u.Address, u.Email, u.Phone, " + 
							"o.ID Order_ID, o.Method_ID, o.Order_Time " +
					 "FROM users u LEFT OUTER JOIN orders o ON u.ID=o.User_ID " +
					 "WHERE u.ID=?";
		
		try (java.sql.PreparedStatement st = conn.prepareStatement(sql)) {
			st.clearParameters();
			st.setInt(1,id);
			
			ResultSet resultSet = st.executeQuery();

			while (resultSet.next()) {
				
				if (firstRow)
				{
					firstRow = false;
					
					int userID = resultSet.getInt("User_ID");
					Date regDate = resultSet.getDate("Registration_Date");
					String userName = resultSet.getString("UserName");
					String password = resultSet.getString("Password");
					String firstName = resultSet.getString("First_Name");
					String lastName = resultSet.getString("Last_Name");
					String address = resultSet.getString("Address");
					String email = resultSet.getString("Email");
					String phone = resultSet.getString("Phone");
					
					user = new User(userID, userName, password, address, phone, 
							regDate, firstName, lastName, email);
					
					// Check whether the user has no orders 
					if (resultSet.getInt("Order_ID") != 0)
					{
						user.AddOrder(CreateOrder(resultSet));
					}
				}
				else
				{
					user.AddOrder(CreateOrder(resultSet));
				}
			}
			
			
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
		
		return user;
	}
	
	public static User GetUser(String userName, String password)
	{
		User user = null;
		boolean firstRow = true;
		
		Connection conn = DBConn.getConnection();
		String sql = "SELECT u.ID User_ID, u.UserName, u.Password, u.Registration_Date, " + 
							"u.First_Name, u.Last_Name, u.Address, u.Email, u.Phone, " + 
							"o.ID Order_ID, o.Method_ID, o.Order_Time " +
					 "FROM users u LEFT OUTER JOIN orders o ON u.ID=o.User_ID " +
					 "WHERE u.UserName=? AND u.Password=?";
		
		try (java.sql.PreparedStatement st = conn.prepareStatement(sql)) {
			st.clearParameters();
			st.setString(1,userName);
			st.setString(2,password);
			
			ResultSet resultSet = st.executeQuery();

			while (resultSet.next()) {
				
				if (firstRow)
				{
					firstRow = false;
					
					int userID = resultSet.getInt("User_ID");
					Date regDate = resultSet.getDate("Registration_Date");
					String firstName = resultSet.getString("First_Name");
					String lastName = resultSet.getString("Last_Name");
					String address = resultSet.getString("Address");
					String email = resultSet.getString("Email");
					String phone = resultSet.getString("Phone");
					
					user = new User(userID, userName, password, address, phone, 
							regDate, firstName, lastName, email);
					
					// Check whether the user has no orders 
					if (resultSet.getInt("Order_ID") != 0)
					{
						user.AddOrder(CreateOrder(resultSet));
					}
				}
				else
				{
					user.AddOrder(CreateOrder(resultSet));
				}
			}
			
			
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		finally
		{
			DBConn.CloseConnection();
		}
		
		return user;
	}
	
	private static Order CreateOrder(ResultSet resultSet) throws SQLException
	{
		int orderID = resultSet.getInt("Order_ID");
		int methodID = resultSet.getInt("Method_ID");
		Date orderTime = resultSet.getDate("Order_Time");
		
		return new Order(orderID, methodID, orderTime);
	}
	
	public static User GetUserFromReddis(String userName, String password) throws ReflectiveOperationException, JSONException, ParseException
	{
		Jedis jconn; 
		
		try
		{
			jconn = RedisConn.getConnection();
		}
		catch (Exception ex)
		{
			return null;
		}
		
		String reddisPassword = jconn.get("uid:"+userName+":password");
		if (reddisPassword == null || reddisPassword.length() ==0 )
		{
			System.out.println("error in login params");
			return null;
		}
		
		if (!reddisPassword.equals(password))
		{
			System.out.println("Wrong Password Entered");
			return null;
		}
		
		String reg_date = jconn.get("uid:"+userName+":reg_date");
		if (reg_date == null || reg_date.length() ==0 )
		{
			System.out.println("error in login params");
		}
		
		String firstname = jconn.get("uid:"+userName+":firstname");
		if (firstname == null || firstname.length() ==0 )
		{
			System.out.println("error in login params");
		}
		
		String lastname = jconn.get("uid:"+userName+":lastname");
		if (lastname == null || lastname.length() ==0 )
		{
			System.out.println("error in login params");
		}
		
		String address = jconn.get("uid:"+userName+":address");
		if (address == null || address.length() ==0 )
		{
			System.out.println("error in login params");
		}
		
		String phone = jconn.get("uid:"+userName+":phone");
		if (phone == null || phone.length() ==0 )
		{
			System.out.println("error in login params");
		}
		
		String email = jconn.get("uid:"+userName+":email");
		if (email == null || email.length() ==0 )
		{
			System.out.println("error in login params");
		}
		
		String id = jconn.get("uid:"+userName+":id");
		if (id == null || id.length() ==0 )
		{
			System.out.println("error in login params");
		}
		
		int userIDNumber = Integer.parseInt(id);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		String orderID = jconn.lpop("uid:"+userName+":orders");
		List<Order> orderList = new ArrayList<Order>();
		while (orderID != null && orderID.length() > 0)
		{
			int orderIDNumber = Integer.parseInt(orderID);
			String methodid = jconn.get("orderid:"+orderID+":methodid");
			if (methodid == null || methodid.length() ==0 )
			{
				System.out.println("error in login params");
			}
			
			String date = jconn.get("orderid:"+orderID+":date");
			if (date == null || date.length() ==0 )
			{
				System.out.println("error in login params");
			}
			
			Date orderDate = new java.sql.Date(df.parse(date).getTime());
			int methodID = Integer.parseInt(methodid);
			Order order = new Order(orderIDNumber, methodID, orderDate);
			orderList.add(order);
			orderID = jconn.lpop("uid:"+userName+":orders");
		}			
		
		Date date = new java.sql.Date(df.parse(reg_date).getTime());
		User foundUser = new User(userIDNumber, userName, password, address,phone,date, firstname, lastname, email, orderList);

		return foundUser;
	}
	
	public static void SaveUserToReddis(User user) throws ClassNotFoundException, JSONException
	{
		Jedis jconn; 
		
		try
		{
			jconn = RedisConn.getConnection();
		}
		catch (Exception ex)
		{
			return;
		}
		
		String username = user.get_userName();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");		
		
		jconn.set("uid:" + username + ":id",Integer.toString(user.get_id()));
		jconn.set("uid:" + username + ":password",user.get_password());
		jconn.set("uid:" + username + ":reg_date", df.format(user.get_reg_date()));
		jconn.set("uid:" + username + ":firstname",user.get_firstName());
		jconn.set("uid:" + username + ":lastname",user.get_lastName());
		jconn.set("uid:" + username + ":address",user.get_address());
		jconn.set("uid:" + username + ":phone",user.get_phone());
		jconn.set("uid:" + username + ":email",user.get_email());
		
		List<Order> orders =  user.get_orders();
		
		for (Order order : orders)
		{
			String orderID = Integer.toString(order.get_id());
			jconn.lpush("uid:" + username + ":orders",orderID);
			jconn.set("orderid:" + orderID +":methodid",Integer.toString(order.get_method_id()));
			jconn.set("orderid:" + orderID +":date",df.format(order.get_order_time()));
		}
	}
	
	
	public static List<User> GetOnlineUsers() throws ParseException
	{
		List<User> returnList = new ArrayList<User>();
		Jedis conn;
		try {
			conn = RedisConn.getConnection();
			Set<String> usersOnline = conn.zrange("users:on:line",0,10);
			String f = "";
			for(String userID:usersOnline)
			{
				// We have the userID, Let get his props
				String userName = conn.get("uid:"+userID+":username");
				if (userName == null || userName.length() ==0 )
				{
					System.out.println("error in login params");
				}
				
				String password = conn.get("uid:"+userID+":password");
				if (password == null || password.length() ==0 )
				{
					System.out.println("error in login params");
				}
				
				String reg_date = conn.get("uid:"+userID+":reg_date");
				if (reg_date == null || reg_date.length() ==0 )
				{
					System.out.println("error in login params");
				}
				
				String firstname = conn.get("uid:"+userID+":firstname");
				if (firstname == null || firstname.length() ==0 )
				{
					System.out.println("error in login params");
				}
				
				String lastname = conn.get("uid:"+userID+":lastname");
				if (lastname == null || lastname.length() ==0 )
				{
					System.out.println("error in login params");
				}
				
				String address = conn.get("uid:"+userID+":address");
				if (address == null || address.length() ==0 )
				{
					System.out.println("error in login params");
				}
				
				String phone = conn.get("uid:"+userID+":phone");
				if (phone == null || phone.length() ==0 )
				{
					System.out.println("error in login params");
				}
				
				String email = conn.get("uid:"+userID+":email");
				if (email == null || email.length() ==0 )
				{
					System.out.println("error in login params");
				}
				
				int userIDNumber = Integer.parseInt(userID);
				DateFormat df = new SimpleDateFormat("yyyy-dd-mm", Locale.ENGLISH);
				
				String orderID = conn.lpop("uid:"+userID+":orders");
				List<Order> orderList = new ArrayList();
				while (orderID != null && orderID.length() > 0)
				{
					int orderIDNumber = Integer.parseInt(orderID);
					String methodid = conn.get("orderid:"+orderID+":methodid");
					if (methodid == null || methodid.length() ==0 )
					{
						System.out.println("error in login params");
					}
					
					String date = conn.get("orderid:"+orderID+":date");
					if (date == null || date.length() ==0 )
					{
						System.out.println("error in login params");
					}
					
					Date orderDate = new java.sql.Date(df.parse(date).getTime());
					int methodID = Integer.parseInt(methodid);
					Order order = new Order(orderIDNumber, methodID, orderDate);
					orderList.add(order);
					orderID = conn.lpop("uid:"+userID+":orders");
				}			
				
				Date date = new java.sql.Date(df.parse(reg_date).getTime());
				User foundUser = new User(userIDNumber, userName, password, address,phone,date, firstname, lastname, email, orderList);
				returnList.add(foundUser);
			}
		} catch (ClassNotFoundException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnList;
	}
}
