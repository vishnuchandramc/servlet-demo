package com.jthread.signin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class ServletEdit extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String stdName = req.getParameter("hidden1");
		String stdAge = req.getParameter("hidden2");
		String stdEmail = req.getParameter("hidden3");
		String stdPhone = req.getParameter("hidden4");
		
		PrintWriter out = resp.getWriter();
		out.println("<link rel=\"stylesheet\" href=\"style.css\">");
		out.println("<title>Update Entry</title>");
		out.println(" <div class=\"flex\">\n"
				+ "		<form class=\"submit_form\" action=\"stedit\" method=\"get\">\n"
				+ "			<h1>Update student details</h1>\n"
				+ "			<input style= \"pointer-events: none; color : gray; \" type=\"text\" placeholder=\"Name\" name=\"std_name\" autocomplete=\"off\" value = \""+ stdName +"\" required>\n"
				+ "			<input type=\"text\" placeholder=\"Age\" name=\"std_age\" autocomplete=\"off\" value =\""+Integer.parseInt(stdAge)+"\" required>\n"
				+ "			<input type=\"text\" placeholder=\"Email\" name=\"std_mail\" autocomplete=\"off\" value = \""+ stdEmail +"\" required>\n"
				+ "			<input type=\"text\" placeholder=\"Phone\" name=\"std_phone\" autocomplete=\"off\" value = \""+ stdPhone +"\" required>\n"
				+ "			<input type=\"submit\" value=\"Save\"/>\n"
				+ "		</form>\n"
				+ "	</div>");
	}
}
