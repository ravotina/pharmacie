package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockProduit {
    private int id;
    private Date daty;
    private String description;
    private Pharmacie pharmacie; // Objet Pharmacie représentant la clé étrangère

    // Constructeur par défaut
    public StockProduit() {
    }

    // Constructeur avec paramètres
    public StockProduit(int id, Date daty, String description, Pharmacie pharmacie) {
        this.id = id;
        this.daty = daty;
        this.description = description;
        this.pharmacie = pharmacie;
    }

    // Constructeur sans ID (pour insertion)
    public StockProduit(Date daty, String description, Pharmacie pharmacie) {
        this.daty = daty;
        this.description = description;
        this.pharmacie = pharmacie;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pharmacie getPharmacie() {
        return pharmacie;
    }

    public void setPharmacie(Pharmacie pharmacie) {
        this.pharmacie = pharmacie;
    }

    // Méthode pour insérer un produit dans le stock
    public int insert(Connection conn) throws SQLException {
        String query = "INSERT INTO stock_produit (daty, description, id_pharmacie) VALUES (?, ?, ?) RETURNING id";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, this.daty);
            stmt.setString(2, this.description);
            stmt.setInt(3, this.pharmacie.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    this.id = rs.getInt(1); // Récupération de l'ID généré
                }
            }
        }
        return this.id;
    }

    // Méthode pour récupérer tous les produits en stock
    public static List<StockProduit> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM stock_produit";
        List<StockProduit> stockProduitList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                StockProduit stockProduit = new StockProduit();
                stockProduit.setId(rs.getInt("id"));
                stockProduit.setDaty(rs.getDate("daty"));
                stockProduit.setDescription(rs.getString("description"));
                stockProduit.setPharmacie(Pharmacie.getById(conn, rs.getInt("id_pharmacie")));
                stockProduitList.add(stockProduit);
            }
        }
        return stockProduitList;
    }

    // Méthode pour récupérer un produit en stock par ID
    public static StockProduit getById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM stock_produit WHERE id = ?";
        StockProduit stockProduit = null;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    stockProduit = new StockProduit();
                    stockProduit.setId(rs.getInt("id"));
                    stockProduit.setDaty(rs.getDate("daty"));
                    stockProduit.setDescription(rs.getString("description"));
                    stockProduit.setPharmacie(Pharmacie.getById(conn, rs.getInt("id_pharmacie")));
                }
            }
        }
        return stockProduit;
    }

    // Méthode pour mettre à jour un produit en stock
    public int update(Connection conn) throws SQLException {
        String query = "UPDATE stock_produit SET daty = ?, description = ?, id_pharmacie = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, this.daty);
            stmt.setString(2, this.description);
            stmt.setInt(3, this.pharmacie.getId());
            stmt.setInt(4, this.id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes affectées
        }
    }

    // Méthode pour supprimer un produit en stock par ID
    public static int deleteById(Connection conn, int id) throws SQLException {
        String query = "DELETE FROM stock_produit WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes supprimées
        }
    }

    @Override
    public String toString() {
        return "StockProduit{" +
               "id=" + id +
               ", daty=" + daty +
               ", description='" + description + '\'' +
               ", pharmacie=" + pharmacie +
               '}';
    }
}
