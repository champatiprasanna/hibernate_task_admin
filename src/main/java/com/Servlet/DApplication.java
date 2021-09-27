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
 * Servlet implementation class DApplication
 */
@WebServlet("/DApplication")
public class DApplication extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		ServletContext context = getServletContext();
	     String employerEmail=(String)context.getAttribute("email");
	     String jobtitle =(String)context.getAttribute("jobtitle");
	     Configuration con = new Configuration();
	        con.configure("hibernateA.cfg.xml");
	        SessionFactory sessionFactory = con.buildSessionFactory();
	        Session session = sessionFactory.openSession();
	        Transaction tr = session.beginTransaction();
	       
	        Query query1 = session.createQuery("delete from ApplicationsPojo where employerEmail =:eref and jobtitle = :jt");
	        query1.setParameter("eref",employerEmail);
	        query1.setParameter("jt",jobtitle);
	        query1.executeUpdate();
	        tr.commit();
	        session.close();
	        sessionFactory.close();
	        out.println("Successfully deleted your applications");
		 
	}

}
