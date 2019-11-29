<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Appointments Application</title>

</head>
<center>
<body style="background-image:url(http://www.sprintonweb.com/wp-content/uploads/2017/12/056-Shady-Water.png)">
<br>
<form method="post" action="Doctor">
  <input type="hidden" name="method" value="login">
  <h1 style="color:#990000">Welcome to the Hospital Website</h1><br>
  <h2>Sign on as a Doctor</h2>
  AMKA:<br>
  <input type="text" name="AMKA" >
  <br>
  Password:<br>
  <input type="password" name="password">
  <br><br>
  <input type="submit" value="Submit">
</form> 
  <br><br>
<form method="post" action="Patient">
  <input type="hidden" name="method" value="login">
  <h2>Sign on as a Patient</h2>
  AMKA:<br>
  <input type="text" name="AMKA" >
  <br>
  Password:<br>
  <input type="password" name="password">
  <br><br>
  <input type="submit" value="Submit">
</form> 
<br><br>
<h4>To sign in with a new account click register:</h4>
<a href="register.jsp">Register</a>

</body>
</center>
</html>