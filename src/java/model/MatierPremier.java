package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatierPremier {
    private int id;
    private String nom;
    private double prix;
    private Uniter uniter; // Objet représentant la clé étrangère

    // Constructeur par défaut
    public MatierPremier() {
    }

    // Constructeur avec paramètres
    public MatierPremier(int id, String nom, double prix, Uniter uniter) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.uniter = uniter;
    }

    public MatierPremier(String nom, double prix, Uniter uniter) {
        this.nom = nom;
        this.prix = prix;
        this.uniter = uniter;
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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Uniter getUniter() {
        return uniter;
    }

    public void setUniter(Uniter uniter) {
        this.uniter = uniter;
    }

    // Méthode pour insérer une matière première
    public int insert(Connection conn) throws SQLException {
        String query = "INSERT INTO matier_premier (nom, prix, id_uniter) VALUES (?, ?, ?) RETURNING id";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.nom);
            stmt.setDouble(2, this.prix);
            stmt.setInt(3, this.uniter.getId()); // Utilisation de l'ID de l'objet `Uniter`

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    this.id = rs.getInt(1); // Récupération de l'ID généré
                }
            }
        }
        return this.id;
    }

    // Méthode pour récupérer toutes les matières premières
    public static List<MatierPremier> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM matier_premier";
        List<MatierPremier> matierList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MatierPremier matier = new MatierPremier();
                matier.setId(rs.getInt("id"));
                matier.setNom(rs.getString("nom"));
                matier.setPrix(rs.getDouble("prix"));
                matier.setUniter(Uniter.getById(conn, rs.getInt("id_uniter"))); // Chargement de l'objet `Uniter`
                matierList.add(matier);
            }
        }
        return matierList;
    }

    // Méthode pour récupérer une matière première par ID
    public static MatierPremier getById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM matier_premier WHERE id = ?";
        MatierPremier matier = null;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    matier = new MatierPremier();
                    matier.setId(rs.getInt("id"));
                    matier.setNom(rs.getString("nom"));
                    matier.setPrix(rs.getDouble("prix"));
                    matier.setUniter(Uniter.getById(conn, rs.getInt("id_uniter"))); // Chargement de l'objet `Uniter`
                }
            }
        }
        return matier;
    }

    // Méthode pour mettre à jour une matière première
    public int update(Connection conn) throws SQLException {
        String query = "UPDATE matier_premier SET nom = ?, prix = ?, id_uniter = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.nom);
            stmt.setDouble(2, this.prix);
            stmt.setInt(3, this.uniter.getId()); // Utilisation de l'ID de l'objet `Uniter`
            stmt.setInt(4, this.id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes affectées
        }
    }

    // Méthode pour supprimer une matière première par ID
    public static int deleteById(Connection conn, int id) throws SQLException {
        String query = "DELETE FROM matier_premier WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes supprimées
        }
    }

    @Override
    public String toString() {
        return "MatierPremier{" +
               "id=" + id +
               ", nom='" + nom + '\'' +
               ", prix=" + prix +
               ", uniter=" + uniter +
               '}';
    }
}
