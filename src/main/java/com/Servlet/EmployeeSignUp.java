package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.Pojo.EmployeeSignUpPojo;

/**
 * Servlet implementation class EmployeeSignUp
 */
@WebServlet("/EmployeeSignUp")
public class EmployeeSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 PrintWriter out = response.getWriter();
		 String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			int mobile = Integer.parseInt(request.getParameter("mobile"));
			String skills = request.getParameter("skills");
			int experience = Integer.parseInt(request.getParameter("experience"));
			String cwo = request.getParameter("cwo");
			int ctc = Integer.parseInt(request.getParameter("ctc"));
			String jobrole = request.getParameter("jobrole");
			String plocation = request.getParameter("plocation");
			int noticep = Integer.parseInt(request.getParameter("noticep"));
			
				Configuration conref = new Configuration();
				conref.configure("hibernateE.cfg.xml");
				SessionFactory sfref = conref.buildSessionFactory();
				Session sref = sfref.openSession();
				Transaction tref = sref.beginTransaction();
				EmployeeSignUpPojo eesp = new EmployeeSignUpPojo();
				eesp.setName(name);
				eesp.setEmail(email);
				eesp.setPassword(password);
				eesp.setMobile(mobile);
				eesp.setSkills(skills);
				eesp.setExperience(experience);
				eesp.setCwo(cwo);
				eesp.setCtc(ctc);
				eesp.setJobrole(jobrole);
				eesp.setPlocation(plocation);
				eesp.setNoticep(noticep);
				sref.save(eesp);
				
				out.print("<table >");
				  out.print("<tr><th>Successfully SignedUp!</th></tr>");
				  out.println("<tr><td><a href = 'EmployeeLogin.html'/>LogIn</td></tr>");
				  out.println("</table>");
				  
				tref.commit();
				sref.close();
				sfref.close();
	}

}
