/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Conseile {
    private int id;
    private Mois mois; // Objet représentant la relation avec la table Mois
    private int anner;
    private Produit produit; // Objet représentant la relation avec la table Produit

    // Constructeur par défaut
    public Conseile() {
    }

    // Constructeur avec tous les paramètres
    public Conseile(int id, Mois mois, int anner, Produit produit) {
        this.id = id;
        this.mois = mois;
        this.anner = anner;
        this.produit = produit;
    }

    // Constructeur sans ID (pour insertion)
    public Conseile(Mois mois, int anner, Produit produit) {
        this.mois = mois;
        this.anner = anner;
        this.produit = produit;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mois getMois() {
        return mois;
    }

    public void setMois(Mois mois) {
        this.mois = mois;
    }

    public int getAnner() {
        return anner;
    }

    public void setAnner(int anner) {
        this.anner = anner;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    // Méthode pour insérer un enregistrement
    public int insert(Connection conn) throws SQLException {
        String query = "INSERT INTO conseile (id_1, anner, id_2) VALUES (?, ?, ?) RETURNING id";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, this.mois.getId()); // Utilise l'ID de l'objet Mois
            stmt.setInt(2, this.anner);
            stmt.setInt(3, this.produit.getId()); // Utilise l'ID de l'objet Produit
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    this.id = rs.getInt(1); // Récupération de l'ID généré
                }
            }
        }
        return this.id;
    }

    // Méthode pour récupérer tous les enregistrements
    public static List<Conseile> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM conseile order by anner , id_1 , id";
        List<Conseile> conseileList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Conseile conseile = new Conseile();
                conseile.setId(rs.getInt("id"));

                // Récupère l'objet Mois
                Mois mois = Mois.getById(conn, rs.getInt("id_1"));
                conseile.setMois(mois);

                conseile.setAnner(rs.getInt("anner"));

                // Récupère l'objet Produit
                Produit produit = Produit.getById(conn, rs.getInt("id_2"));
                conseile.setProduit(produit);

                conseileList.add(conseile);
            }
        }
        return conseileList;
    }

    // Méthode pour récupérer un enregistrement spécifique par ID
    public static Conseile getById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM conseile WHERE id = ?";
        Conseile conseile = null;
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    conseile = new Conseile();
                    conseile.setId(rs.getInt("id"));

                    // Récupère l'objet Mois
                    Mois mois = Mois.getById(conn, rs.getInt("id_1"));
                    conseile.setMois(mois);

                    conseile.setAnner(rs.getInt("anner"));

                    // Récupère l'objet Produit
                    Produit produit = Produit.getById(conn, rs.getInt("id_2"));
                    conseile.setProduit(produit);
                }
            }
        }
        return conseile;
    }

    // Méthode pour mettre à jour un enregistrement
    public int update(Connection conn) throws SQLException {
        String query = "UPDATE conseile SET id_1 = ?, anner = ?, id_2 = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, this.mois.getId()); // Utilise l'ID de l'objet Mois
            stmt.setInt(2, this.anner);
            stmt.setInt(3, this.produit.getId()); // Utilise l'ID de l'objet Produit
            stmt.setInt(4, this.id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes affectées
        }
    }

    // Méthode pour supprimer un enregistrement par ID
    public static int deleteById(Connection conn, int id) throws SQLException {
        String query = "DELETE FROM conseile WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes supprimées
        }
    }

    @Override
    public String toString() {
        return "Conseile{" +
               "id=" + id +
               ", mois=" + mois +
               ", anner=" + anner +
               ", produit=" + produit +
               '}';
    }
}
