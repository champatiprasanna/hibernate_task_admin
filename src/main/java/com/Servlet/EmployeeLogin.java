package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

import javax.print.attribute.standard.PrinterName;
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
 * Servlet implementation class EmployeeLogin
 */
@WebServlet("/EmployeeLogin")
public class EmployeeLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 PrintWriter out = response.getWriter();
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
				Configuration conref = new Configuration();
				conref.configure("hibernateE.cfg.xml");
				SessionFactory sfref = conref.buildSessionFactory();
				Session sref = sfref.openSession();
				Transaction tref = sref.beginTransaction();
				Query qref = sref.createQuery("select id from EmployeeSignUpPojo where email = :eref and password = :pref");
				qref.setParameter("eref", email);
				qref.setParameter("pref", password);
				List ls = qref.list();
				Iterator itref = ls.iterator();
				if(itref.hasNext()) {
					int  id = (Integer) ls.get(0);
					 ServletContext servletContext = getServletContext();
			            servletContext.setAttribute("email",email);
			            servletContext.setAttribute("id", id);
					out.print("<table >");
					  out.print("<tr><th>Successfully SignedIn!</th></tr>");
					  out.println("<tr><td><a href = 'JobSearch.html'/>Search for a Job</td></tr>");
					  out.println("<tr><td><a href = 'Applied.html'/>Applied Jobs</td></tr>");
					  out.println("</table>");
					
					//out.println(id);
				}
				else {
					out.println("The details you entered are incorrect");
				}
				
				tref.commit();
				sref.close();
				sfref.close();
	}

}

/*
try {
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "root","1234");
	//Jobtype query
	String sql = "select distinct jobtype from employerjp ";
	PreparedStatement ps = con.prepareStatement(sql);
	//qualification query
	String sqlq = "select distinct qualification from employerjp";
	PreparedStatement psq = con.prepareStatement(sqlq);
	//experience query
	String sqle = "select distinct location from employerjp";
	PreparedStatement pse = con.prepareStatement(sqle);
	ResultSet rs = ps.executeQuery();
	ResultSet rsq = psq.executeQuery();
	ResultSet rse = pse.executeQuery();
	  out.print("<table>");
	  out.print("<tr><th>SkillName</th></tr>");
	  out.println("<h3>Job Type</h3>");
        while (rs.next()) {
            System.out.println(rs.getString(1));
          
          
         out.print(" <label></label></br>");
         out.print("<input type= 'radio' name='jobtype'  value="+rs.getString(1)+">"+rs.getString(1));
         
         ServletContext servletContext = getServletContext();
            servletContext.setAttribute("jobtype",rs.getString(1));
           
         
       
        }
        
        out.println("<h3>Qualification</h3>");
        out.print("<select name = 'qualification'>");
        
        while (rsq.next()) {
            System.out.println(rsq.getString(1));
          
        	   out.print(" <label></label></br>");
		       
		         out.print("<option value="+rsq.getString(1)+">"+rsq.getString(1)+"</option>");

		         ServletContext servletContext = getServletContext();
		         servletContext.setAttribute("qualification",rsq.getString(1));
         
       
        }/*
        out.print("</select>");
        out.println("<h3>Location</h3>");
        out.print("<select name = 'location'>");
        while (rse.next()) {
	            System.out.println(rse.getString(1));
	          
	         out.print(" <label></label></br>");
	       
	         out.print("<option  value="+rse.getString(1)+">"+rse.getString(1)+"</option>");

			
	         ServletContext servletContext3 = getServletContext();
	         servletContext3.setAttribute("location",rse.getString(1));
	      
			
	        
	         
	         
	       
	        }
        
        
        out.print("</select>");
       *//* 
        out.println("<tr><td><a href = 'JobSearch'/>Search job</td></tr>");
        out.println("</table>");
       
    } catch(Exception e) {
		 e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
 */
