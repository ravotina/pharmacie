package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockMatiere {
    private int id;
    private Date daty;
    private String description;
    private Laboratoir laboratoir; // Objet Laboratoir représentant la clé étrangère

    // Constructeur par défaut
    public StockMatiere() {
    }

    // Constructeur avec paramètres
    public StockMatiere(int id, Date daty, String description, Laboratoir laboratoir) {
        this.id = id;
        this.daty = daty;
        this.description = description;
        this.laboratoir = laboratoir;
    }

    // Constructeur sans ID (pour insertion)
    public StockMatiere(Date daty, String description, Laboratoir laboratoir) {
        this.daty = daty;
        this.description = description;
        this.laboratoir = laboratoir;
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

    public Laboratoir getLaboratoir() {
        return laboratoir;
    }

    public void setLaboratoir(Laboratoir laboratoir) {
        this.laboratoir = laboratoir;
    }

    // Méthode pour insérer un stock de matière
    public int insert(Connection conn) throws SQLException {
        String query = "INSERT INTO stock_matiere (daty, description, id_laboratoir) VALUES (?, ?, ?) RETURNING id";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, this.daty);
            stmt.setString(2, this.description);
            stmt.setInt(3, this.laboratoir.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    this.id = rs.getInt(1); // Récupération de l'ID généré
                }
            }
        }
        return this.id;
    }

    // Méthode pour récupérer tous les stocks de matières
    public static List<StockMatiere> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM stock_matiere";
        List<StockMatiere> stockMatiereList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                StockMatiere stockMatiere = new StockMatiere();
                stockMatiere.setId(rs.getInt("id"));
                stockMatiere.setDaty(rs.getDate("daty"));
                stockMatiere.setDescription(rs.getString("description"));
                stockMatiere.setLaboratoir(Laboratoir.getById(conn, rs.getInt("id_laboratoir")));
                stockMatiereList.add(stockMatiere);
            }
        }
        return stockMatiereList;
    }

    // Méthode pour récupérer un stock de matière spécifique par ID
    public static StockMatiere getById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM stock_matiere WHERE id = ?";
        StockMatiere stockMatiere = null;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    stockMatiere = new StockMatiere();
                    stockMatiere.setId(rs.getInt("id"));
                    stockMatiere.setDaty(rs.getDate("daty"));
                    stockMatiere.setDescription(rs.getString("description"));
                    stockMatiere.setLaboratoir(Laboratoir.getById(conn, rs.getInt("id_laboratoir")));
                }
            }
        }
        return stockMatiere;
    }

    // Méthode pour mettre à jour un stock de matière
    public int update(Connection conn) throws SQLException {
        String query = "UPDATE stock_matiere SET daty = ?, description = ?, id_laboratoir = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, this.daty);
            stmt.setString(2, this.description);
            stmt.setInt(3, this.laboratoir.getId());
            stmt.setInt(4, this.id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes affectées
        }
    }

    // Méthode pour supprimer un stock de matière par ID
    public static int deleteById(Connection conn, int id) throws SQLException {
        String query = "DELETE FROM stock_matiere WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes supprimées
        }
    }

    @Override
    public String toString() {
        return "StockMatiere{" +
               "id=" + id +
               ", daty=" + daty +
               ", description='" + description + '\'' +
               ", laboratoir=" + laboratoir +
               '}';
    }
}
