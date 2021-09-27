package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
 * Servlet implementation class JobSearch
 */
@WebServlet("/JobSearch")
public class JobSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String jobtitle = request.getParameter("jobtitle");
		String qualification = request.getParameter("qualification");
		 String jobtype = request.getParameter("jobtype");
		 int experience = Integer.parseInt(request.getParameter("experience"));
		
		 ServletContext context = getServletContext();
	      String email=(String)context.getAttribute("email");
	      context.setAttribute("jobtitle", jobtitle);
	
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "root","1234");
			String sql = " select * from employerjp where jobtitle = '"+jobtitle+"' and jobtype = '"+jobtype +"' and experience = '"+ experience+"'";
			String sqi = "select id,name from Employee where email = '"+email+"'";
			PreparedStatement psi = con.prepareStatement(sqi);
			 ResultSet rsi = psi.executeQuery();
			 if (rsi.next()) {
				 System.out.println(rsi.getString(1));
				 System.out.println(rsi.getString(2));
		            ServletContext servletContext = getServletContext();
		            servletContext.setAttribute("id",rsi.getString(1));
		            servletContext.setAttribute("name",rsi.getString(2));
			 }
			PreparedStatement ps = con.prepareStatement(sql);
			
			  ResultSet rs = ps.executeQuery();
			  out.print("<table border = '1' width = '50%'>");
			  out.print("<tr><th>jobtitle</th><th>jobtype</th><th>poy</th><th>qualification</th><th>skills</th><th>experience</th><th>interviewdate</th><th>location</th><th>salary</th><th>Apply</th></tr>");
		        if (rs.next()) {
		         
		            ServletContext servletContext = getServletContext();
		            servletContext.setAttribute("email",rs.getString(11));
		       
		            
		            out.println("<tr><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td><td>"+rs.getString(9)+"</td><td>"+rs.getString(10)+"</td><td><a href = 'Apply.html'/>Job Apply</td></tr>");
		        
		          
		      
		           
		        }
		        out.println("</table>");
		       
		    } catch(Exception e) {
				 e.printStackTrace();
					System.out.println(e.getMessage());
				}
				
		
	
	}

}



/*
PrintWriter out = response.getWriter();
//String skill = request.getParameter("skillName");
 ServletContext context1 = getServletContext();
  String n=(String)context1.getAttribute("jobtype");
ServletContext context2 = getServletContext();
String location=(String)context2.getAttribute("location");
//ServletContext context = getServletContext();
ServletContext context3 = getServletContext();
String qualification=(String)context3.getAttribute("qualification");

out.println(n+""+ location + ""+qualification);
/*try {
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "root","1234");
	String sql = " select * from employerjp where jobtype = ' ;
	PreparedStatement ps = con.prepareStatement(sql);
	
	  ResultSet rs = ps.executeQuery();
	 // out.print("<table border = '1' width = '50%'>");
	//  out.print("<tr><th>SkillName</th><th>Active</th><th>Deactive</th></tr>");
        while (rs.next()) {
            System.out.println(rs.getString(1));
          //  ServletContext servletContext = getServletContext();
           // servletContext.setAttribute("skill",rs.getString(1));
           
            
           // out.println("<tr><td>"+rs.getString(1)+"</td><td><a href = 'TUpdate'/>Activate</td><td><a href = 'TRemove'/>Deactivate</td></tr>");
           
           
        }
        out.println("</table>");
       
    } catch(Exception e) {
		 e.printStackTrace();
			System.out.println(e.getMessage());
		}*/
