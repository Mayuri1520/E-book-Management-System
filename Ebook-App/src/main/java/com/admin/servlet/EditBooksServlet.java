package com.admin.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.DAO.BookDAOImpl;
import com.DB.DBConnect;
import com.entity.BookDtls;
@WebServlet("/editbooks")
public class EditBooksServlet extends HttpServlet {
   
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			String bookName = req.getParameter("bname");
			String author = req.getParameter("author");
			String price = req.getParameter("Price");
			String status = req.getParameter("status");
			
			BookDtls b = new BookDtls();
			b.setBookId(id);
			b.setBookname(bookName);
			b.setAuthor(author);
			b.setPrice(price);
			b.setStatus(status);
			
			BookDAOImpl dao = new BookDAOImpl(DBConnect.getConn());
			boolean f = dao.updateEditBooks(b);
	
			HttpSession session = req.getSession();
			  
			 if(f)
			  {       
				 session.setAttribute("succMsg", "Book update successfull..");
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
