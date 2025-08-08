package model;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitDetaille {
    private int id;
    private double prix;
    private Date dateExpiration;
    private Date dateFabrication;
    private Produit produit; // Objet Produit représentant la clé étrangère

    // Constructeur par défaut
    public ProduitDetaille() {
    }

    // Constructeur avec paramètres
    public ProduitDetaille(int id, double prix, Date dateExpiration, Date dateFabrication, Produit produit) {
        this.id = id;
        this.prix = prix;
        this.dateExpiration = dateExpiration;
        this.dateFabrication = dateFabrication;
        this.produit = produit;
    }

    // Constructeur sans ID (pour insertion)
    public ProduitDetaille(double prix, Date dateExpiration, Date dateFabrication, Produit produit) {
        this.prix = prix;
        this.dateExpiration = dateExpiration;
        this.dateFabrication = dateFabrication;
        this.produit = produit;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public Date getDateFabrication() {
        return dateFabrication;
    }

    public void setDateFabrication(Date dateFabrication) {
        this.dateFabrication = dateFabrication;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    // Méthode pour insérer un produit détaillé
    public int insert(Connection conn) throws SQLException {
        String query = "INSERT INTO produit_detaille (prix, date_expiration, date_fabrication, id_produit) VALUES (?, ?, ?, ?) RETURNING id";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1,this.prix);
            stmt.setDate(2, this.dateExpiration);
            stmt.setDate(3, this.dateFabrication);
            stmt.setInt(4, this.produit.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    this.id = rs.getInt(1); // Récupération de l'ID généré
                }
            }
        }
        return this.id;
    }

    // Méthode pour récupérer tous les produits détaillés
    public static List<ProduitDetaille> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM produit_detaille order by id_produit , date_fabrication , date_expiration";
        List<ProduitDetaille> produitDetailleList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProduitDetaille produitDetaille = new ProduitDetaille();
                produitDetaille.setId(rs.getInt("id"));
                produitDetaille.setPrix(rs.getDouble("prix"));
                produitDetaille.setDateExpiration(rs.getDate("date_expiration"));
                produitDetaille.setDateFabrication(rs.getDate("date_fabrication"));
                produitDetaille.setProduit(Produit.getById(conn, rs.getInt("id_produit")));
                produitDetailleList.add(produitDetaille);
            }
        }
        return produitDetailleList;
    }

    // Méthode pour récupérer un produit détaillé spécifique par ID
    public static ProduitDetaille getById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM produit_detaille WHERE id = ?";
        ProduitDetaille produitDetaille = null;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    produitDetaille = new ProduitDetaille();
                    produitDetaille.setId(rs.getInt("id"));
                    produitDetaille.setPrix(rs.getDouble("prix"));
                    produitDetaille.setDateExpiration(rs.getDate("date_expiration"));
                    produitDetaille.setDateFabrication(rs.getDate("date_fabrication"));
                    produitDetaille.setProduit(Produit.getById(conn, rs.getInt("id_produit")));
                }
            }
        }
        return produitDetaille;
    }

    // Méthode pour mettre à jour un produit détaillé
    public int update(Connection conn) throws SQLException {
        String query = "UPDATE produit_detaille SET prix = ?, date_expiration = ?, date_fabrication = ?, id_produit = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, this.prix);
            stmt.setDate(2, this.dateExpiration);
            stmt.setDate(3, this.dateFabrication);
            stmt.setInt(4, this.produit.getId());
            stmt.setInt(5, this.id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes affectées
        }
    }

    // Méthode pour supprimer un produit détaillé par ID
    public static int deleteById(Connection conn, int id) throws SQLException {
        String query = "DELETE FROM produit_detaille WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes supprimées
        }
    }

    @Override
    public String toString() {
        return "ProduitDetaille{" +
               "id=" + id +
               ", prix=" + prix +
               ", dateExpiration=" + dateExpiration +
               ", dateFabrication=" + dateFabrication +
               ", produit=" + produit +
               '}';
    }
}
