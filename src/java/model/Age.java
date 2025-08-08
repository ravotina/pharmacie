package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Age {
    private int id;
    private int ageDebut;
    private String description;
    private int ageFin;

    // Constructeur par défaut
    public Age() {
    }

    // Constructeur avec tous les paramètres
    public Age(int id, int ageDebut, String description, int ageFin) {
        this.id = id;
        this.ageDebut = ageDebut;
        this.description = description;
        this.ageFin = ageFin;
    }

    // Constructeur sans ID (pour insertion)
    public Age(int ageDebut, String description, int ageFin) {
        this.ageDebut = ageDebut;
        this.description = description;
        this.ageFin = ageFin;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAgeDebut() {
        return ageDebut;
    }

    public void setAgeDebut(int ageDebut) {
        this.ageDebut = ageDebut;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAgeFin() {
        return ageFin;
    }

    public void setAgeFin(int ageFin) {
        this.ageFin = ageFin;
    }

    // Méthode pour insérer un enregistrement dans la table Age
    public int insert(Connection conn) throws SQLException {
        String query = "INSERT INTO Age (agedebu, description, agefin) VALUES (?, ?, ?) RETURNING id";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, this.ageDebut);
            stmt.setString(2, this.description);
            stmt.setInt(3, this.ageFin);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    this.id = rs.getInt(1); // Récupération de l'ID généré
                }
            }
        }
        return this.id;
    }

    // Méthode pour récupérer tous les enregistrements de la table Age
    public static List<Age> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM Age";
        List<Age> ageList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Age age = new Age();
                age.setId(rs.getInt("id"));
                age.setAgeDebut(rs.getInt("agedebu"));
                age.setDescription(rs.getString("description"));
                age.setAgeFin(rs.getInt("agefin"));
                ageList.add(age);
            }
        }
        return ageList;
    }

    // Méthode pour récupérer un enregistrement spécifique par ID
    public static Age getById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM Age WHERE id = ?";
        Age age = null;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    age = new Age();
                    age.setId(rs.getInt("id"));
                    age.setAgeDebut(rs.getInt("agedebu"));
                    age.setDescription(rs.getString("description"));
                    age.setAgeFin(rs.getInt("agefin"));
                }
            }
        }
        return age;
    }

    // Méthode pour mettre à jour un enregistrement
    public int update(Connection conn) throws SQLException {
        String query = "UPDATE Age SET agedebu = ?, description = ?, agefin = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, this.ageDebut);
            stmt.setString(2, this.description);
            stmt.setInt(3, this.ageFin);
            stmt.setInt(4, this.id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes affectées
        }
    }

    // Méthode pour supprimer un enregistrement par ID
    public static int deleteById(Connection conn, int id) throws SQLException {
        String query = "DELETE FROM Age WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes supprimées
        }
    }

    @Override
    public String toString() {
        return "Age{" +
               "id=" + id +
               ", ageDebut=" + ageDebut +
               ", description='" + description + '\'' +
               ", ageFin=" + ageFin +
               '}';
    }
}
