package com.jthread.signin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jthread.signin.ServletLogin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/std")
public class StudentLogin extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String stdName = req.getParameter("std_name");
		String stdAge = req.getParameter("std_age");
		String stdMail = req.getParameter("std_mail");
		String stdPhone = req.getParameter("std_phone");
		
			
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
			st.executeUpdate("insert into students values('"+ stdName +"',"+Integer.parseInt(stdAge)+",'"+stdMail+"', '"+stdPhone+"');");
			
			PrintWriter out = resp.getWriter();
			out.println("<link rel=\"stylesheet\" href=\"style.css\">");
			out.println("<title>Welcome</title>");
			out.println("<div class=\"flex\">\n"
					+ "        <form class=\"submit_form\" action=\"std\" method=\"post\">\n"
					+ "            <h1>Enter student details</h1>\n"
					+ "            <input type=\"text\" placeholder=\"Name\" name=\"std_name\" autocomplete=\"off\" required>\n"
					+ "            <input type=\"text\" placeholder=\"Age\" name=\"std_age\" autocomplete=\"off\" required> \n"
					+ "            <input type=\"text\" placeholder=\"Email\" name=\"std_mail\" autocomplete=\"off\" required> \n"
					+ "            <input type=\"text\" placeholder=\"Phone\" name=\"std_phone\" autocomplete=\"off\" required> \n"
					+ "            <input type=\"submit\" value=\"Save\"/> \n"
					+ "            <br><center style=\"color : #33d17a;!important\">Successfully Saved</center>\""
					+ "        </form>\n"
					+ "    </div>");
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

