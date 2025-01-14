<%@page import="com.entity.BookDtls"%>
<%@page import="com.DB.DBConnect"%>
<%@page import="com.DAO.BookDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@include file="all_component/allCss.jsp"%>
</head>
<body style="background-color:#f0f1f2";>
 <%@include file="all_component/navbar.jsp" %>
 <%
 
     int bid =Integer.parseInt(request.getParameter("bid"));
     BookDAOImpl dao = new BookDAOImpl(DBConnect.getConn());
      BookDtls b= dao.getBookById(bid);
   %>
 <div class="Container p-3">
      <div class="row p-5">
          <div class="col-md-6 text-center p-5 border bg-white">
              <img src="book/<%=b.getPhotoName() %>" style="height:150px;width:100px"><br>
              <h4 class="mt-3">Book Name:<span class="text-success"><%=b.getBookname() %></span></h4>
              <h4>Author Name: <span class="text-success"><%=b.getAuthor() %></span></h4>
              <h4>Category:<span class="text-success"><%=b.getBookCategory()%> </span> </h4>
          </div>
          <div class="col-md-6 text-center p-5 border bg-white">
              <h2><%=b.getBookname() %></h2>
              <!-- old book not buy only new buy -->
              <%
                if("Old".equals(b.getBookCategory()))
              {%>
                <h5 class="text-primary">Contact To Seller</h5>
            	<h5 class="text-primary"><i class="fa-solid fa-envelope"></i> 
            	      Email:<%=b.getEmail() %></h5>
            	
              <%  
              }
              %>
             <div class="row">
                  <div class="col-md-4 text-danger text-center p-2">
                        <i class="fa-solid fa-money-bill-wave fa-2x"></i>
                         <p>Cash on Delivery</p>
                  </div>
                  <div class="col-md-4 text-danger text-center p-2">
                           <i class="fa-solid fa-rotate-left fa-2x"></i>
                           <p>Return Available</p>
                   </div>
                 <div class="col-md-4 text-danger text-center p-2">
                      <i class="fa-solid fa-truck fa-2x"></i>
                      <p>Free Shipping</p>
                 </div>
              </div>
              <%
                 if("Old".equals(b.getBookCategory()))
                {
              %>
                 <div class="text-center p-3">
                 <a href="index.jsp" class="btn btn-success"><i class="fa-solid fa-cart-plus"></i>Continue Shopping</a>   
                  <a href="" class="btn btn-danger"><i class="fa-solid fa-indian-rupee-sign"></i> 200</a>              
              </div>
              <%
              }else{
            	  %>
                   <div class="text-center p-3">
                 <a href="" class="btn btn-primary"><i class="fa-solid fa-cart-plus"></i> Add Cart</a>   
                  <a href="" class="btn btn-danger"><i class="fa-solid fa-indian-rupee-sign"></i> 200</a>              
              </div>
              
              <%
              }
              %>
             
          </div>
      </div>
 </div>
</body>
</html>