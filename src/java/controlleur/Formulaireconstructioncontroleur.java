/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlleur;

import connexion.Dbconnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ConstructProduit;
import model.Laboratoir;
import model.MatierPremier;
import model.Produit;
import model.ProduitDetaille;
import model.Uniter;
import java.time.LocalDate;
import model.Client;
import model.Conseile;
import model.Mois;
import model.Vendeur;
import model.Vente;


// verification concept (10 minute) liste historique 

/**
 *
 * @author Ravo tina
 */
@WebServlet(name = "Formulaireconstructioncontroleur", urlPatterns = {"/Formulaireconstructioncontroleur"})
public class Formulaireconstructioncontroleur extends HttpServlet {

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
            out.println("<title>Servlet Formulaireconstructioncontroleur</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Formulaireconstructioncontroleur at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        
        PrintWriter out = response.getWriter();
         String signe = request.getParameter("signe");
         
        if(signe!=null){
            //if(signe == "laboratoire"){
                
            //}
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
             
             
         } else {
            
            
        
            
            List<Produit> les_produit = null;
            List<MatierPremier> les_matiere_premier = null;
            List<ProduitDetaille> les_produit_detaite = null;
            List<Mois> les_mois = null;
            List<Client> les_client = null;  
            List<Vendeur> les_vendeur = null;
            
            try{
                // test connection ici 
                Connection conn = Dbconnection.getConnection(); 
                les_produit = Produit.getAll(conn);
                les_matiere_premier = MatierPremier.getAll(conn);
                les_produit_detaite = ProduitDetaille.getAll(conn);
                les_mois = Mois.getAll(conn);
                les_client = Client.getAll(conn);
                les_vendeur = Vendeur.getAll(conn);
                

            }catch(SQLException e){
            // eurreru ici 
               out.println(e.getMessage());
           }

          request.setAttribute("les_produit", les_produit ); // le anarana andraisana azy
          request.setAttribute("les_matiere_premier", les_matiere_premier ); // le anarana andraisana azy
          request.setAttribute("les_produit_detaite", les_produit_detaite ); // le anarana andraisana azy
          request.setAttribute("les_mois", les_mois ); // le anarana andraisana azy
          request.setAttribute("les_client", les_client );
          request.setAttribute("les_vendeur", les_vendeur );
          
          
          RequestDispatcher dispatcher = request.getRequestDispatcher("insertionconstruction.jsp");
          dispatcher.forward(request, response);   
        
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
        //processRequest(request, response);
        
        String signe = request.getParameter("signef");
        PrintWriter out = response.getWriter();
        if(signe!= null){
            if(signe.equals("construction")){
                String quantiter = request.getParameter("quantiter");
                String produitDetailleId = request.getParameter("produitDetailleId");
                String matierPremierId = request.getParameter("matierPremierId");
                
                 try{
                // test connection ici
                      int intproduitde = Integer.parseInt(produitDetailleId);
                      int intmatierPremierId = Integer.parseInt(matierPremierId);
                      double quantiter_double = Double.parseDouble(quantiter);
                      
                      ProduitDetaille une_produit_detaille = new ProduitDetaille();
                      une_produit_detaille.setId(intproduitde);
                      
                      MatierPremier une_matier_premier = new  MatierPremier();
                      une_matier_premier.setId(intmatierPremierId);
                      
                         Connection conn = Dbconnection.getConnection(); 
                         ConstructProduit une_laboratoir = new ConstructProduit(quantiter_double , une_produit_detaille ,  une_matier_premier);
                         une_laboratoir.insert(conn);

                     }catch(SQLException e){
                     // eurreru ici 
                        out.println(e.getMessage());
                    }
                 
                 response.sendRedirect(request.getContextPath() + "/Formulaireconstructioncontroleur");
            }
            
            
            if(signe.equals("dettaill_produit")){
                String produit_id = request.getParameter("produitId");
                String prix = request.getParameter("prix");
                String date_fabrication = request.getParameter("date_fabrication");
                String date_expiration = request.getParameter("date_expiration");
                
                 try{
                // test connection ici
                      int intproduit = Integer.parseInt(produit_id);
                      double prix_double = Double.parseDouble(prix);
                      
                      LocalDate localDate1 = LocalDate.parse(date_fabrication);
                      Date date_fabrication_sql = Date.valueOf(localDate1);
                      
                      LocalDate localDate = LocalDate.parse(date_expiration);
                      Date date_expiration_sql = Date.valueOf(localDate);
                      
                      Produit une_produit = new Produit();
                      une_produit.setId(intproduit);
                      
                      ProduitDetaille une_produit_dettaille = new ProduitDetaille(prix_double , date_expiration_sql , date_fabrication_sql , une_produit);
                      
                      
                      
                      //ProduitDetaille une_produit_detaille = new ProduitDetaille();
                      //une_produit_detaille.setId(intproduitde);
                      
                      //MatierPremier une_matier_premier = new  MatierPremier();
                      //une_matier_premier.setId(intmatierPremierId);
                      
                       Connection conn = Dbconnection.getConnection(); 
                       //  ConstructProduit une_laboratoir = new ConstructProduit(quantiter_double , une_produit_detaille ,  une_matier_premier);
                       une_produit_dettaille.insert(conn);
                       
                       out.println(date_fabrication);

                     }catch(Exception e){
                     // eurreru ici 
                        out.println(e.getMessage());
                    }
                 
                 response.sendRedirect(request.getContextPath() + "/Formulaireconstructioncontroleur");
            } else if(signe.equals("vente")){
                String produit_detaile_id = request.getParameter("produitdettaileId");
                String prix = request.getParameter("prix");
                String date_vente = request.getParameter("date_vente");
                String id_client = request.getParameter("client_id");
                String id_vendeur = request.getParameter("vendeurid");
                 try{
                // test connection ici
                      Connection conn = Dbconnection.getConnection(); 
                      int intproduit_deta = Integer.parseInt(produit_detaile_id );
                      int int_id_client = Integer.parseInt(id_client); 
                      int int_id_vendeur = Integer.parseInt(id_vendeur); 
                      ProduitDetaille  une_produit_d = ProduitDetaille.getById(conn, intproduit_deta);
                      Client une_cliento = Client.getById(conn, int_id_client);
                      
                      
                      Vendeur une_vendeur = Vendeur.getById(conn, int_id_vendeur);
                      

                      double prix_double = Double.parseDouble(prix);
                      double conmission = 0;
                      double nouvel_prix_double = prix_double;
                      if(prix_double>200000){
                          conmission = (prix_double * 5)/ 100;
                          nouvel_prix_double =  prix_double - conmission;
                      }
                      
                      LocalDate localDate1 = LocalDate.parse(date_vente);
                      Date date_vente_sql = Date.valueOf(localDate1);
                      
                      if (date_vente_sql.before(une_produit_d.getDateFabrication())) {
                            request.setAttribute("erreur", "La date de vente doit être postérieure à la date de fabrication : " + une_produit_d.getDateFabrication());
                            response.sendRedirect(request.getContextPath() + "/Formulaireconstructioncontroleur");
                        }
                      
                      
                      Vente une_produit_dettaille = new Vente(date_vente_sql , nouvel_prix_double , une_produit_d , une_cliento ,  une_vendeur , conmission);
                      
                      //ProduitDetaille une_produit_detaille = new ProduitDetaille();
                      //une_produit_detaille.setId(intproduitde);
                      
                      //MatierPremier une_matier_premier = new  MatierPremier();
                      //une_matier_premier.setId(intmatierPremierId);
                      
                       
                       //  ConstructProduit une_laboratoir = new ConstructProduit(quantiter_double , une_produit_detaille ,  une_matier_premier);
                       une_produit_dettaille.insert(conn);
                       
                       //out.println(date_fabrication);

                     }catch(Exception e){
                     // eurreru ici 
                        out.println(e.getMessage());
                    }
                 
                 response.sendRedirect(request.getContextPath() + "/Formulaireconstructioncontroleur");
            }
            else if(signe.equals("conseile")){
                String produit_id_1 = request.getParameter("produitid");
                String mois_id_1 = request.getParameter("Moisid");
                String anner_1 = request.getParameter("Anner");
                
                 try{
                // test connection ici
                      Connection conn = Dbconnection.getConnection(); 
                      int produit_id = Integer.parseInt(produit_id_1);
                      int mois_id = Integer.parseInt(mois_id_1);
                      int anner = Integer.parseInt(anner_1);
                      
                      out.println(produit_id_1);
                      out.println(mois_id_1);
                      out.println(anner_1);
                      
                     Conseile une_conseile = new Conseile(Mois.getById(conn, mois_id) , anner ,  Produit.getById(conn, produit_id));
                     out.println( une_conseile.getProduit().getNom());
                     
                     une_conseile.insert(conn);
                       //out.println(date_fabrication);

                     }catch(Exception e){
                     // eurreru ici 
                        out.println(e.getMessage());
                    }
                 
                 response.sendRedirect(request.getContextPath() + "/Formulaireconstructioncontroleur");
            }
    }

    
    }
}
