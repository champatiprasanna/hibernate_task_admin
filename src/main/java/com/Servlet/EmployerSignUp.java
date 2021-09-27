package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
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

import com.Pojo.EmployerSignUpPojo;

/**
 * Servlet implementation class EmployerSignUp
 */
@WebServlet("/EmployerSignUp")
public class EmployerSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 PrintWriter out = response.getWriter();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String oname = request.getParameter("oname");
	//	String skills = request.getParameter("skills");
		String location = request.getParameter("location");
		String web = request.getParameter("web");
		String about = request.getParameter("about");
		
		
		
		
		
			Configuration conref = new Configuration();
			conref.configure("hibernateER.cfg.xml");
			SessionFactory sfref = conref.buildSessionFactory();
			Session sref = sfref.openSession();
			Transaction tref = sref.beginTransaction();
			
			EmployerSignUpPojo esp = new EmployerSignUpPojo();
			esp.setEmail(email);
			esp.setPassword(password);
			esp.setOrgName(oname);
			//esp.setSkills(skills);
			esp.setLocation(location);
			esp.setWeb(web);
			esp.setAbout(about);
			sref.save(esp);

			out.print("<table >");
			  out.print("<tr><th>Successfully SignedUp!</th></tr>");
			  out.println("<tr><td><a href = 'EmployerLogin.html'/>LogIn</td></tr>");
			  out.println("</table>");
			tref.commit();
			sref.close();
			sfref.close();
			
			
			
	}

}
