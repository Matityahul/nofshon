package vacation.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import vacation.DAL.BookingHandler;
import vacation.DAL.OrdersHandler;
import vacation.DAL.UsersHandler;
import vacation.model.Order;
import vacation.model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String url = request.getPathInfo();
		
        out.println("<HEAD><TITLE>Vacation servlet test</TITLE></HEAD><BODY>");
        
		try
		{
			// If specific user
			if (url != null)
			{
				String userID = request.getParameter("id");
				List<Order> orders = OrdersHandler.GetOrdersOfUser(Integer.parseInt(userID));
					
			    out.println("<h1>Orders of user " + userID + "<br>");
			    out.println(gson.toJson(orders));	        


			}
			else
			{
				List<User> users = UsersHandler.GetOnlineUsers();
				
		        out.println("<h1>Online Users:" + "<br>");
		        out.println(gson.toJson(users));
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		
		// Get the method to do (insert or update)
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User usr = UsersHandler.GetUser(username, password);
		
        out.println("<HEAD><TITLE>Vacation servlet test</TITLE></HEAD><BODY>");
		
		if (usr != null)
		{
			out.println(gson.toJson(usr));	
		}
		else
		{
			out.println("<P>bad user or password<br>");
		}
		
        out.println("</BODY>");
        out.close();
	}

}
