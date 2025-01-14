package com.user.servlet;

import javax.servlet.http.HttpServlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.UserDAOImpl;
import com.DB.DBConnect;
import com.entity.User;
@WebServlet("/login")
public class LoginServlet  extends HttpServlet{
 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			UserDAOImpl dao = new UserDAOImpl(DBConnect.getConn());
			
			HttpSession session = req.getSession();
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			//----------admin login-----------
			if("admin@gmail.com".equals(email) && "admin".equals(password))
			{   
				User us = new User();
				us.setName("Admin");
				
				session.setAttribute("userobj",us);
				resp.sendRedirect("admin/home.jsp");
		     }
			else {
				
				     User us = dao.login(email,password);
			         if(us!=null)
			        {
			    	    session.setAttribute("userobj", us);
			    	    resp.sendRedirect("index.jsp");
			         }else
			        {
			    	    session.setAttribute("failedMsg","Email & password Invalid");
			    	    resp.sendRedirect("login.jsp");
			         }
				  // resp.sendRedirect("index.jsp");
                 }
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
