package cs3220.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.model.VaccineEntry;

/**
 * Servlet implementation class NewVaccine
 */
@WebServlet("/NewVaccine")
public class NewVaccine extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NewVaccine() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/NewVaccine.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String vaccineName = request.getParameter("vaccineName");
		int dosesRequired = Integer.parseInt(request.getParameter("dosesRequired"));
		int daysBetweenDoses = Integer.parseInt(request.getParameter("daysBetweenDoses"));
		
		Connection c = null;
		try {
			// creating connection
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu24";
			String username = "cs3220stu24";
			String password = "fthgmrZji4Iz";
			// creating data
			c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();
			String sql = "insert into vaccines(name, doses_required, days_between_doses) values ('"
					+ vaccineName + "', " + dosesRequired + "," + daysBetweenDoses + ")";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new ServletException(e);
		} finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}
		response.sendRedirect("Vaccine");
	}

}
