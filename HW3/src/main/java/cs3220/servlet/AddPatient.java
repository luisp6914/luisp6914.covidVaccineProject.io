package cs3220.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.model.VaccineEntry;

/**
 * Servlet implementation class AddPatient
 */
@WebServlet("/AddPatient")
public class AddPatient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddPatient() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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

		request.getRequestDispatcher("/WEB-INF/AddPatient.jsp").forward(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DateTimeFormatter dateFormatted = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.now();
		String patientFirstName = request.getParameter("patientFirstName");
		String patientLastName = request.getParameter("patientLastName");
		String vaccineName = request.getParameter("patientVaccine");

		Connection c = null;
		try {
			// creating connection
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu24";
			String username = "cs3220stu24";
			String password = "fthgmrZji4Iz";
			// creating data
			c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id FROM vaccines Where name = '" + vaccineName + "'");
			
			// Updating vaccine list
			int vaccineId = 0;
			while (rs.next()) {
				vaccineId = rs.getInt("id");
			} 
			
			//Creating new patient
			String sql = "insert into patients(first_name, last_name, vaccine_id, first_dose_date) values ('"
					+ patientFirstName + "', '" + patientLastName + "'," + vaccineId + ",'" + dateFormatted.format(date)
					+ "')";
			stmt.executeUpdate(sql);
			
			//updating vaccine
			String sql3 = "UPDATE vaccines SET total_doses_left = total_doses_left - 1" + " Where name = '"
					+ vaccineName + "'";
			stmt.executeUpdate(sql3);
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

//		List<PatientEntry> patients = (List<PatientEntry>)getServletContext().getAttribute("patients");
//		patients.add(patient);
//		
//		List<VaccineEntry> entries = (List<VaccineEntry>)getServletContext().getAttribute("entries");
//		for(VaccineEntry e: entries) {
//			if(request.getParameter("patientVaccine").equals(e.getVaccine())) {
//				e.setTotalDosesLeft(e.getTotalDosesLeft() - 1);
//			}
//		}

		response.sendRedirect("Patient");

	}

}
