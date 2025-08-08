/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlleur;

import connexion.Dbconnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Laboratoir;
import model.Uniter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import model.Age;
import model.Client;
import model.Maladie;
import model.MatierPremier;
import model.Produit;
import model.Querison;
import model.Sexe;
import model.TypeProduit;
import model.Vendeur;

/**
 *
 * @author Ravo tina
 */
@WebServlet(name = "Formulairecontroleur", urlPatterns = {"/Formulairecontroleur"})
public class Formulairecontroleur extends HttpServlet {

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
            out.println("<title>Servlet Formulairecontroleur</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Formulairecontroleur at " + request.getContextPath() + "</h1>");
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
            
            
        
            
            List<Laboratoir> les_laboratoir = null;
            List<Uniter> les_uniter = null;
            List<Age> les_age = null;
            List<Maladie> les_maladie = null;
            List<Produit> les_produit = null;
            List<TypeProduit> les_type_produit = null;
            List<Sexe> les_type_sexe = null;
            
            
            
            try{
                // test connection ici 
                Connection conn = Dbconnection.getConnection(); 
                les_laboratoir = Laboratoir.getAll(conn);
                les_uniter = Uniter.getAll(conn);
                les_age  = Age.getAll(conn);
                les_maladie   = Maladie.getAll(conn);
                les_produit = Produit.getAll(conn);
                
                les_type_produit = TypeProduit.getAll(conn);
                les_type_sexe = Sexe.getAll(conn);
                
                

            }catch(SQLException e){
            // eurreru ici 
               out.println(e.getMessage());
           }

          request.setAttribute("les_laboratoir", les_laboratoir ); // le anarana andraisana azy
          request.setAttribute("les_uniter", les_uniter ); // le anarana andraisana azy
          request.setAttribute("les_age", les_age ); 
          request.setAttribute("les_maladie", les_maladie );
          request.setAttribute("les_produit", les_produit );
          request.setAttribute("les_type_produit", les_type_produit );
          request.setAttribute("les_sexe", les_type_sexe);
          
          RequestDispatcher dispatcher = request.getRequestDispatcher("insertionbase.jsp");
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
            if(signe.equals("laboratoire")){
                String nom = request.getParameter("nom");
                String adresse = request.getParameter("adresse");
                String contact = request.getParameter("contact");
                
                 try{
                // test connection ici
                         Connection conn = Dbconnection.getConnection(); 
                         Laboratoir une_laboratoir = new Laboratoir(nom, adresse, contact);
                         une_laboratoir.insert(conn);

                     }catch(SQLException e){
                     // eurreru ici 
                        out.println(e.getMessage());
                    }
                 
                 response.sendRedirect(request.getContextPath() + "/Formulairecontroleur");
            }
            else if(signe.equals("produit")){
                String nom = request.getParameter("nom");
                String description = request.getParameter("description");
                String laboratoirid = request.getParameter("laboratoirId");
                String  age = request.getParameter("ageId");
                String idtypeproduit = request.getParameter("produit_id");
                
                 try{
                // test connection ici
                         Connection conn = Dbconnection.getConnection(); 
                         int number = Integer.parseInt(laboratoirid);
                         int intage = Integer.parseInt(age);
                         int intidtype_produit = Integer.parseInt(idtypeproduit);
                         TypeProduit une_type_produit = TypeProduit.getById(conn, intidtype_produit);
                         Age une_age = new Age();
                         une_age.setId(intage);
                         Laboratoir une_laboratoir = new Laboratoir();
                         une_laboratoir.setId(number);
                         Produit une_produit = new Produit(nom , description , une_laboratoir , une_age , une_type_produit);
                         une_produit.insert(conn);
                         out.print(intage);

                     }catch(SQLException e){
                     // eurreru ici 
                        out.println(e.getMessage());
                    }
                 
                 response.sendRedirect(request.getContextPath() + "/Formulairecontroleur");
            }
            else if(signe.equals("matier_premier")){
                String nom = request.getParameter("nom");
                String prix = request.getParameter("prix");
                String uniterId = request.getParameter("uniterId");
                
                try{
                // test connection ici
                         Connection conn = Dbconnection.getConnection(); 
                         int number = Integer.parseInt(uniterId);
                         double prix_double = Double.parseDouble(prix);
                         Uniter une_uniter = new Uniter();
                         une_uniter.setId(number);
                         MatierPremier une_produit = new MatierPremier(nom , prix_double , une_uniter);
                         une_produit.insert(conn);

                     }catch(SQLException e){
                     // eurreru ici 
                        out.println(e.getMessage());
                    }
                 
                response.sendRedirect(request.getContextPath() + "/Formulairecontroleur");
               
            }
            
            else if(signe.equals("maladie")){
                String nom = request.getParameter("nom");
                String description = request.getParameter("description");
               
                try{
                // test connection ici
                         Connection conn = Dbconnection.getConnection(); 
                         Maladie une_produit = new Maladie(nom , description);
                         une_produit.insert(conn);

                     }catch(SQLException e){
                     // eurreru ici 
                        out.println(e.getMessage());
                    }
                 
                response.sendRedirect(request.getContextPath() + "/Formulairecontroleur");
               
            } 
            
            else if(signe.equals("age")){
                String age_debu = request.getParameter("ager_debu");
                String age_fin = request.getParameter("age_fin");
                String description= request.getParameter("description");
                
               
                try{
                // test connection ici
                    int numberdebu = Integer.parseInt(age_debu);
                    int numberfin = Integer.parseInt(age_fin);
                         Connection conn = Dbconnection.getConnection(); 
                         Age une_produit = new Age(numberdebu , description , numberfin);
                         une_produit.insert(conn);

                     }catch(SQLException e){
                     // eurreru ici 
                        out.println(e.getMessage());
                    }
                 
                response.sendRedirect(request.getContextPath() + "/Formulairecontroleur");
               
            } 
            
            else if(signe.equals("gerison")){
                String maladiid = request.getParameter("idmaladie");
                String produitid = request.getParameter("idproduit");
                String pourcetage = request.getParameter("pourcentage");
                
               
                try{
                // test connection ici
                    int numbermaladie = Integer.parseInt(maladiid);
                    int numbeproduit = Integer.parseInt(produitid);
                    double pourcetagedouble = Double.parseDouble(pourcetage);
                    
                    Maladie une_maladie = new Maladie();
                    une_maladie.setId(numbermaladie);
                    Produit une_produit = new Produit();
                    une_produit.setId(numbeproduit);
                    Connection conn = Dbconnection.getConnection(); 
                    Querison une_gerison = new Querison(une_maladie , une_produit , pourcetagedouble);
                    une_gerison.insert(conn);

                     }catch(SQLException e){
                     // eurreru ici 
                        out.println(e.getMessage());
                    }
                 
                response.sendRedirect(request.getContextPath() + "/Formulairecontroleur");
               
            } else if(signe.equals("typeproduit")){
                String nom = request.getParameter("nom");
                
                try{
                // test connection ici
                         Connection conn = Dbconnection.getConnection(); 
                         TypeProduit une_produit = new TypeProduit(nom);
                         une_produit.insert(conn);

                     }catch(SQLException e){
                     // eurreru ici 
                        out.println(e.getMessage());
                    }
                 
                response.sendRedirect(request.getContextPath() + "/Formulairecontroleur");
            } else if(signe.equals("client")){
                String nom = request.getParameter("nom");
                 String prenom = request.getParameter("prenom");
                 
                 
                 try{
                // test connection ici
                         Connection conn = Dbconnection.getConnection(); 
                         Client une_client = new Client(nom , prenom);
                         une_client.insert(conn);

                     }catch(SQLException e){
                     // eurreru ici 
                        out.println(e.getMessage());
                    }
                 
                response.sendRedirect(request.getContextPath() + "/Formulairecontroleur");
                 
                 
            }else if(signe.equals("vendeur")){
                String nom = request.getParameter("nom");
                 String prenom = request.getParameter("prenom");
                 String value_sexe = request.getParameter("sexe");
                 
                  int numbe_sexe = Integer.parseInt(value_sexe);
                 
                 
                 try{
                // test connection ici
                         Connection conn = Dbconnection.getConnection(); 
                         Sexe sexe_venndeur = Sexe.getById(conn, numbe_sexe);
                         Vendeur une_client = new Vendeur(nom , prenom , sexe_venndeur);
                         une_client.insert(conn);

                     }catch(SQLException e){
                     // eurreru ici 
                        out.println(e.getMessage());
                    }
                 
                response.sendRedirect(request.getContextPath() + "/Formulairecontroleur");
                 
                 
            }
        }
        
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