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
 * Servlet implementation class EditEntry
 */
@WebServlet("/EditEntry")
public class EditEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditEntry() {
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

		request.getRequestDispatcher("/WEB-INF/EditEntry.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int vaccineID = Integer.parseInt(request.getParameter("id"));
		String vaccineName = request.getParameter("vaccineName");
		String daysBetweenDoses = request.getParameter("daysBetweenDoses");

		// insert data into data base
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

			String sql = "UPDATE vaccines SET name = '" + vaccineName + "', days_between_doses = " + daysBetweenDoses
					+ " Where id = " + vaccineID;

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
