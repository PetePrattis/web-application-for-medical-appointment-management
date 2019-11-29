package med_app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class Appointments
 */
@WebServlet("/Appointments")
public class Appointments extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DataSource datasource = null;
	
	sql query = new sql();
	
	public void init() throws ServletException {
		try {

			InitialContext ctx = new InitialContext();
			datasource = (DataSource) ctx.lookup("java:comp/env/jdbc/LiveDataSource");
		} catch (Exception e) {
			throw new ServletException(e.toString());
		}

	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Appointments() {
        super();
        // TODO Auto-generated constructor stub
    }

	String myAMKA ;
	String doctorAMKA ;
	Timestamp t;
	String diagnosis;
	int id;
    
	public void availability(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();

			myAMKA = request.getParameter("myAMKA");
			doctorAMKA = request.getParameter("doctorAMKA");
			
			PreparedStatement stmt = con.prepareStatement(query.search_empty(doctorAMKA));
			
			ResultSet rs = stmt.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) { 
					id = rs.getInt("id");
					t = rs.getTimestamp("t"); 
				}
				response.sendRedirect("reason.jsp");
			}
			else
				response.sendRedirect("error.jsp");
			
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
			
	
	public void close(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			Connection con = datasource.getConnection();
			
			diagnosis = request.getParameter("diagnosis");

			PreparedStatement upd = con.prepareStatement(query.update());
			upd.setLong(1, Long.parseLong(myAMKA));
			upd.setString(2, diagnosis);
			upd.setInt(3, id);

			upd.executeUpdate();
			upd.close();
			con.close();
			
			response.sendRedirect("success.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
		}
	
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
		
		String method = request.getParameter("method");
		
		if (method.equals("select"))
			availability(request, response);
		else if (method.equals("close"))
			close(request, response);
	}

}
