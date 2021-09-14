package com.jthread.signin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletList extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection con = null;
		String db = "logindb";
		String url = "jdbc:mysql://localhost:3306/"+db;
		String driver = "com.mysql.cj.jdbc.Driver";
		String user = "root";
		String passwd = "Mypassword@123";
		try{
			Class.forName(driver);  //1st step
			con = DriverManager.getConnection(url, user, passwd); //2nd step
			try{
			Statement st = con.createStatement();
			ResultSet res = st.executeQuery("select * from students;");
					PrintWriter out = resp.getWriter();
//					out.println("<h1>"+ res.getString("name") +"</h1>");
					out.println("<link rel=\"stylesheet\" href=\"style.css\">");
					out.println("<link rel=\"stylesheet\" href=\"https://unicons.iconscout.com/release/v4.0.0/css/line.css\">");
					out.println("<title>Student List</title>");
					out.println("<div class=\"flex\">\n"
							+ "        <div class=\"std_list\">\n"
							+ "        <h1>Student List</h1>\n"
							+ "<table class=\"table\">");
							while (res.next()) {
								out.println("<tr><td><span class=\"names\">"+ res.getString("name") + "</span></td> "
										+ "<td>"
										+ "<form class= \"hidden-form\" action = \"edit\" method = \"get\"> "
										+ "<input class = \"hidden-input\" type = \"text\" value = \""+ res.getString("name") +"\" name = \"hidden1\">"
										+ "<input class = \"hidden-input\" type = \"text\" value = \""+ res.getString("age") +"\" name = \"hidden2\">"
										+ "<input class = \"hidden-input\" type = \"text\" value = \""+ res.getString("email") +"\" name = \"hidden3\">"
										+ "<input class = \"hidden-input\" type = \"text\" value = \""+ res.getString("phone") +"\" name = \"hidden4\">"		
										+ "<button class = \"icon_button\" type = \"submit\" style = \"	background : none !important;\n"
										+ "	border : none !important;	padding-left : 10px;\n"
										+ "	color : #26a269;\n"
										+ "	font-size : 18px;\n"
										+ "	cursor : pointer;\">"
										+ "<i class=\"uil uil-pen\"></i></button></form>"
										
					
										+ "<form class= \"hidden-form\" action = \"del\" method = \"get\"> "
										+ "<input class = \"hidden-input\" type = \"text\" value = \""+ res.getString("name") +"\" name = \"hidden\">"
										+ "<button class = \"icon_button\" type = \"submit\" style = \"	background : none !important;\n"
										+ "	border : none !important;	padding-left : 10px;\n"
										+ "	color : #ed333b;\n"
										+ "	font-size : 18px;\n"
										+ "	cursor : pointer;\">"
										+ "<i class=\"uil uil-trash-alt\"></i></button></form></td></tr>");
							}
					out.println("</table><br><br></div></div>");
							
				
			}catch (SQLException s){
				System.out.println("SQL code does not execute.");
			}
			}
			catch (Exception e){
				e.printStackTrace();
			}finally{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
