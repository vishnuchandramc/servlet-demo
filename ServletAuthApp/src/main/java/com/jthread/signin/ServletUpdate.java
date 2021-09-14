package com.jthread.signin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletUpdate extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("std_name");
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
				st.executeUpdate("update students set age = "+ stdAge +", email = '"+ stdMail +"', phone = '"+ stdPhone +"'  where name = '"+ name +"';");
				resp.sendRedirect("/ServletAuthApp/lst");
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
