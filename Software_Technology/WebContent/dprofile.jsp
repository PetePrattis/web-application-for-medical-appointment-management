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
<h1>Welcome Dr. <%= request.getParameter("surname") %></h1><br>

<form method="post" action="Doctor">
			<input type="hidden" name="method" value="availability">
			<p>Pick a date to set availability:</p>
			<select name="timestamp">
			<option value="2018-07-02 08:00:00">2018-07-02 08:00:00</option>
			<option value="2018-07-02 09:00:00">2018-07-02 09:00:00</option>
			<option value="2018-07-02 10:00:00">2018-10-02 10:00:00</option>
			<option value="2018-07-03 08:00:00">2018-07-02 10:00:00</option>
			<option value="2018-07-03 09:00:00">2018-07-03 09:00:00</option>
			<option value="2018-07-03 10:00:00">2018-07-03 10:00:00</option>
			<option value="2018-10-02 08:00:00">2018-10-02 08:00:00</option>
			<option value="2018-10-02 09:00:00">2018-10-02 09:00:00</option>
			<option value="2018-10-02 10:00:00">2018-10-02 10:00:00</option>
			</select>
			<input type="submit" value="Set Availability">
	</form>
	</br>
		<form method="get" action="Doctor">
		<input type="submit" value="Log out">
	</form>
</body>
</center>
</html>