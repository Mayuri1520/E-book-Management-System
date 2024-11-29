package com.admin.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.DAO.BookDAOImpl;
import com.DB.DBConnect;
@WebServlet("/delete")
public class BookDeleteServlet extends HttpServlet {
   
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
			int id=Integer.parseInt(req.getParameter("id"));
			
			BookDAOImpl dao = new BookDAOImpl(DBConnect.getConn());
			boolean f = dao.deleteBooks(id);
			
			HttpSession session = req.getSession();
			  
			 if(f)
			  {       
				 session.setAttribute("succMsg", "Book Delete successfull..");
				 resp.sendRedirect("admin/all_books.jsp");
			  }
			  else {    
				  session.setAttribute("failedMsg", "something wrong on server...");
					 resp.sendRedirect("admin/all_books.jsp");
			  }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
