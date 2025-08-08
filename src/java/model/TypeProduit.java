package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeProduit {
    private int id;
    private String nom;

    // Constructeur par défaut
    public TypeProduit() {
    }

    // Constructeur avec paramètres
    public TypeProduit(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    // Constructeur sans ID (pour insertion)
    public TypeProduit(String nom) {
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

    // Méthode pour insérer un type de produit
    public int insert(Connection conn) throws SQLException {
        String query = "INSERT INTO typeproduit (nom) VALUES (?) RETURNING id";
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

    // Méthode pour récupérer tous les types de produits
    public static List<TypeProduit> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM typeproduit";
        List<TypeProduit> typeProduits = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TypeProduit typeProduit = new TypeProduit();
                typeProduit.setId(rs.getInt("id"));
                typeProduit.setNom(rs.getString("nom"));
                typeProduits.add(typeProduit);
            }
        }
        return typeProduits;
    }

    // Méthode pour récupérer un type de produit spécifique par ID
    public static TypeProduit getById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM typeproduit WHERE id = ?";
        TypeProduit typeProduit = null;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    typeProduit = new TypeProduit();
                    typeProduit.setId(rs.getInt("id"));
                    typeProduit.setNom(rs.getString("nom"));
                }
            }
        }
        return typeProduit;
    }

    // Méthode pour mettre à jour un type de produit
    public int update(Connection conn) throws SQLException {
        String query = "UPDATE typeproduit SET nom = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.nom);
            stmt.setInt(2, this.id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes affectées
        }
    }

    // Méthode pour supprimer un type de produit par ID
    public static int deleteById(Connection conn, int id) throws SQLException {
        String query = "DELETE FROM typeproduit WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes supprimées
        }
    }

    @Override
    public String toString() {
        return "TypeProduit{" +
               "id=" + id +
               ", nom='" + nom + '\'' +
               '}';
    }
}
