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

import com.Pojo.ApplicationsPojo;

/**
 * Servlet implementation class Apply
 */
@WebServlet("/Apply")
public class Apply extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("Job Applied");
		 ServletContext context = getServletContext();
	     String employeremail=(String)context.getAttribute("email");
	    String employeeid =(String)context.getAttribute("id");
	    // out.println(id);
	    String employeename=(String)context.getAttribute("name");
	     String jobtitle = (String)context.getAttribute("jobtitle");
	    Configuration conref = new Configuration();
		conref.configure("hibernateA.cfg.xml");
		SessionFactory sfref = conref.buildSessionFactory();
		Session sref = sfref.openSession();
		Transaction tref = sref.beginTransaction();
		ApplicationsPojo ap = new ApplicationsPojo();
		ap.setEmployeeId(employeeid);
		ap.setEmployeeName(employeename);
		
		ap.setEmployerEmail(employeremail);
		ap.setJobtitle(jobtitle);
		sref.save(ap);
		tref.commit();
		sref.close();
		sfref.close();
	        
	}

}
