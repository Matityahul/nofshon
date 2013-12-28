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
import java.util.List;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
		String url = request.getPathInfo();

		response.setContentType( "text/html" );

		if (url != null && url.indexOf("MakeOrder") != -1)
		{
			try {
				StringBuilder sb = new StringBuilder();
			    BufferedReader br = request.getReader();
			    String str;
			    
			    while( (str = br.readLine()) != null ){
			        sb.append(str);
			    }    
			    
			    // Get the JSON from the string we got from the wrapper
			    JSONObject jObj = new JSONObject(sb.toString());
			    
			    // Build the order itself
			    List<Booking> lstBookings = new ArrayList<Booking>();
			    
			    int orderID = StaticDataHandler.GetNextID("orders");
			    int user_id = jObj.getInt("user_id");
			    int method_id = jObj.getInt("method_id");
			    
			    JSONArray bookingsArray = jObj.getJSONArray("bookings");
			    
			    // Go over the array
			    int bookingID = StaticDataHandler.GetNextID("bookings");
			    Booking newBooking = new Booking(bookingID, orderID, , , , , , , , );
			    lstBookings.add(newBooking);
			    
			    // Add the order and the bookings
			    OrdersHandler.AddOrder(orderID, user_id, lstBookings, method_id);
				}
			 catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

}
