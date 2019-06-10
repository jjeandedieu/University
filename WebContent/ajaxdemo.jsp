<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ page import="java.sql.*"%>

<%
String s=request.getParameter("val");
if(s==null || s.trim().equals("")){
	out.print("Please enter id");
}else{
	int id=Integer.parseInt(s);
	out.print(id);
	
	try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mdb","root","root");
		PreparedStatement ps=con.prepareStatement("select * from emp where id=?");
		ps.setInt(1,id);
		ResultSet rs=ps.executeQuery();
	
		while(rs.next()){
			out.print(rs.getInt(1)+" "+rs.getString(2));
	}
		
	con.close();
	}
		catch(Exception e){
			
			e.printStackTrace();
	
	}
}
%>

</body>
</html>