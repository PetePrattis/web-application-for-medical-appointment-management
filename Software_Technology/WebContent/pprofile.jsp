<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Your Profile</title>
</head>
<center>
<body style="background-image:url(http://www.sprintonweb.com/wp-content/uploads/2017/12/056-Shady-Water.png)">
<br>
	<h1>
		Welcome
		<%=request.getParameter("surname")%></h1>
	</br>
	<form method="post" action="Patient">
			<input type="hidden" name="method" value="view">
			<input type="hidden" name="view" value="month">
			<p>Pick a month:</p>
			<select name="pick_month">
			<option value="1">January</option>
			<option value="2">February</option>
			<option value="3">March</option>
			<option value="4">April</option>
			<option value="5">May</option>
			<option value="6">June</option>
			<option value="7">July</option>
			<option value="8">August</option>
			<option value="9">September</option>
			<option value="10">October</option>
			<option value="11">November</option>
			<option value="12">December</option>
			</select>
			<input type="submit" value="My Appointments for this month">
	</form>
	</br>
		<form method="post" action="Patient">
			<input type="hidden" name="method" value="view">
			<input type="hidden" name="view" value="week">
			<input type="submit" value="My Appointments for next week">
	</form>
	</br>
	<a href="searchdoctor.jsp">New Appointment</a>
	</br></br>
	<form method="get" action="Patient">
	<input type="submit" value="Log out">
	</form>
</body>
</center>
</html>