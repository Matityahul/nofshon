package vacation.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import vacation.model.User;
import vacation.DAL.UsersHandler;
import vacation.DAL.StaticDataHandler;

/**
 * Servlet implementation class AuthenticateServlet
 */
@WebServlet("/AuthenticateServlet")
public class AuthenticateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType( "text/html" );
		PrintWriter writer = response.getWriter();
		
		String requestType = request.getParameter("requestType");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		if (requestType.compareTo("login") == 0)
		{
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			
			User user = null;
			
			try 
			{
				user = UsersHandler.GetUser(userName, password);
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
			
			if (user != null)
			{
				writer.print("{\"status\":1, \"data\":" +gson.toJson(user) + "}");
			}
			else
			{
				writer.print("{\"status\":0, \"data\":" +gson.toJson(user) + "}");
			}
		}
		else if (requestType.compareTo("register") == 0)
		{
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			
			java.util.Calendar cal = java.util.Calendar.getInstance();
			Date dateNow = new Date(cal.getTimeInMillis());
			
			User newUser = new User(StaticDataHandler.GetNextID("users"), userName, 
					password, address, phone, dateNow, firstName, lastName, email);
			
			try
			{
				UsersHandler.AddUser(newUser);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}

}
