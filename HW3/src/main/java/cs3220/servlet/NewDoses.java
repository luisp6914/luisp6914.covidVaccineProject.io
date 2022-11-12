package cs3220.servlet;

import java.io.IOException;
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
 * Servlet implementation class NewDoses
 */
@WebServlet("/NewDoses")
public class NewDoses extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NewDoses() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get data from data base
		Connection c = null;
		try {
			// creating connection
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu24";
			String username = "cs3220stu24";
			String password = "fthgmrZji4Iz";
			// getting data
			c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from vaccines");
			// Creating vaccine list
			List<VaccineEntry> entries = new ArrayList<VaccineEntry>();
			while (rs.next()) {
				VaccineEntry entry = new VaccineEntry();
				entry.setId(rs.getInt("id"));
				entry.setVaccine(rs.getString("name"));
				entry.setDosesRequired(rs.getInt("doses_required"));
				entry.setDaysBetweenDoses(String.valueOf(rs.getInt("days_between_doses")));
				entry.setTotalDosesReceived(rs.getInt("total_doses_received"));
				entry.setTotalDosesLeft(rs.getInt("total_doses_left"));
				entries.add(entry);
			}
			// Pass the list to view
			request.setAttribute("entries", entries);
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

		request.getRequestDispatcher("/WEB-INF/NewDoses.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int dosesRecieved = Integer.parseInt(request.getParameter("newDosesReceived"));
		int dosesLeft = dosesRecieved;
		int id = Integer.parseInt(request.getParameter("vaccine"));

		// connect to data
		Connection c = null;
		try {
			// creating connection
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu24";
			String username = "cs3220stu24";
			String password = "fthgmrZji4Iz";
			// creating data
			c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM vaccines Where id = " + id);

			// Updating vaccine list
			int currentDosesRecieved = 0;
			int currentDosesLeft = 0;
			while (rs.next()) {
				currentDosesRecieved = rs.getInt("total_doses_received");
				currentDosesLeft = rs.getInt("total_doses_left");
			}
			
			if(currentDosesRecieved == 0 && currentDosesLeft == 0) {
				String sql1 = "UPDATE vaccines "
						    + "SET total_doses_received = " + dosesRecieved + ", total_doses_left = " + dosesLeft
						    + " WHERE id = " + id;
				stmt.executeUpdate(sql1);
			}
			else {
				String sql2 ="UPDATE vaccines "
					    + "SET total_doses_received = total_doses_received + " + dosesRecieved + ", total_doses_left = total_doses_left + " + dosesLeft
					    + " WHERE id = " + id;
				stmt.executeUpdate(sql2);
			}
			
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
