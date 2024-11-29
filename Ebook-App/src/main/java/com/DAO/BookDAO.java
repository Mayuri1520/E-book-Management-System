package com.DAO;

import java.util.List;

import com.entity.BookDtls;

public interface BookDAO {

	 public boolean addBooks(BookDtls b);  //add book
		
		public List<BookDtls> getAllBooks();  //get all books
		
		public BookDtls getBookById(int id);   //get by using id
		
		public boolean updateEditBooks(BookDtls b); //update books
		
		public boolean deleteBooks(int id);       //delete books
		
		public List<BookDtls> getNewBook();        //get new book
		
		public List<BookDtls> getRecentBook();    //get Recent book
		
		public List<BookDtls> getOldBook();     //get Old Book
		
		public List<BookDtls> getAllRecentBook();   //get recent book
		
		public List<BookDtls> getAllNewBook();   //get New book
		
		public List<BookDtls> getAllOldBook();   //get Old book
		
		public List<BookDtls> getBookByOld(String email,String category); //get old book those selled

	   public boolean oldBookDelete(String email,String cat,int id);
	   
	   public List<BookDtls> getBookBySearch(String ch);
	   }

