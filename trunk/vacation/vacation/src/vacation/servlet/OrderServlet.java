package vacation.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import vacation.model.*;
import vacation.DAL.*;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType( "text/html" );
		PrintWriter writer = response.getWriter();
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		
		String requestType = request.getParameter("requestType");
		
		if (requestType != null && requestType.compareTo("getOrders") == 0)
		{
			String userId = request.getParameter("userId");
			List<Order> orders = null;
			
			try 
			{
				orders = OrdersHandler.GetOrdersOfUser(Integer.parseInt(userId));
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
			
			writer.print(gson.toJson(orders));
		}
		else if (requestType != null && requestType.compareTo("getBookings") == 0)
		{
			String orderId = request.getParameter("orderId");
			
			List<Booking> bookings = null;
			
			try 
			{
				bookings = BookingHandler.GetBookingsOfOrder(Integer.parseInt(orderId));
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
			
			writer.print(gson.toJson(bookings));
		}
		else if (requestType != null && requestType.compareTo("getFlights") == 0)
		{
			String orderId = request.getParameter("orderId");
			
			List<Flight> flights = null;
			
			try 
			{
				flights = FlightsHandler.GetFlightsByOrder(Integer.parseInt(orderId));
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
			
			writer.print(gson.toJson(flights));
		}
		else if (requestType != null && requestType.compareTo("getHotels") == 0)
		{
			String orderId = request.getParameter("orderId");
			
			List<Hotel> hotels = null;
			
			try 
			{
				hotels = HotelsHandler.GetHotelsByOrder(Integer.parseInt(orderId));
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
			
			writer.print(gson.toJson(hotels));
		}
		else if (requestType != null && requestType.compareTo("getPaymentMethods") == 0)
		{			
			List<PaymentMethod> methods = null;
			
			try 
			{
				methods = OrdersHandler.GetPaymentMethods();
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
			
			writer.print(gson.toJson(methods));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType( "text/html" );
		PrintWriter writer = response.getWriter();
		BufferedReader br = request.getReader();
		String a = br.readLine();
		try {
			JSONObject jObj = new JSONObject(a);

			// Build the order itself
		    List<Booking> lstBookings = new ArrayList<Booking>();
		    
		    int orderID = StaticDataHandler.GetNextID("orders");
		    int user_id = jObj.getInt("userID");
		    int method_id = jObj.getInt("paymentMethodId");
		    int departFlightId = jObj.getInt("departFlightId");
		    int returnFlightId = jObj.getInt("returnFlightId");
		    int nights = jObj.getInt("nights");
		    int hotelID = jObj.getInt("hotelId");
		    
		    JSONArray passengers = jObj.getJSONArray("passengers");
		    
		    // Go over each passenger
		    for (int i = 0; i < passengers.length(); i++)
		    {
		    	// Get the current passenger
		    	JSONObject currPassenger = passengers.getJSONObject(i);
		    	
		    	// Get the next booking id
			    int bookingID = StaticDataHandler.GetNextID("bookings");
			    
			    // Create the new booking
			    Booking newBooking = new Booking(bookingID, orderID, departFlightId, returnFlightId, hotelID, nights, currPassenger.getString("name"), currPassenger.getInt("passport"));
			    
			    // Add it to the list
			    lstBookings.add(newBooking);
		    }
		   			    
		    // Add the order and the bookings
		    OrdersHandler.AddOrder(orderID, user_id, lstBookings, method_id);	
		    
		    String jsonAck = "{\"success\": \"success\"}";
		    
		    writer.print(jsonAck);	
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (Exception e) {
				e.printStackTrace();
			}
	
	}

}
