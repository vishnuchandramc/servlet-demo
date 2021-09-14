package com.jthread.signin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/rs")
public class ServletLogin extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String pass = req.getParameter("pass");
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
			ResultSet res = st.executeQuery("select password from user WHERE username='"+ name +"';");
			while (res.next()) {
				String password = res.getString("password");
				if(password.equals(pass)) {
					PrintWriter out = resp.getWriter();
					out.println("<link rel=\"stylesheet\" href=\"style.css\">");
					out.println("<title>Welcome</title>");
					out.println("<div class=\"hero-flex\" style = \"align-items : left; justify-content: left; border-radius:20px;\">\n"
							+ "        <div class=\"submit_form\" style=\"margin-left:200px;margin-right:80px;background:none;\">\n"
							+ "            <h1 style=\"font-size:40px !important;margin-left:0 !important;\">Hello "+ name +"</h1>\n"
							+ "            <h2 style=\"font-size:20px !important;margin-left:0 !important;\">Welcome to jthread servlet portal.<br>Choose any option<br><br></h2><br>\n"
							+ "            <a class =\"redirect1\" href=\"student.html\">Student Add</a>"
							+ "            <a class =\"redirect2\" href=\"lst\">Student List</a>"
							+ "        <br><br></div>\n"
							+ "        <div class=\"banner\">.</div>"
							+ "    </div>");
				}else {
					PrintWriter out = resp.getWriter();
					out.println("<link rel=\"stylesheet\" href=\"style.css\">");
					out.println("<title>Invalid</title>");
					out.println("<div class=\"flex\">\n"
							+ "        <form class=\"submit_form\" action=\"rs\" method=\"post\">\n"
							+ "            <h1>Sign in</h1>\n"
							+ "            <h2>Sign in for more details</h2>\n"
							+ "            <input type=\"text\" placeholder=\"Username\" name=\"name\" autocomplete=\"off\" required>\n"
							+ "            <input type=\"password\" placeholder=\"Password\" name=\"pass\" autocomplete=\"off\" required> \n"
							+ "            <input type=\"submit\" value=\"submit\"/> \n"
							+ " 			<br><center style=\"color : #ed333b;!important\">Invalid Username and Password</center>"
							+ "        </form>\n"
							+ "    </div>");
					}
			}
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
