package vacation.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import vacation.DAL.BookingHandler;
import vacation.DAL.FlightsHandler;
import vacation.DAL.HotelsHandler;
import vacation.DAL.OrdersHandler;
import vacation.DAL.StaticDataHandler;
import vacation.model.Airport;
import vacation.model.Booking;
import vacation.model.Flight;
import vacation.model.Hotel;
import vacation.model.Order;

/**
 * Servlet implementation class DBServlet
 */
@WebServlet("/StaticInfoServlet")
public class StaticInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaticInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType( "text/html" );
		PrintWriter writer = response.getWriter();
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		
		String requestType = request.getParameter("info");
		
		if (requestType != null && requestType.compareTo("AllDestinations") == 0)
		{
			ArrayList<String> src = new ArrayList<String>();
			ArrayList<Airport> src2 = new ArrayList<Airport>();
			List<Flight> flights = new ArrayList<Flight>();
			
			try 
			{
				flights = FlightsHandler.GetAllFlights(false);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			for (Iterator<Flight> it = flights.iterator(); it.hasNext(); ) {
				Flight flight = it.next();
				int fromAirport = flight.get_from_airport();
				String fromAirportName = flight.get_from_airportName();
				Airport currAirport = StaticDataHandler.GetAirportByID(fromAirport, !it.hasNext());

				if (!src.contains(fromAirportName))
				{
					src.add(fromAirportName);
					src2.add(currAirport);
				}
			}
			
			writer.print(gson.toJson(src2));
		}
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
