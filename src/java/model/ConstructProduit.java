package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConstructProduit {
    private int id;
    private double quantiter; // Changement de type de NUMERIC à double
    private ProduitDetaille produitDetaille; // Objet ProduitDetaille représentant la clé étrangère
    private MatierPremier matierPremier; // Objet MatierPremier représentant la clé étrangère

    // Constructeur par défaut
    public ConstructProduit() {
    }

    // Constructeur avec paramètres
    public ConstructProduit(int id, double quantiter, ProduitDetaille produitDetaille, MatierPremier matierPremier) {
        this.id = id;
        this.quantiter = quantiter;
        this.produitDetaille = produitDetaille;
        this.matierPremier = matierPremier;
    }

    // Constructeur sans ID (pour insertion)
    public ConstructProduit(double quantiter, ProduitDetaille produitDetaille, MatierPremier matierPremier) {
        this.quantiter = quantiter;
        this.produitDetaille = produitDetaille;
        this.matierPremier = matierPremier;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getQuantiter() {
        return quantiter;
    }

    public void setQuantiter(double quantiter) {
        this.quantiter = quantiter;
    }

    public ProduitDetaille getProduitDetaille() {
        return produitDetaille;
    }

    public void setProduitDetaille(ProduitDetaille produitDetaille) {
        this.produitDetaille = produitDetaille;
    }

    public MatierPremier getMatierPremier() {
        return matierPremier;
    }

    public void setMatierPremier(MatierPremier matierPremier) {
        this.matierPremier = matierPremier;
    }

    // Méthode pour insérer un constructeur de produit
    public int insert(Connection conn) throws SQLException {
        String query = "INSERT INTO construct_produit (quantiter, id_produit_detaille, id_matier_premier) VALUES (?, ?, ?) RETURNING id";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, this.quantiter);
            stmt.setInt(2, this.produitDetaille.getId());
            stmt.setInt(3, this.matierPremier.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    this.id = rs.getInt(1); // Récupération de l'ID généré
                }
            }
        }
        return this.id;
    }

    // Méthode pour récupérer tous les constructeurs de produits
    public static List<ConstructProduit> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM construct_produit";
        List<ConstructProduit> constructProduitList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ConstructProduit constructProduit = new ConstructProduit();
                constructProduit.setId(rs.getInt("id"));
                constructProduit.setQuantiter(rs.getDouble("quantiter"));
                constructProduit.setProduitDetaille(ProduitDetaille.getById(conn, rs.getInt("id_produit_detaille")));
                constructProduit.setMatierPremier(MatierPremier.getById(conn, rs.getInt("id_matier_premier")));
                constructProduitList.add(constructProduit);
            }
        }
        return constructProduitList;
    }

    // Méthode pour récupérer un constructeur de produit spécifique par ID
    public static ConstructProduit getById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM construct_produit WHERE id = ?";
        ConstructProduit constructProduit = null;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    constructProduit = new ConstructProduit();
                    constructProduit.setId(rs.getInt("id"));
                    constructProduit.setQuantiter(rs.getDouble("quantiter"));
                    constructProduit.setProduitDetaille(ProduitDetaille.getById(conn, rs.getInt("id_produit_detaille")));
                    constructProduit.setMatierPremier(MatierPremier.getById(conn, rs.getInt("id_matier_premier")));
                }
            }
        }
        return constructProduit;
    }

    // Méthode pour mettre à jour un constructeur de produit
    public int update(Connection conn) throws SQLException {
        String query = "UPDATE construct_produit SET quantiter = ?, id_produit_detaille = ?, id_matier_premier = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, this.quantiter);
            stmt.setInt(2, this.produitDetaille.getId());
            stmt.setInt(3, this.matierPremier.getId());
            stmt.setInt(4, this.id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes affectées
        }
    }

    // Méthode pour supprimer un constructeur de produit par ID
    public static int deleteById(Connection conn, int id) throws SQLException {
        String query = "DELETE FROM construct_produit WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes supprimées
        }
    }

    @Override
    public String toString() {
        return "ConstructProduit{" +
               "id=" + id +
               ", quantiter=" + quantiter +
               ", produitDetaille=" + produitDetaille +
               ", matierPremier=" + matierPremier +
               '}';
    }
}
