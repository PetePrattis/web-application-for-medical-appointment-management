package med_app;

import java.sql.Timestamp;

public class sql {
	
	public String Doctorlogin(String AMKA, String password) {
		String s = "SELECT * FROM doctor WHERE doctoramka=" + AMKA + "AND password='" + password + "'";
		return(s);
	}

	public String Patientlogin(String AMKA, String password) {
		String s = "SELECT * FROM patient WHERE patientamka=" + AMKA + "AND password='" + password + "'";
		return(s);
	}
	
	public String Register() {
		String s = "INSERT INTO patient(patientamka,userid,password,name,surname,gender) VALUES (?,?,?,?,?,?)";
		return(s);
	}
	
	public String Search(String specialty,String region) {
		String s = "SELECT doctorAMKA,doctor.name,surname FROM doctor WHERE specialty='" + specialty + "' AND region='" + region + "'";
		return(s);
	}
	
	//appointments.id as appid
	public String view_month(String AMKA,int month) {
		String s = "SELECT appointments.id,t, doctor.name as dname,doctor.surname as dsurname, departments.name FROM appointments natural join doctor inner join departments on specialty = departments.id where patientamka =" + AMKA +" and date_part('month',t) = "+month;
	return(s);
	}
	
	public String view_week(String AMKA,String week_start,String week_end) {
		String s = "SELECT  appointments.id,t, doctor.name as dname,doctor.surname as dsurname, departments.name FROM appointments natural join doctor inner join departments on specialty = departments.id where patientamka =" + AMKA +" and t >= '"+week_start+"' and t < '"+week_end+"'";
	return(s);
	}
	
	public String delete_app() {
		String s = "DELETE FROM appointments where id = ?";
	return(s);
	}
	
	public String search_empty(String AMKA) {
		String s = "select * from appointments where patientAMKA = 0 AND doctorAMKA ="+AMKA;
		return(s);
	}
	
	public String update() {
		String s = "update appointments set patientAMKA = ?, diagnosis = ? where id = ?";
		return(s);
	}
	  
	public String get() {
		String s = "select MAX(id) as m FROM appointments";
		return(s);
	}
	
	public String set() {
		String s = "INSERT INTO appointments(id,t,patientamka,doctoramka,diagnosis) VALUES (?,?,0,?,NULL)";
		return(s);
	}

}
