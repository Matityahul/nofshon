package vacation.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import vacation.DAL.*;
import vacation.model.*;

/**
 * Servlet implementation class CookieServlet
 */
@WebServlet("/CookieServlet")
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType( "text/html" ); 
		Cookie[] cookies = request.getCookies();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String id = null;
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
					if (cookie.getName().equals("UserID"))
					{
						id = cookie.getValue();
					}
				}
			}
		
		if (id == null || id.length() ==0 )
		{
			out.write("{}");
		}
		else
		{
			User user = UsersHandler.GetUser(Integer.parseInt(id), false);
			out.write(gson.toJson(user));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
