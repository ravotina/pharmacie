package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Maladie {
    private int id;
    private String nom;
    private String description;

    // Constructeur par défaut
    public Maladie() {
    }

    // Constructeur avec paramètres
    public Maladie(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public Maladie(String nom, String description) {
        this.nom = nom;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Méthode pour insérer une maladie
    public int insert(Connection conn) throws SQLException {
        String query = "INSERT INTO maladie (nom, description) VALUES (?, ?) RETURNING id";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.nom);
            stmt.setString(2, this.description);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    this.id = rs.getInt(1); // Récupération de l'ID généré
                }
            }
        }
        return this.id;
    }

    // Méthode pour récupérer toutes les maladies
    public static List<Maladie> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM maladie";
        List<Maladie> maladieList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Maladie maladie = new Maladie();
                maladie.setId(rs.getInt("id"));
                maladie.setNom(rs.getString("nom"));
                maladie.setDescription(rs.getString("description"));
                maladieList.add(maladie);
            }
        }
        return maladieList;
    }

    // Méthode pour récupérer une maladie par ID
    public static Maladie getById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM maladie WHERE id = ?";
        Maladie maladie = null;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    maladie = new Maladie();
                    maladie.setId(rs.getInt("id"));
                    maladie.setNom(rs.getString("nom"));
                    maladie.setDescription(rs.getString("description"));
                }
            }
        }
        return maladie;
    }

    // Méthode pour mettre à jour une maladie
    public int update(Connection conn) throws SQLException {
        String query = "UPDATE maladie SET nom = ?, description = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.nom);
            stmt.setString(2, this.description);
            stmt.setInt(3, this.id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes affectées
        }
    }

    // Méthode pour supprimer une maladie par ID
    public static int deleteById(Connection conn, int id) throws SQLException {
        String query = "DELETE FROM maladie WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes supprimées
        }
    }

    @Override
    public String toString() {
        return "Maladie{" +
               "id=" + id +
               ", nom='" + nom + '\'' +
               ", description='" + description + '\'' +
               '}';
    }
}
