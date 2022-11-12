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

import cs3220.model.PatientViewModel;
import cs3220.model.VaccineEntry;

/**
 * Servlet implementation class Patient
 */
@WebServlet("/Patient")
public class Patient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Patient() {
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
			ResultSet rs = stmt.executeQuery("select * " + "From patients " + "inner join vaccines "
					+ "where patients.vaccine_id = vaccines.id");
			// Creating vaccine list
			List<PatientViewModel> patients = new ArrayList<PatientViewModel>();
			while (rs.next()) {
				PatientViewModel patient = new PatientViewModel();
				patient.setPatientId(rs.getInt("patients.id"));
				patient.setPatientName(rs.getString("first_name") + " " + rs.getString("last_name"));
				patient.setVaccineId(rs.getInt("vaccine_id"));
				patient.setVaccineName(rs.getString("name"));
				patient.setVaccineDosesRequired(String.valueOf(rs.getInt("doses_required")));
				patient.setVaccineDoseLeft(rs.getInt("total_doses_left"));
				patient.setFirstDoseDate(String.valueOf(rs.getDate("first_dose_date")));
				patient.setSecondDoseDate(String.valueOf(rs.getDate("second_dose_date")));
				patient.setRecievedSecondDose(rs.getBoolean("recieved_second_dose"));
				patients.add(patient);
			}
			// Pass the list to view
			request.setAttribute("patients", patients);
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

		request.getRequestDispatcher("/WEB-INF/Patient.jsp").forward(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DateTimeFormatter dateFormatted = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.now();

		int vaccineID = Integer.parseInt(request.getParameter("vaccineId"));
		int patientID = Integer.parseInt(request.getParameter("patientId"));
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

			String sql = "UPDATE vaccines " + "SET total_doses_left = total_doses_left - 1 Where id = " + vaccineID;
			String sql2 = "UPDATE patients SET second_dose_date = '" + dateFormatted.format(date) + "' , recieved_second_dose = 1 WHERE id = " + patientID;

			stmt.executeUpdate(sql);
			stmt.executeUpdate(sql2);
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

		response.sendRedirect("Patient");
	}

}
