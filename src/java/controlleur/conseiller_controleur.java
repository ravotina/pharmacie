/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlleur;

import connexion.Dbconnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Age;
import model.Conseile;
import model.Laboratoir;
import model.Maladie;
import model.Mois;
import model.Produit;
import model.Querison;
import model.TypeProduit;
import model.Uniter;
import model.Vente;

/**
 *
 * @author Ravo tina
 */
@WebServlet(name = "conseiller_controleur", urlPatterns = {"/conseiller_controleur"})
public class conseiller_controleur extends HttpServlet {

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
            out.println("<title>Servlet conseiller_controleur</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet conseiller_controleur at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response)
          
          
        
         String signe = request.getParameter("signef");
        PrintWriter out = response.getWriter();
        if(signe!= null){
            
            String mois_id = request.getParameter("mois_id");
            String anner = request.getParameter("anner");
            
            
            List<Conseile> les_conseile = null;
            List<Mois> les_mois = null;



                try{
                    // test connection ici 
                    Connection conn = Dbconnection.getConnection(); 
                    les_conseile = Conseile.getAll(conn);
                    les_mois = Mois.getAll(conn);
                   
               
                List<Conseile> nouveau_Conseile = new ArrayList<>(); // Utilisé ArrayList avec une majuscule

                for (Conseile une_querison : les_conseile ) { 
                    
                    if(mois_id !="" && anner != ""){
                    if ((une_querison.getMois().getId() == Integer.parseInt(mois_id) &&
                        une_querison.getAnner() == Integer.parseInt(anner))) {
                        nouveau_Conseile.add(une_querison);
                    }
                    }if(mois_id=="" && anner != ""){
                        if(une_querison.getAnner() == Integer.parseInt(anner)) {
                        nouveau_Conseile.add(une_querison);
                        }
                    }if(mois_id!="" && anner == ""){
                        if(une_querison.getMois().getId() == Integer.parseInt(mois_id)) {
                         nouveau_Conseile.add(une_querison);
                        }
                    }
                    
                    if(mois_id=="" && anner == ""){
                        nouveau_Conseile.add(une_querison);
                        
                    }
                }

                request.setAttribute("les_conseile", nouveau_Conseile);
                request.setAttribute("les_mois", les_mois);
                RequestDispatcher dispatcher = request.getRequestDispatcher("liste_conseile.jsp");
                dispatcher.forward(request, response); 
                //processRequest(request, response);
            } catch (SQLException ex) {
                out.println(ex.getMessage());
                //Logger.getLogger(ProduitCotroleur.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            
            List<Conseile> les_conseile = null;
            List<Mois> les_mois = null;



                try{
                    // test connection ici 
                    Connection conn = Dbconnection.getConnection(); 
                    les_conseile = Conseile.getAll(conn);
                    les_mois = Mois.getAll(conn);
                 

                request.setAttribute("les_conseile", les_conseile);
                request.setAttribute("les_mois", les_mois);
                RequestDispatcher dispatcher = request.getRequestDispatcher("liste_conseile.jsp");
                dispatcher.forward(request, response); 
                //processRequest(request, response);
            } catch (SQLException ex) {
                out.println(ex.getMessage());
                //Logger.getLogger(ProduitCotroleur.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }    
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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

