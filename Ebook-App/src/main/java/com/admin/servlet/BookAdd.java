package com.admin.servlet;

import java.io.File;
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
@WebServlet("/add_books")
@MultipartConfig
public class BookAdd extends HttpServlet {
   
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
			String bookName = req.getParameter("bname");
			String author = req.getParameter("author");
			String price = req.getParameter("Price");
			String bookCategories = req.getParameter("categories");
			String status = req.getParameter("status");
			Part part = req.getPart("bimg");
			String fileName = part.getSubmittedFileName();
			
			BookDtls b= new BookDtls(bookName,author,price,bookCategories,status,fileName,"admin");
			
			BookDAOImpl dao= new BookDAOImpl(DBConnect.getConn());
		    boolean f = dao.addBooks(b);
		     
		     
			 HttpSession session = req.getSession();
		     
			 if (f)
			 {
				String path = getServletContext().getRealPath("")+"book";
			
			File file = new File(path);
			part.write(path+ File.separator+ fileName);
				 
			// session.setAttribute("failedMsg","Some thing wrong");
				 session.setAttribute("succMsg","Book add successfully");
				 resp.sendRedirect("admin/add_books.jsp");
			} else {
				//session.setAttribute("succMsg","Book add successfully");
				 session.setAttribute("failedMsg","Some thing wrong");
				 resp.sendRedirect("admin/add_books.jsp");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
