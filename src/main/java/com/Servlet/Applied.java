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
 * Servlet implementation class Applied
 */
@WebServlet("/Applied")
public class Applied extends HttpServlet {
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
	     int id = (Integer)context.getAttribute("id");
	    System.out.println(email);
	    System.out.println(id);
	    String employerEmail =null;
	    try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "root","1234");
			String sql = "select employerEmail,jobtitle from Applications where  employeeId = '"+id+"'";
			
			PreparedStatement psl = con.prepareStatement(sql);
			
			 ResultSet rsl = psl.executeQuery();
			 out.print("<table border = '1' width = '50%'>");
			  out.print("<tr><th>jobtitle</th><th>jobtype</th><th>skills</th><th>experience</th><th>interviewdate</th><th>location</th><th>Delete my application</tr>");
			 while(rsl.next()) {
				   employerEmail  = rsl.getString(1);
				  String jobtitle = rsl.getString(2);
				
				
				  Configuration conref = new Configuration();
				  conref.configure("hibernateERJP.cfg.xml");
				  SessionFactory sfref = conref.buildSessionFactory();
				  Session sref = sfref.openSession();
				  Transaction tref = sref.beginTransaction();
				
				  Query sqref = sref.createQuery("select jobtitle, jobtype, skills, experience, interviewdate, location from EmployerJobPostPojo where email = :er");
					sqref.setParameter("er", employerEmail);
					List lsref = sqref.list();
					Iterator itsref = lsref.iterator();
					Object o[] = null;
					 
					while(itsref.hasNext()) {
						 ServletContext servletContext = getServletContext();
				            servletContext.setAttribute("email",employerEmail);
				            servletContext.setAttribute("jobtitle",jobtitle);
						 o = (Object[])itsref.next();
						
			          out.println("<tr><td>"+o[0]+"</td><td>"+o[1]+"</td><td>"+o[2]+"</td><td>"+o[3]+"</td><td>"+o[4]+"</td><td>"+o[5]+"</td><td><a href = 'DApplication.html'/>Delet my application</td></tr>");

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
/*
Configuration conref = new Configuration();
conref.configure("hibernateA.cfg.xml");
SessionFactory sfref = conref.buildSessionFactory();
Session sref = sfref.openSession();
Transaction tref = sref.beginTransaction();
Query qref = sref.createQuery("select employerEmail from ApplicationsPojo where employeeId = :eref");
qref.setParameter("eref", id);

List ls = qref.list();
Iterator itref = ls.iterator();
if(itref.hasNext()) {
	// id = (Integer) ls.get(0);
	//String employerEmail = (String)ls.get(0);
	System.out.println("hi"); 
	
}
tref.commit();
sref.close();
sfref.close();
*/
