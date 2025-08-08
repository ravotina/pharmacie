package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MouvementStockProduit {
    private int id;
    private double entre;
    private double sorti;
    private double prix;
    private ProduitDetaille produitDetaille; // Objet ProduitDetaille représentant la clé étrangère
    private StockProduit stockProduit; // Objet StockProduit représentant la clé étrangère

    // Constructeur par défaut
    public MouvementStockProduit() {
    }

    // Constructeur avec paramètres
    public MouvementStockProduit(int id, double entre, double sorti, double prix, ProduitDetaille produitDetaille, StockProduit stockProduit) {
        this.id = id;
        this.entre = entre;
        this.sorti = sorti;
        this.prix = prix;
        this.produitDetaille = produitDetaille;
        this.stockProduit = stockProduit;
    }

    // Constructeur sans ID (pour insertion)
    public MouvementStockProduit(double entre, double sorti, double prix, ProduitDetaille produitDetaille, StockProduit stockProduit) {
        this.entre = entre;
        this.sorti = sorti;
        this.prix = prix;
        this.produitDetaille = produitDetaille;
        this.stockProduit = stockProduit;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getEntre() {
        return entre;
    }

    public void setEntre(double entre) {
        this.entre = entre;
    }

    public double getSorti() {
        return sorti;
    }

    public void setSorti(double sorti) {
        this.sorti = sorti;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public ProduitDetaille getProduitDetaille() {
        return produitDetaille;
    }

    public void setProduitDetaille(ProduitDetaille produitDetaille) {
        this.produitDetaille = produitDetaille;
    }

    public StockProduit getStockProduit() {
        return stockProduit;
    }

    public void setStockProduit(StockProduit stockProduit) {
        this.stockProduit = stockProduit;
    }

    // Méthode pour insérer un mouvement de stock produit
    public int insert(Connection conn) throws SQLException {
        String query = "INSERT INTO mouvement_stock_produit (entre, sorti, prix, id_produit_detaille, id_stock_produit) VALUES (?, ?, ?, ?, ?) RETURNING id";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, this.entre);
            stmt.setDouble(2, this.sorti);
            stmt.setDouble(3, this.prix);
            stmt.setInt(4, this.produitDetaille.getId());
            stmt.setInt(5, this.stockProduit.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    this.id = rs.getInt(1); // Récupération de l'ID généré
                }
            }
        }
        return this.id;
    }

    // Méthode pour récupérer tous les mouvements de stock produit
    public static List<MouvementStockProduit> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM mouvement_stock_produit";
        List<MouvementStockProduit> mouvementStockProduitList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MouvementStockProduit mouvementStockProduit = new MouvementStockProduit();
                mouvementStockProduit.setId(rs.getInt("id"));
                mouvementStockProduit.setEntre(rs.getDouble("entre"));
                mouvementStockProduit.setSorti(rs.getDouble("sorti"));
                mouvementStockProduit.setPrix(rs.getDouble("prix"));
                mouvementStockProduit.setProduitDetaille(ProduitDetaille.getById(conn, rs.getInt("id_produit_detaille")));
                mouvementStockProduit.setStockProduit(StockProduit.getById(conn, rs.getInt("id_stock_produit")));
                mouvementStockProduitList.add(mouvementStockProduit);
            }
        }
        return mouvementStockProduitList;
    }

    // Méthode pour récupérer un mouvement de stock produit spécifique par ID
    public static MouvementStockProduit getById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM mouvement_stock_produit WHERE id = ?";
        MouvementStockProduit mouvementStockProduit = null;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    mouvementStockProduit = new MouvementStockProduit();
                    mouvementStockProduit.setId(rs.getInt("id"));
                    mouvementStockProduit.setEntre(rs.getDouble("entre"));
                    mouvementStockProduit.setSorti(rs.getDouble("sorti"));
                    mouvementStockProduit.setPrix(rs.getDouble("prix"));
                    mouvementStockProduit.setProduitDetaille(ProduitDetaille.getById(conn, rs.getInt("id_produit_detaille")));
                    mouvementStockProduit.setStockProduit(StockProduit.getById(conn, rs.getInt("id_stock_produit")));
                }
            }
        }
        return mouvementStockProduit;
    }

    // Méthode pour mettre à jour un mouvement de stock produit
    public int update(Connection conn) throws SQLException {
        String query = "UPDATE mouvement_stock_produit SET entre = ?, sorti = ?, prix = ?, id_produit_detaille = ?, id_stock_produit = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, this.entre);
            stmt.setDouble(2, this.sorti);
            stmt.setDouble(3, this.prix);
            stmt.setInt(4, this.produitDetaille.getId());
            stmt.setInt(5, this.stockProduit.getId());
            stmt.setInt(6, this.id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes affectées
        }
    }

    // Méthode pour supprimer un mouvement de stock produit par ID
    public static int deleteById(Connection conn, int id) throws SQLException {
        String query = "DELETE FROM mouvement_stock_produit WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes supprimées
        }
    }

    @Override
    public String toString() {
        return "MouvementStockProduit{" +
               "id=" + id +
               ", entre=" + entre +
               ", sorti=" + sorti +
               ", prix=" + prix +
               ", produitDetaille=" + produitDetaille +
               ", stockProduit=" + stockProduit +
               '}';
    }
}
