package med_app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class Patient
 */
@WebServlet("/Patient")
public class Patient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DataSource datasource = null;

	sql query = new sql();
	String html = "<h4> Search Results: </h4>";
	
	private String AMKA;

	public void init() throws ServletException {
		try {

			InitialContext ctx = new InitialContext();
			datasource = (DataSource) ctx.lookup("java:comp/env/jdbc/LiveDataSource");
		} catch (Exception e) {
			throw new ServletException(e.toString());
		}

	}

	public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			Connection con = datasource.getConnection();

			String AMKA = request.getParameter("AMKA");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String gender = request.getParameter("gender");

			PreparedStatement stmt = con.prepareStatement(query.Register());
			stmt.setLong(1, Long.parseLong(AMKA));
			stmt.setString(2, username);
			stmt.setString(3, password);
			stmt.setString(4, name);
			stmt.setString(5, surname);
			stmt.setString(6, gender);

			stmt.executeUpdate();
			stmt.close();
			con.close();

			response.sendRedirect("success.jsp");

		} catch (Exception e) {
			System.out.println(e);
			response.sendRedirect("error.jsp");
		}
	}

	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			Connection con = datasource.getConnection();

			String AMKA = request.getParameter("AMKA");
			String password = request.getParameter("password");

			PreparedStatement stmt = con.prepareStatement(query.Patientlogin(AMKA, password));
			ResultSet rs = stmt.executeQuery();
				if (rs.isBeforeFirst()) {
				while (rs.next()) {
					// String username = request.getParameter("username");
					String name = rs.getString("name");
					String surname = rs.getString("surname");
					String gender = rs.getString("gender");
	
					String type;
					if (gender.equals("Male"))
						type = "Mr.";
					else
						type = "Ms.";
	
					response.sendRedirect("pprofile.jsp?surname=" + type + surname);
					this.AMKA = AMKA; //use for other methods
				}
				}
				else
					response.sendRedirect("error.jsp");
			rs.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
			response.sendRedirect("error.jsp");
		}

	}

	public void SearchDoctor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			Connection con = datasource.getConnection();

			String specialty = request.getParameter("specialty");
			String region = request.getParameter("region");
			String myAMKA = this.AMKA;
			PreparedStatement stmt = con.prepareStatement(query.Search(specialty, region));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String doctorAMKA = rs.getString("doctorAMKA");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String menu = "<form method=\"post\" action=\"Appointments\"><input type=\"hidden\" name=\"method\" value=\"select\"><input type=\"hidden\" name=\"doctorAMKA\" value=\""+doctorAMKA+"\"><input type=\"hidden\" name= \"myAMKA\" value =\""+myAMKA+"\"><input type=\"submit\" value=\"See Available Dates\"></form>";
				html += createHTML(name, surname, menu);
			}
			response.sendRedirect("view_doctors.jsp?data="+html);
			rs.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
			response.sendRedirect("error.jsp");
		}
	}

	public void view(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			Connection con = datasource.getConnection();
			String html = "Appointments";
			String view = request.getParameter("view");
			if (view.equals("month")) {
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Timestamp start = new Timestamp(System.currentTimeMillis());
				
				int month = Integer.parseInt(request.getParameter("pick_month")); 
				PreparedStatement stmt = con.prepareStatement(query.view_month(AMKA, month ));
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String id = rs.getString("id");
					String t = rs.getTimestamp("t").toString()+" ";
					String dname = rs.getString("dname");
					String dsurname = rs.getString("dsurname");
					String name = rs.getString("name");
					String menu = "<form method=\"post\" action=\"Patient\"><input type=\"hidden\" name=\"method\" value=\"delete\"><input type=\"hidden\" name=\"id\" value=\""+id+"\"><input type=\"submit\" value=\"Delete\"></form>";
					html += createHTMLRow(t, dname, dsurname, name, menu);
			}
				response.sendRedirect("view_month.jsp?data="+html);
				rs.close();
				con.close();
			}
			else { 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Timestamp start = new Timestamp(System.currentTimeMillis());
			    Calendar cal = Calendar.getInstance();
			    cal.setTimeInMillis(start.getTime());
			    cal.add(Calendar.DAY_OF_MONTH, 7);
				Timestamp end = new Timestamp(cal.getTime().getTime());
				String s = start.toString();
				String e = end.toString();
				s = sdf.format(start);
				e = sdf.format(end);
				PreparedStatement stmt = con.prepareStatement(query.view_week(AMKA, s, e));
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String id = rs.getString("id");
					String t = rs.getTimestamp("t").toString()+" ";
					String dname = rs.getString("dname");
					String dsurname = rs.getString("dsurname");
					String name = rs.getString("name");
					String menu = "<form method=\"post\" action=\"Patient\"><input type=\"hidden\" name=\"method\" value=\"delete\"><input type=\"hidden\" name=\"id\" value=\""+id+"\"><input type=\"submit\" value=\"Delete\"></form>";
					html += createHTMLRow(t, dname, dsurname, name, menu);
			}
				response.sendRedirect("view_month.jsp?data="+html);
				rs.close();
				con.close();
			}


	} catch (Exception e) {
		System.out.println(e);
		response.sendRedirect("error.jsp");
	}

			
	}
	
	public void delete_app(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			Connection con = datasource.getConnection();
			int appid = Integer.parseInt(request.getParameter("id"));
			
			PreparedStatement stmt = con.prepareStatement(query.delete_app());
			stmt.setInt(1, appid);

			stmt.executeUpdate();
			stmt.close();
			con.close();

			response.sendRedirect("success.jsp");

		} catch (Exception e) {
			System.out.println(e);
			response.sendRedirect("error.jsp");
		}
		
	}
	
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

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = request.getParameter("method");

		if (method.equals("register"))
			register(request, response);
		else if (method.equals("login"))
			login(request, response);
		else if (method.equals("search"))
			SearchDoctor(request, response);
		else if (method.equals("view"))
			view(request, response);
		else if (method.equals("delete"))
			delete_app(request, response);
	}

	private String createHTMLRow(String t,String dname, String dsurname, String name, String menu) {
		String row = "<tr>";
		row += "<td>" + t+ "</td>";
		row += "<td>" + dname + "</td>";
		row += "<td>" + dsurname + "</td>";
		row += "<td>" + name + "</td>";
		row += "<td>" + menu + "</td>";
		row += "</tr>";
		return row;
	}
	
	private String createHTML(String dname, String dsurname, String menu) {
		String row = "<tr>";
		row += "<td>" + dname + "</td>";
		row += "<td>" + dsurname + "</td>";
		row += "<td>" + menu + "</td>";
		row += "</tr>";
		return row;
	}



}
