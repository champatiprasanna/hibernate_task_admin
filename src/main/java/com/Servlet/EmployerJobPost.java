package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
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

import com.Pojo.EmployerJobPostPojo;
import com.Pojo.SkillsPojo;

/**
 * Servlet implementation class EmployerJobPost
 */
@WebServlet("/EmployerJobPost")
public class EmployerJobPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		 PrintWriter out = response.getWriter();
		 String jobtitle = request.getParameter("jobtitle");
			String jobtype = request.getParameter("jobtype");
			int poy = Integer.parseInt(request.getParameter("poy"));
			String qualification = request.getParameter("qualification");
			String skills = request.getParameter("skills");
			int experience = Integer.parseInt(request.getParameter("experience"));
			String interviewdate = request.getParameter("date")+"/"+
				request.getParameter("month")+ "/" +request.getParameter("year");
			
		
			String location = request.getParameter("location");
			int salary = Integer.parseInt(request.getParameter("salary"));
			
			ServletContext context = getServletContext();
	        String email=(String)context.getAttribute("email");
	        
				Configuration conref = new Configuration();
				conref.configure("hibernateERJP.cfg.xml");
				SessionFactory sfref = conref.buildSessionFactory();
				Session sref = sfref.openSession();
				Transaction tref = sref.beginTransaction();
				
				EmployerJobPostPojo ejp = new EmployerJobPostPojo();
				ejp.setJobtitle(jobtitle);
				ejp.setJobtype(jobtype);
				ejp.setPoy(poy);
				ejp.setQualification(qualification);
				
				ejp.setSkills(skills);
				ejp.setExperience(experience);
				ejp.setInterviewdate(interviewdate);
				ejp.setLocation(location);
				ejp.setSalary(salary);
				ejp.setEmail(email);
				/*sref.save(ejp);
				out.println("successfully posted the job");
				
				tref.commit();
				sref.close();
				sfref.close();*/

			Configuration conref1 = new Configuration();
				conref1.configure("hibernateS.cfg.xml");
				SessionFactory sfref1 = conref1.buildSessionFactory();
				Session sref1 = sfref1.openSession();
				Transaction tref1 = sref1.beginTransaction();
				Query qref1 = sref1.createQuery("from SkillsPojo where skills = :eref");
				qref1.setParameter("eref", skills);
				
				List ls1 = qref1.list();
				Iterator itref1 = ls1.iterator();
				if(itref1.hasNext()) {
					ejp.setActive(true);
					sref.save(ejp);
					out.println("successfully posted the job");
					
					tref.commit();
					sref.close();
					sfref.close();
					
				}
				else {
					ejp.setActive(false);
					sref.save(ejp);
					out.println("successfully posted the job");
					
					tref.commit();
					sref.close();
					sfref.close();
				}
				
	}

}
