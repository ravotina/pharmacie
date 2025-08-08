/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlleur;

import connexion.Dbconnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.*;
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
import model.TypeProduit;
import model.Uniter;
import model.Vente;

/**
 *
 * @author Ravo tina
 */
@WebServlet(name = "ClientControleur", urlPatterns = {"/ClientControleur"})
public class ClientControleur extends HttpServlet {

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
            out.println("<title>Servlet ClientControleur</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClientControleur at " + request.getContextPath() + "</h1>");
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
        String signe = request.getParameter("signef");
        PrintWriter out = response.getWriter();

        if (signe != null) {
            String daty = request.getParameter("daty");

            List<Querison> les_querison = null;
            List<Laboratoir> les_laboratoir = null;
            List<Uniter> les_uniter = null;
            List<Age> les_age = null;
            List<Maladie> les_maladie = null;
            List<Produit> les_produit = null;
            List<Vente> les_vente = null;
            List<TypeProduit> les_type_produit = null;

            try {
                // Connexion à la base de données
                Connection conn = Dbconnection.getConnection();
                les_laboratoir = Laboratoir.getAll(conn);
                les_uniter = Uniter.getAll(conn);
                les_age = Age.getAll(conn);
                les_maladie = Maladie.getAll(conn);
                les_produit = Produit.getAll(conn);
                les_vente = Vente.getAll(conn);
                les_type_produit = TypeProduit.getAll(conn);
                les_querison = Querison.getAll(conn);

                List<Vente> nouveau_vente = new ArrayList<>();

                if (daty != null && !daty.trim().isEmpty()) {
                    try {
                        // Conversion de `daty` en LocalDate et SQL Date
                        LocalDate localDate1 = LocalDate.parse(daty.trim());
                        Date date_vente_sql = Date.valueOf(localDate1);

                        for (Vente une_vente : les_vente) {
                            if (date_vente_sql.equals(une_vente.getDaty())) {
                                nouveau_vente.add(une_vente);
                            }
                        }
                    } catch (Exception e) {
                        out.println("Erreur de conversion de la date : " + e.getMessage());
                    }
                } else {
                    // Si `daty` est vide ou null, tous les éléments sont ajoutés
                    nouveau_vente.addAll(les_vente);
                }

                // Ajout des attributs à la requête
                request.setAttribute("les_laboratoir", les_laboratoir);
                request.setAttribute("les_uniter", les_uniter);
                request.setAttribute("les_age", les_age);
                request.setAttribute("les_maladie", les_maladie);
                request.setAttribute("les_produit", les_produit);
                request.setAttribute("les_vente", nouveau_vente);
                request.setAttribute("les_type_produit", les_type_produit);

                // Redirection vers la JSP
                RequestDispatcher dispatcher = request.getRequestDispatcher("liste_vente.jsp");
                dispatcher.forward(request, response);

            } catch (SQLException ex) {
                out.println("Erreur SQL : " + ex.getMessage());
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
