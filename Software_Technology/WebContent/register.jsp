<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<center>
<body style="background-image:url(http://www.sprintonweb.com/wp-content/uploads/2017/12/056-Shady-Water.png)">
<br>
<form  action="Patient" method="post">
  <input type="hidden" name="method" value="register">
<h2>Register new Account!</h2> <br> 
AMKA: <input type="text" name="AMKA"><br><br>
Username: <input type="text" name="username"><br><br>
Password: <input type="text" name="password"><br><br>
First name: <input type="text" name="name"><br><br>
Surname: <input type="text" name="surname"><br><br>
Gender: <input type="radio" name="gender" value="Male"> Male
<input type="radio" name="gender" value="Female">Female<br>
<br><br>
<input type="submit"  value="Register">
</form>

</body>
</center>
</html>