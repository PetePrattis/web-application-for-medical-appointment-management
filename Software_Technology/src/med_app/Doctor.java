package med_app;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class Doctor
 */
@WebServlet("/Doctor")
public class Doctor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DataSource datasource = null;

	sql query = new sql();
	private String AMKA;
	
	public void init() throws ServletException {
		try {

			InitialContext ctx = new InitialContext();
			datasource = (DataSource) ctx.lookup("java:comp/env/jdbc/LiveDataSource");
		} catch (Exception e) {
			throw new ServletException(e.toString());
		}

	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) {
		Connection con;

		try {
			con = datasource.getConnection();

		String AMKA = request.getParameter("AMKA");
		this.AMKA = AMKA;
		String password = request.getParameter("password");

		//PrintWriter pw = response.getWriter();

		// pw.println("<h1> Welcome "+AMKA+"</h1> <br/>");
		// pw.println("<h2> Your Password is "+password+"</h2>");

		PreparedStatement stmt = con.prepareStatement(query.Doctorlogin(AMKA,password));
		ResultSet rs = stmt.executeQuery();
		if (rs.isBeforeFirst()) {
			while (rs.next()) {
				//String r_name = rs.getString("name");
				String r_surname = rs.getString("surname");
				//String r_specialty = rs.getString("specialty");
				//String r_region = rs.getString("region");
				
				
				System.out.println("Welcome Dr." + r_surname);
				response.sendRedirect( "dprofile.jsp?surname=" + r_surname );
				
				con.close();
				rs.close();
			}
		}
		else
			response.sendRedirect("error.jsp");
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void set_app(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			int new_id = 1;
			PreparedStatement prestmt = con.prepareStatement(query.get());
			ResultSet rs = prestmt.executeQuery();
			while (rs.next()) {
				new_id += rs.getInt("m");
			}
			rs.close();
			
			String t = request.getParameter("timestamp");
			
			PreparedStatement stmt = con.prepareStatement(query.set());
			
			//HOW TO CHECK IF (T,DOCTORAMKA) IS UNIQUE?
			
			stmt.setInt(1, new_id);
			stmt.setTimestamp(2,Timestamp.valueOf(t));
			stmt.setLong(3, Long.parseLong(AMKA));
			stmt.executeUpdate();
			stmt.close();
			con.close();

			response.sendRedirect("success.jsp");

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Doctor() {
		super();

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

		if (method.equals("login"))
			login(request,response);
		else if (method.equals("availability"))
			set_app(request,response);

	}
	
}
