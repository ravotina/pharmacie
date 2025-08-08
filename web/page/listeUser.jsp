<%-- 
    Document   : listeUser
    Created on : 24 déc. 2024, 10:25:26
    Author     : Ravo tina
--%>

<%@page import="model.Presance"%>
<%@page import="model.User"%>
<%@page import="java.util.List"%>
<%
    List<User> les_users = (List<User>)request.getAttribute("les_users");
    List<Presance> les_presance = (List<Presance>)request.getAttribute("les_presence");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <% 
            if(les_users != null){
                for(User une_user : les_users){ %>
                
                <h1>  <%= une_user.getEmail() %> </h1>
                    
                <% }
            }
        %>
        
        <form action="<%= request.getContextPath() %>/Usercontroleur" method="post">

          <p> Username <input type="text" name="username" id=""> </p>
          <p> Email <input type="email" name="email" id=""> </p>
          <p> passord  <input type="password" name="password" id=""></p>
          <p> Date time <input type="datetime-local" name="datetimes" id=""></p>

          <input type="submit" name="insert_user" value="Valider">

        </form>
        
        <a href="<%= request.getContextPath() %>/Usercontroleur?signe=liste">liste Presance</a>
        
        
        <% 
            if(les_presance != null){
                for(Presance une_presance : les_presance){ %>
                
                <h1>  <%= une_presance.getCreatedAt() %> </h1>
                <p>  <%= une_presance.getUser().getUsername() %> </p>
                    
                <% }
            }
        %>
        
        
        
        
    </body>
</html>
