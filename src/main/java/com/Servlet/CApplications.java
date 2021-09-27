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
 * Servlet implementation class CApplications
 */
@WebServlet("/CApplications")
public class CApplications extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		 ServletContext context = getServletContext();
	     String email=(String)context.getAttribute("email");
	    
	    int employeeId = 0;
	    try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "root","1234");
			String sql = "select employeeId from Applications where  employerEmail = '"+email+"'";
			
			PreparedStatement psl = con.prepareStatement(sql);
			
			 ResultSet rsl = psl.executeQuery();
			 out.print("<table border = '1' width = '50%'>");
			 out.print("<tr><th>id</th><th>name</th><th>email</th><th>mobile</th><th>skills</th><th>experience</th><th>jobrole</th><th>plocation</th></tr>");
			 while(rsl.next()) {
				   employeeId = rsl.getInt(1);
				  
				
				
				  Configuration conref = new Configuration();
				  conref.configure("hibernateE.cfg.xml");
				  SessionFactory sfref = conref.buildSessionFactory();
				  Session sref = sfref.openSession();
				  Transaction tref = sref.beginTransaction();
				
				  Query sqref = sref.createQuery("select id, name, email,mobile,skills, experience, jobrole, plocation from EmployeeSignUpPojo where id = :er");
					sqref.setParameter("er", employeeId);
					List lsref = sqref.list();
					Iterator itsref = lsref.iterator();
					Object o[] = null;
					 
					while(itsref.hasNext()) {
						
						 o = (Object[])itsref.next();
						//out.println(o[0]);
			        out.println("<tr><td>"+o[0]+"</td><td>"+o[1]+"</td><td>"+o[2]+"</td><td>"+o[3]+"</td><td>"+o[4]+"</td><td>"+o[5]+"</td><td>"+o[6]+"</td><td>"+o[7]+"</td></tr>");

					}
					
					tref.commit();
					sref.close();
					sfref.close();
					
				  
			 }							 
			 out.println("</table>");	
			 
				
			 	
		       
		    } catch(Exception e) {
				 e.printStackTrace();
					System.out.println(e.getMessage());
				}
				
	   
		
		
	}

}
