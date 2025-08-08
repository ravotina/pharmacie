/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Mois {
    private int id;
    private String nom;

    // Constructeur par défaut
    public Mois() {
    }

    // Constructeur avec tous les paramètres
    public Mois(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    // Constructeur sans ID (pour insertion)
    public Mois(String nom) {
        this.nom = nom;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Méthode pour insérer un enregistrement dans la table
    public int insert(Connection conn) throws SQLException {
        String query = "INSERT INTO mois (nom) VALUES (?) RETURNING id";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.nom);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    this.id = rs.getInt(1); // Récupération de l'ID généré
                }
            }
        }
        return this.id;
    }

    // Méthode pour récupérer tous les enregistrements
    public static List<Mois> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM mois";
        List<Mois> moisList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Mois mois = new Mois();
                mois.setId(rs.getInt("id"));
                mois.setNom(rs.getString("nom"));
                moisList.add(mois);
            }
        }
        return moisList;
    }

    // Méthode pour récupérer un enregistrement spécifique par ID
    public static Mois getById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM mois WHERE id = ?";
        Mois mois = null;
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    mois = new Mois();
                    mois.setId(rs.getInt("id"));
                    mois.setNom(rs.getString("nom"));
                }
            }
        }
        return mois;
    }

    // Méthode pour mettre à jour un enregistrement
    public int update(Connection conn) throws SQLException {
        String query = "UPDATE mois SET nom = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.nom);
            stmt.setInt(2, this.id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes affectées
        }
    }

    // Méthode pour supprimer un enregistrement par ID
    public static int deleteById(Connection conn, int id) throws SQLException {
        String query = "DELETE FROM mois WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes supprimées
        }
    }

    @Override
    public String toString() {
        return "Mois{" +
               "id=" + id +
               ", nom='" + nom + '\'' +
               '}';
    }
}
