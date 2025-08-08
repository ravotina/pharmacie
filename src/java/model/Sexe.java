package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Sexe {
    private int id;
    private String nom;

    // Constructeur par défaut
    public Sexe() {
    }

    // Constructeur avec paramètres
    public Sexe(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    // Constructeur sans ID (pour insertion)
    public Sexe(String nom) {
        this.nom = nom;
    }

    // Getters et Setters
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

    // Méthode pour insérer un enregistrement dans la table Sexe
    public int insert(Connection conn) throws SQLException {
        String query = "INSERT INTO sexe (nom) VALUES (?) RETURNING id";
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

    // Méthode pour récupérer tous les enregistrements de la table Sexe
    public static List<Sexe> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM sexe";
        List<Sexe> sexeList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Sexe sexe = new Sexe();
                sexe.setId(rs.getInt("id"));
                sexe.setNom(rs.getString("nom"));
                sexeList.add(sexe);
            }
        }
        return sexeList;
    }

    // Méthode pour récupérer un enregistrement spécifique par ID
    public static Sexe getById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM sexe WHERE id = ?";
        Sexe sexe = null;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    sexe = new Sexe();
                    sexe.setId(rs.getInt("id"));
                    sexe.setNom(rs.getString("nom"));
                }
            }
        }
        return sexe;
    }

    // Méthode pour mettre à jour un enregistrement
    public int update(Connection conn) throws SQLException {
        String query = "UPDATE sexe SET nom = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.nom);
            stmt.setInt(2, this.id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes affectées
        }
    }

    // Méthode pour supprimer un enregistrement par ID
    public static int deleteById(Connection conn, int id) throws SQLException {
        String query = "DELETE FROM sexe WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes supprimées
        }
    }

    @Override
    public String toString() {
        return "Sexe{" +
               "id=" + id +
               ", nom='" + nom + '\'' +
               '}';
    }
}
