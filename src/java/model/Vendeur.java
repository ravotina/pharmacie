
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Vendeur {
    private int id;
    private String nom;
    private String prenom;
    Sexe sexe;

    // Constructeur par défaut
    public Vendeur() {
    }

    // Constructeur avec tous les paramètres
    public Vendeur(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    // Constructeur sans ID (pour insertion)
    public Vendeur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
    
    // Constructeur sans ID (pour insertion)
    public Vendeur(String nom, String prenom , Sexe sexe) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    // Méthode pour insérer un enregistrement dans la table Client
    public int insert(Connection conn) throws SQLException {
        String query = "INSERT INTO Vendeur (nom, prenom , id_sexe) VALUES (?, ? , ?) RETURNING id";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.nom);
            stmt.setString(2, this.prenom);
            stmt.setInt(3, this.getSexe().getId());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    this.id = rs.getInt(1); // Récupération de l'ID généré
                }
            }
        }
        return this.id;
    }

    // Méthode pour récupérer tous les enregistrements de la table Client
    public static List<Vendeur> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM Vendeur";
        List<Vendeur> clientList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Vendeur client = new Vendeur();
                client.setId(rs.getInt("id"));
                client.setNom(rs.getString("nom"));
                client.setPrenom(rs.getString("prenom"));
                client.setSexe(Sexe.getById(conn, rs.getInt("id_sexe")));
                clientList.add(client);
            }
        }
        return clientList;
    }

    // Méthode pour récupérer un client spécifique par ID
    public static Vendeur getById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM Vendeur WHERE id = ?";
        Vendeur client = null;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    client = new Vendeur();
                    client.setId(rs.getInt("id"));
                    client.setNom(rs.getString("nom"));
                    client.setPrenom(rs.getString("prenom"));
                    client.setSexe(Sexe.getById(conn, rs.getInt("id_sexe")));
                }
            }
        }
        return client;
    }

    // Méthode pour mettre à jour un enregistrement
    public int update(Connection conn) throws SQLException {
        String query = "UPDATE Vendeur SET nom = ?, prenom = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.nom);
            stmt.setString(2, this.prenom);
            stmt.setInt(3, this.id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes affectées
        }
    }

    // Méthode pour supprimer un enregistrement par ID
    public static int deleteById(Connection conn, int id) throws SQLException {
        String query = "DELETE FROM Vendeur WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes supprimées
        }
    }

    @Override
    public String toString() {
        return "Client{" +
               "id=" + id +
               ", nom='" + nom + '\'' +
               ", prenom='" + prenom + '\'' +
               '}';
    }
}


