<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Doctor</title>
</head>
<center>
<body style="background-image:url(http://www.sprintonweb.com/wp-content/uploads/2017/12/056-Shady-Water.png)">
<br>	
	<form method="post" action="Patient">
		<input type="hidden" name="method" value="search">
		 <h2>Select Doctor's Specialty:</h2> 
		 <select name="specialty" size="5">
			<option value="1">Accident and Emergency</option>
			<option value="2">Cardiology</option>
			<option value="3">Gastroenterology</option>
			<option value="4">General Surgery</option>
			<option value="5">Nephrology</option>
			<option value="6">Neurology</option>
			<option value="7">Oncology</option>
			<option value="8">Ophthalmology</option>
			<option value="9">Orthopaedics</option>
			<option value="10">Physiotherapy</option>
			<option value="11">Occupational Therapy</option>
			<option value="12">Radiotherapy</option>
			<option value="13">Urology</option>
			<option value="14">Gynecology</option>
			<option value="15">Breast Screening</option>
		</select> <br> <br> 
		<h2>Select Region:</h2> 
		<select name="region" size="2">
			<option value="Athens">Athens</option>
			<option value="Piraeus">Piraeus</option>
		</select> <br> <br> <br> <input type="submit" value="Search">
	</form>
</body>
</center>
</html>