package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 PrintWriter out = response.getWriter();
		/*	String email = request.getParameter("email");
			String password = request.getParameter("password");
			
				Configuration conref = new Configuration();
				conref.configure("hibernateER.cfg.xml");
				SessionFactory sfref = conref.buildSessionFactory();
				Session sref = sfref.openSession();
				Transaction tref = sref.beginTransaction();
				Query qref = sref.createQuery("from EmployerSignUpPojo where email = :eref and password = :pref");
				qref.setParameter("eref", email);
				qref.setParameter("pref", password);
				List ls = qref.list();
				Iterator itref = ls.iterator();
				if(itref.hasNext()) {
					 */
		 
		 
		 
			
					   try {
							 int employeecount = 0, employercount = 0, jobcount = 0;
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "root","1234");
							String sql = " select count(id) from employee";
							String sqi = "select count(id) from employer";
							String sqa = "select count(id) from applications";
							String sqs = "select skills from employerjp where active = 0";
							PreparedStatement psl = con.prepareStatement(sql);
							 ResultSet rsl = psl.executeQuery();
							 if (rsl.next()) {
								  employeecount = rsl.getInt(1);
								
								
							 }							 
							 PreparedStatement psi = con.prepareStatement(sqi);
							 ResultSet rsi = psi.executeQuery();
							 if (rsi.next()) {
								  employercount = rsi.getInt(1);
								
								
							 }		
							 PreparedStatement psa = con.prepareStatement(sqa);
							 ResultSet rsa = psa.executeQuery();
							 if (rsa.next()) {
								  jobcount = rsa.getInt(1);
								
								
							 }	
							 
								
							 out.println("<p>Total no of Employers: </p>");
							 out.print(employercount);
							 out.println("<p>Total no of Employees: </p>");
							  out.print(employeecount);
							  out.println("<p>Total no of applications: </p>");
							  out.print(jobcount);
							  out.println("<p>Skills that are not present in Database: </p>");
							  PreparedStatement pss = con.prepareStatement(sqs);
								 ResultSet rss = pss.executeQuery();
								 while (rss.next()) {
									out.println(rss.getString(1));
									 
									
									
								 }	
						       
						    } catch(Exception e) {
								 e.printStackTrace();
									System.out.println(e.getMessage());
								}
								
						
					
					
			//	}
				//else {}
				
	}

}
