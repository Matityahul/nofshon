package vacation.servlet;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vacation.DAL.*;
import vacation.model.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType( "text/html" ); 
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		
		String searchType = request.getParameter("searchtype");
        
		try
		{
			if (searchType != null && searchType.compareTo("flight") == 0)
			{
				String start = request.getParameter("start");
				String end = request.getParameter("end");
				String fromAirport = request.getParameter("fromairport");
				String toAirport = request.getParameter("toairport");
				String bottomCost = request.getParameter("bottomcost");
				String topCost = request.getParameter("topcost");
				
				Date startDate = null;
				Date endDate  = null;
				Integer from = null;
				Integer to = null;
				Integer min = null;
				Integer max = null;
				
				if (start != null && start != "")
				{
					startDate = Date.valueOf(start);
				}
				if (end != null && end != "")
				{
					endDate = Date.valueOf(end);
				}
				
				if (fromAirport != null && fromAirport != "")
				{
					from = Integer.parseInt(fromAirport);
				}
				
				if (toAirport != null && toAirport != "")
				{
					to = Integer.parseInt(toAirport);
				}
				
				if (bottomCost != null && bottomCost != "")
				{
					min = Integer.parseInt(bottomCost);
				}
				
				if (topCost != null && topCost != "")
				{
					max = Integer.parseInt(topCost);
				}
				
				List<Flight> flights = FlightsHandler.GetFilterdFlights(startDate, endDate, from, to, min, max);
				
		        out.println(gson.toJson(flights));
			}
			else if (searchType != null && searchType.compareTo("hotel") == 0)
			{
				String name = request.getParameter("name");
				String city = request.getParameter("city");
				String bottomCost = request.getParameter("bottomcost");
				String topCost = request.getParameter("topcost");
				
				Integer min = null;
				Integer max = null;
				Integer cityID = null;
				
				if (city != null && city != "")
				{
					cityID = Integer.parseInt(city);
				}
				
				if (bottomCost != null && bottomCost != "")
				{
					min = Integer.parseInt(bottomCost);
				}
				
				if (topCost != null && topCost != "")
				{
					max = Integer.parseInt(topCost);
				}
				
				List<Hotel> hotels = HotelsHandler.GetFilterdHotels(name, cityID, min, max);
				
		        out.println(gson.toJson(hotels));
			}
			else if (searchType != null && searchType.compareTo("HotelsByFlight") == 0)
			{
				String flight = request.getParameter("flightID");
				
				Integer flightID = null;;
				
				if (flight != null && flight != "")
				{
					flightID = Integer.parseInt(flight);
				}
							
				List<Hotel> hotels = HotelsHandler.GetHotelsByFlightID(flightID);
				
		        out.println(gson.toJson(hotels));
			}
			else if (searchType != null && searchType.compareTo("ReturnFlights") == 0)
			{
				String flight = request.getParameter("flightID");
				String nights = request.getParameter("nightsNumber");
				
				Integer flightID = null;;
				Integer nightsNumber = null;;
				
				if (flight != null && flight != "")
				{
					flightID = Integer.parseInt(flight);
				}
				
				if (nights != null && nights != "")
				{
					nightsNumber = Integer.parseInt(nights);
				}
							
				List<Flight> flights = FlightsHandler.GetReturnFlights(flightID, nightsNumber);
				
		        out.println(gson.toJson(flights));

			}
		}
		catch (Exception ex)
		{
			out.println("<P>Exception happened<br>" + ex.getMessage());
		}
		finally
		{
	        out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
