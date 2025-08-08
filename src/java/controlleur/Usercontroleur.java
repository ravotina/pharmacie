/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlleur;

import connexion.Dbconnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.RequestDispatcher;
//import model.User;
//import java.time.format.DateTimeFormatter;
//import model.Presance;

/**
 *
 * @author Ravo tina
 */
@WebServlet(name = "Usercontroleur", urlPatterns = {"/Usercontroleur"})
public class Usercontroleur extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Usercontroleur</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Usercontroleur at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
         //processRequest(request, response);
         
         //PrintWriter out = response.getWriter();
         //String signe = request.getParameter("signe");
         
         //if(signe!=null){
         //    List<Presance> les_presence = null;
         //   try{
                
          //      Connection conn = Dbconnection.getConnection(); 
          //      les_presence = Presance.getAll(conn);

          //  }catch(SQLException e){
            // eurreru ici 
          //     out.println(e.getMessage());
          ///
          //request.setAttribute("les_presence", les_presence); // le anarana andraisana azy
          //  RequestDispatcher dispatcher = request.getRequestDispatcher("page/listeUser.jsp");
          //  dispatcher.forward(request, response); 
             
             
         //} else {
        
            
          //  List<User> les_user = null;
          //  try{
                // test connection ici 
           //     Connection conn = Dbconnection.getConnection(); 
           //     les_user = User.getAll(conn);

            //}catch(SQLException e){
            // eurreru ici 
            //   out.println(e.getMessage());
           //}

          // request.setAttribute("les_users", les_user); // le anarana andraisana azy
          // RequestDispatcher dispatcher = request.getRequestDispatcher("page/listeUser.jsp");
           //dispatcher.forward(request, response);   
        
         //}
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        //processRequest(request, response);
        
        //String insert_user = request.getParameter("insert_user");
        //if(insert_user!= null){
            
            
        //    String username = request.getParameter("username");
        //    String email = request.getParameter("email");
        //    String password =request.getParameter("password");
        //    String datetime = request.getParameter("datetimes");

        //    PrintWriter out = response.getWriter();
        //    LocalDateTime dateTime_tena_izi = LocalDateTime.parse(datetime);

            //out.println(datetime);
        //    List<User> les_user = null;
        //    try{
                 // test connection ici
        //         Connection conn = Dbconnection.getConnection(); 
        //         User une_user = new User(username  , email , password , dateTime_tena_izi);
        //         une_user.insert(conn);
        //         les_user = User.getAll(conn);

        //     }catch(SQLException e){
             // eurreru ici 
        //        out.println(e.getMessage());
        //    }
        //    request.setAttribute("les_users", les_user); // le anarana andraisana azy
        //    RequestDispatcher dispatcher = request.getRequestDispatcher("page/listeUser.jsp");
        //    dispatcher.forward(request, response); 
        //} 
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
