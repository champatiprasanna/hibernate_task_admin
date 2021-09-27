package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class EmployerLogin
 */
@WebServlet("/EmployerLogin")
public class EmployerLogin extends HttpServlet {
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
				 ServletContext servletContext = getServletContext();
		            servletContext.setAttribute("email",email);
				out.print("<table >");
				  out.print("<tr><th>Successfully SignedIn!</th></tr>");
				  out.println("<tr><td><a href = 'EmployerJobPost.html'/>Post a Job</td></tr>");
				  out.println("<tr><td><a href = 'CApplications.html'/>Check for applications received</td></tr>");
				  out.println("</table>");
				 
				
			}
			else {
				out.println("The details you entered are incorrect");
			}
			
			tref.commit();
			sref.close();
			sfref.close();
	}

}
