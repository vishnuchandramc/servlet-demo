package com.jthread.signin;

import com.jthread.signin.ServletList;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletDelete extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String stdName = req.getParameter("hidden");
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
				st.executeUpdate("delete from students where name = '"+ stdName +"';");
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
