package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MouvementStockMatier {
    private int id;
    private double entre;
    private double sorti;
    private double prix;
    private MatierPremier matierPremier; // Objet MatierPremier représentant la clé étrangère
    private StockMatiere stockMatiere;   // Objet StockMatiere représentant la clé étrangère

    // Constructeur par défaut
    public MouvementStockMatier() {
    }

    // Constructeur avec paramètres
    public MouvementStockMatier(int id, double entre, double sorti, double prix, MatierPremier matierPremier, StockMatiere stockMatiere) {
        this.id = id;
        this.entre = entre;
        this.sorti = sorti;
        this.prix = prix;
        this.matierPremier = matierPremier;
        this.stockMatiere = stockMatiere;
    }

    // Constructeur sans ID (pour insertion)
    public MouvementStockMatier(double entre, double sorti, double prix, MatierPremier matierPremier, StockMatiere stockMatiere) {
        this.entre = entre;
        this.sorti = sorti;
        this.prix = prix;
        this.matierPremier = matierPremier;
        this.stockMatiere = stockMatiere;
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

    public MatierPremier getMatierPremier() {
        return matierPremier;
    }

    public void setMatierPremier(MatierPremier matierPremier) {
        this.matierPremier = matierPremier;
    }

    public StockMatiere getStockMatiere() {
        return stockMatiere;
    }

    public void setStockMatiere(StockMatiere stockMatiere) {
        this.stockMatiere = stockMatiere;
    }

    // Méthode pour insérer un mouvement de stock
    public int insert(Connection conn) throws SQLException {
        String query = "INSERT INTO mouvement_stock_matier (entre, sorti, prix, id_matier_premier, id_stock_matiere) VALUES (?, ?, ?, ?, ?) RETURNING id";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, this.entre);
            stmt.setDouble(2, this.sorti);
            stmt.setDouble(3, this.prix);
            stmt.setInt(4, this.matierPremier.getId());
            stmt.setInt(5, this.stockMatiere.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    this.id = rs.getInt(1); // Récupération de l'ID généré
                }
            }
        }
        return this.id;
    }

    // Méthode pour récupérer tous les mouvements de stock de matières
    public static List<MouvementStockMatier> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM mouvement_stock_matier";
        List<MouvementStockMatier> mouvementList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MouvementStockMatier mouvement = new MouvementStockMatier();
                mouvement.setId(rs.getInt("id"));
                mouvement.setEntre(rs.getDouble("entre"));
                mouvement.setSorti(rs.getDouble("sorti"));
                mouvement.setPrix(rs.getDouble("prix"));
                mouvement.setMatierPremier(MatierPremier.getById(conn, rs.getInt("id_matier_premier")));
                mouvement.setStockMatiere(StockMatiere.getById(conn, rs.getInt("id_stock_matiere")));
                mouvementList.add(mouvement);
            }
        }
        return mouvementList;
    }

    // Méthode pour récupérer un mouvement de stock spécifique par ID
    public static MouvementStockMatier getById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM mouvement_stock_matier WHERE id = ?";
        MouvementStockMatier mouvement = null;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    mouvement = new MouvementStockMatier();
                    mouvement.setId(rs.getInt("id"));
                    mouvement.setEntre(rs.getDouble("entre"));
                    mouvement.setSorti(rs.getDouble("sorti"));
                    mouvement.setPrix(rs.getDouble("prix"));
                    mouvement.setMatierPremier(MatierPremier.getById(conn, rs.getInt("id_matier_premier")));
                    mouvement.setStockMatiere(StockMatiere.getById(conn, rs.getInt("id_stock_matiere")));
                }
            }
        }
        return mouvement;
    }

    // Méthode pour mettre à jour un mouvement de stock
    public int update(Connection conn) throws SQLException {
        String query = "UPDATE mouvement_stock_matier SET entre = ?, sorti = ?, prix = ?, id_matier_premier = ?, id_stock_matiere = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, this.entre);
            stmt.setDouble(2, this.sorti);
            stmt.setDouble(3, this.prix);
            stmt.setInt(4, this.matierPremier.getId());
            stmt.setInt(5, this.stockMatiere.getId());
            stmt.setInt(6, this.id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes affectées
        }
    }

    // Méthode pour supprimer un mouvement de stock par ID
    public static int deleteById(Connection conn, int id) throws SQLException {
        String query = "DELETE FROM mouvement_stock_matier WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes supprimées
        }
    }

    @Override
    public String toString() {
        return "MouvementStockMatier{" +
               "id=" + id +
               ", entre=" + entre +
               ", sorti=" + sorti +
               ", prix=" + prix +
               ", matierPremier=" + matierPremier +
               ", stockMatiere=" + stockMatiere +
               '}';
    }
}
