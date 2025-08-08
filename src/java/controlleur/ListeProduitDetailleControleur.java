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
import model.Laboratoir;
import model.Maladie;
import model.Produit;
import model.ProduitDetaille;
import model.Querison;
import model.TypeProduit;
import model.Uniter;
import model.Vente;

/**
 *
 * @author Ravo tina
 */
@WebServlet(name = "ListeProduitDetailleControleur", urlPatterns = {"/ListeProduitDetailleControleur"})
public class ListeProduitDetailleControleur extends HttpServlet {

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
            out.println("<title>Servlet ListeProduitDetailleControleur</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListeProduitDetailleControleur at " + request.getContextPath() + "</h1>");
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
        /**processRequest(request, response);*/
        String signe = request.getParameter("signef");
         //signef=
        PrintWriter out = response.getWriter();
        if(signe!= null){
            
            String id_typeproduit = request.getParameter("id_type_produit");
            String id_age = request.getParameter("idage");
            String id_produit = request.getParameter("idproduit");
           
            List<Querison> les_querison = null;
            
            List<Laboratoir> les_laboratoir = null;
            List<Uniter> les_uniter = null;
            List<Age> les_age = null;
            List<Maladie> les_maladie = null;
            List<Produit> les_produit = null;
            List<Vente> les_vente = null;
            List<TypeProduit> les_type_produit = null;
             List<ProduitDetaille> les_produit_detailles = null;



                try{
                    // test connection ici 
                    Connection conn = Dbconnection.getConnection(); 
                    les_laboratoir = Laboratoir.getAll(conn);
                    les_uniter = Uniter.getAll(conn);
                    les_age  = Age.getAll(conn);
                    les_vente = Vente.getAll(conn);
                   
                    les_maladie   = Maladie.getAll(conn);
                    
                    les_produit_detailles  = ProduitDetaille.getAll(conn);
                    les_produit = Produit.getAll(conn);
                    
                    les_vente = Vente.getAll(conn);
                    les_type_produit = TypeProduit.getAll(conn);
                    
                // une_user.insert(conn);
                les_querison = Querison.getAll(conn);
                List<ProduitDetaille> nouveau_vente = new ArrayList<>(); // Utilisé ArrayList avec une majuscule

                for (ProduitDetaille une_produit_detaille : les_produit_detailles ) { 
                    
                    if(id_produit !=""){
                    if ( une_produit_detaille.getProduit().getId() == Integer.parseInt(id_produit)){
                        nouveau_vente.add(une_produit_detaille);
                    }
                    }
                    if( id_produit==""){
                        
                        nouveau_vente.add(une_produit_detaille);
                        
                    }
                }

                out.println(les_querison);
                //request.setAttribute("les_querison",  nouveau_querison); // le anarana andraisana azy
                request.setAttribute("les_laboratoir", les_laboratoir ); // le anarana andraisana azy
                request.setAttribute("les_uniter", les_uniter ); // le anarana andraisana azy
                request.setAttribute("les_age", les_age ); 
                request.setAttribute("les_maladie", les_maladie );
                request.setAttribute("les_produit", les_produit );
                request.setAttribute("les_type_produit", les_type_produit);
                request.setAttribute("les_produit_detailles", nouveau_vente);
                RequestDispatcher dispatcher = request.getRequestDispatcher("liste_produit_detaille.jsp");
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
            List<ProduitDetaille> les_produit_detailles = null;
            



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
                    les_produit_detailles = ProduitDetaille.getAll(conn);
                // une_user.insert(conn);
                les_querison = Querison.getAll(conn);

                out.println(les_querison);
                request.setAttribute("les_querison", les_querison); // le anarana andraisana azy
                request.setAttribute("les_laboratoir", les_laboratoir ); // le anarana andraisana azy
                request.setAttribute("les_uniter", les_uniter ); // le anarana andraisana azy
                request.setAttribute("les_age", les_age ); 
                request.setAttribute("les_maladie", les_maladie );
                request.setAttribute("les_produit", les_produit );
                request.setAttribute("les_vente", les_vente );
                request.setAttribute("les_type_produit", les_type_produit);
                request.setAttribute("les_produit_detailles", les_produit_detailles);
                 
                RequestDispatcher dispatcher = request.getRequestDispatcher("liste_produit_detaille.jsp");
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
