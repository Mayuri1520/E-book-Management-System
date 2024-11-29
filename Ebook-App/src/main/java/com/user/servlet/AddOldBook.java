package com.user.servlet;

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

@WebServlet("/add_old_book")
@MultipartConfig
public class AddOldBook extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
			String bookName = req.getParameter("bname");
			String author = req.getParameter("author");
			String price = req.getParameter("Price");
			String bookCategories ="Old";
			String status = "Active";
			Part part = req.getPart("bimg");
			String fileName = part.getSubmittedFileName();
			
			String useremail = req.getParameter("user");
			
			BookDtls b= new BookDtls(bookName,author,price,bookCategories,status,fileName,useremail);
			
			BookDAOImpl dao= new BookDAOImpl(DBConnect.getConn());
		    boolean f = dao.addBooks(b);
		     
		     
			 HttpSession session = req.getSession();
		     
			 if(f)
			 {
				String path = getServletContext().getRealPath("")+"book";
			
			File file = new File(path);
			part.write(path+ File.separator+ fileName);
				 
			// session.setAttribute("failedMsg","Some thing wrong");
				 session.setAttribute("succMsg","Book add successfully");
				 resp.sendRedirect("sell_book.jsp");
			} else {
				//session.setAttribute("succMsg","Book add successfully");
				 session.setAttribute("failedMsg","Something wrong");
				 resp.sendRedirect("sell_book.jsp");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	} 
}
