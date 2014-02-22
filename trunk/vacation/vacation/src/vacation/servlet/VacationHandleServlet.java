package vacation.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vacation.DAL.BookingHandler;
import vacation.DAL.StaticDataHandler;
import vacation.model.Booking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class VacationHandleServlet
 */
@WebServlet("/VacationHandleServlet")
public class VacationHandleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VacationHandleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
	
		
		// Read the booking id
		String bookingID = request.getParameter("id");
		
        out.println("<HEAD><TITLE>Vacation servlet test</TITLE></HEAD><BODY>");
        
		try
		{
			BookingHandler.DeleteBookingByID(Integer.parseInt(bookingID));
			
	        out.println("<P>Booking #" + bookingID + " was deleted");

		}
		catch (Exception ex)
		{
			out.println("<P>Exception happened<br>" + ex.getMessage());
		}
		finally
		{
	        out.println("</BODY>");
	        out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		
		// Get the method to do (insert or update)
		String method = request.getParameter("method");
		
		int orderID = Integer.parseInt(request.getParameter("orderID"));
		
		// Read the parameters
		String name = request.getParameter("txtName");
		int passport = Integer.parseInt(request.getParameter("txtPassport"));
		int depart_flight = Integer.parseInt(request.getParameter("nDepartFlightID"));
		int return_flight = Integer.parseInt(request.getParameter("nReturnFlightID"));
		int hotelID = Integer.parseInt(request.getParameter("nHotelID"));
		int nights = Integer.parseInt(request.getParameter("nNumOfNights"));
		
        out.println("<HEAD><TITLE>Vacation servlet test</TITLE></HEAD><BODY>");
		
        try
        {
        	if (method.compareToIgnoreCase("insert") == 0)
    		{
    	        out.println("insert a new booking to " + name + " , " + passport);
    			
    	        // Generate a new ID
    	        int newBookingID = StaticDataHandler.GetNextID("bookings");
    	        
    			// We need to insert a new booking
    	        Booking newBooking = new Booking(newBookingID, orderID, depart_flight, return_flight, hotelID, nights, name, passport);
    			
    	        BookingHandler.AddBookingToOrder(orderID, newBooking, true);
    	        
    			// Convert Booking to JSON and return it
    			out.print(gson.toJson(newBooking));
    		}
    		else if(method.compareToIgnoreCase("update") == 0)
    		{
    	        out.println("update booking for " + name + " , " + passport);
    	        
    	        int bookingID = Integer.parseInt(request.getParameter("bookingID"));
    	        
    			// Get the booking
    			Booking currBooking = BookingHandler.GetBookingByID(bookingID);
    			
    			// Update the data
    			currBooking.set_order_id(orderID);
    			currBooking.set_depart_flight_id(depart_flight);
    			currBooking.set_return_flight_id(return_flight);
    			currBooking.set_hotel_id(hotelID);
    			currBooking.set_number_of_nights(nights);
    			currBooking.set_name(name);
    			currBooking.set_passport_id(passport);
    			
    			// Update the DB
    			BookingHandler.UpdateBooking(currBooking);
    			
    			// Convert Booking to JSON and return it
    			out.print(gson.toJson(currBooking));
    		}
        }
        catch (Exception ex)
        {
			out.println("<P>Exception happened<br>" + ex.getMessage());
        }
        finally
        {
	        out.println("</BODY>");
	        out.close();
        }				
	}
}
