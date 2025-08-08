/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlleur;

import connexion.Dbconnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Age;
import model.Laboratoir;
import model.Maladie;
import model.Produit;
import model.Querison;
import model.Sexe;
import model.TypeProduit;
import model.Uniter;
import model.Vendeur;
import model.Vente;

/**
 *
 * @author Ravo tina
 */
@WebServlet(name = "VenteCommissionControleur", urlPatterns = {"/VenteCommissionControleur"})
public class VenteCommissionControleur extends HttpServlet {

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
            out.println("<title>Servlet VenteCommissionControleur</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VenteCommissionControleur at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response)
          
          
        
         String signe = request.getParameter("signef");
         //signef=
        PrintWriter out = response.getWriter();
        if(signe!= null){
            
            String id_vendeur = request.getParameter("idVendeur");
             String date_min = request.getParameter("daty_min");
              String date_max  = request.getParameter("daty_max");
              String sexe_id = request.getParameter("idsexe");
           
            List<Querison> les_querison = null;
            
            List<Laboratoir> les_laboratoir = null;
            List<Uniter> les_uniter = null;
            List<Age> les_age = null;
            List<Maladie> les_maladie = null;
            List<Produit> les_produit = null;
            List<Vente> les_vente = null;
            List<TypeProduit> les_type_produit = null;
             List<Vendeur> les_vendeur = null;
             List<Sexe> les_sexe = null;



           try{
                    // test connection ici 
                    Connection conn = Dbconnection.getConnection(); 
                    les_laboratoir = Laboratoir.getAll(conn);
                    les_uniter = Uniter.getAll(conn);
                    les_age  = Age.getAll(conn);
                    les_vente = Vente.getAll(conn);
                   
                    les_maladie   = Maladie.getAll(conn);
                    
                    
                    les_produit = Produit.getAll(conn);
                    les_vendeur  = Vendeur.getAll(conn);
                    
                    les_vente = Vente.getAll(conn);
                    les_type_produit = TypeProduit.getAll(conn);
                    les_sexe = Sexe.getAll(conn);
                    
                    
                // une_user.insert(conn);
                les_querison = Querison.getAll(conn);
                //List<Vente> nouveau_vente = new ArrayList<>(); // Utilisé ArrayList avec une majuscule

                // id_vendeur  ,
                List<Vente> nouveau_vente =  Vente.filtrerVentes(les_vente , date_min , date_max , sexe_id);
                
                double total_commission =  Vente.sommecommition(nouveau_vente);

                out.println(les_querison);
                //request.setAttribute("les_querison",  nouveau_querison); // le anarana andraisana azy
                request.setAttribute("les_laboratoir", les_laboratoir ); // le anarana andraisana azy
                request.setAttribute("les_uniter", les_uniter ); // le anarana andraisana azy
                request.setAttribute("les_age", les_age ); 
                request.setAttribute("les_maladie", les_maladie );
                request.setAttribute("les_produit", les_produit );
                request.setAttribute("les_vente", nouveau_vente );
                request.setAttribute("les_type_produit", les_type_produit);
                request.setAttribute("les_vendeur", les_vendeur);
                request.setAttribute("total_commission", total_commission);
                request.setAttribute("les_sexe", les_sexe);
                RequestDispatcher dispatcher = request.getRequestDispatcher("liste_vente_commission.jsp");
                dispatcher.forward(request, response); 
                //processRequest(request, response);
            } catch (SQLException ex) {
                out.println(ex.getMessage());
                //Logger.getLogger(ProduitCotroleur.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } 
        
        
        
        
        else {
            
            List<Querison> les_querison = null;
            List<Laboratoir> les_laboratoir = null;
            List<Uniter> les_uniter = null;
            List<Age> les_age = null;
            List<Maladie> les_maladie = null;
            List<Produit> les_produit = null;
            List<Vente> les_vente = null;
            List<TypeProduit> les_type_produit = null;
            
            
            List<Vendeur> les_vendeur = null;
            
             List<Sexe> les_sexe = null;
            



                try{
                    // test connection ici 
                    Connection conn = Dbconnection.getConnection(); 
                    les_laboratoir = Laboratoir.getAll(conn);
                    les_uniter = Uniter.getAll(conn);
                    les_age  = Age.getAll(conn);
                    les_maladie   = Maladie.getAll(conn);
                    les_produit = Produit.getAll(conn);
                    les_vente = Vente.getAll(conn);
                    les_type_produit = TypeProduit.getAll(conn);
                    les_vendeur  = Vendeur.getAll(conn);
                // une_user.insert(conn);
                les_querison = Querison.getAll(conn);
                les_sexe = Sexe.getAll(conn);

                out.println(les_querison);
                request.setAttribute("les_querison", les_querison); // le anarana andraisana azy
                request.setAttribute("les_laboratoir", les_laboratoir ); // le anarana andraisana azy
                request.setAttribute("les_uniter", les_uniter ); // le anarana andraisana azy
                request.setAttribute("les_age", les_age ); 
                request.setAttribute("les_maladie", les_maladie );
                request.setAttribute("les_produit", les_produit );
                request.setAttribute("les_vente", les_vente );
                request.setAttribute("les_type_produit", les_type_produit);
                request.setAttribute("les_vendeur", les_vendeur);
                request.setAttribute("les_sexe", les_sexe);
                
                double total_commission =  Vente.sommecommition(les_vente);
                request.setAttribute("total_commission", total_commission);
                
                
                 
                RequestDispatcher dispatcher = request.getRequestDispatcher("liste_vente_commission.jsp");
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
