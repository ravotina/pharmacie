package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant la table `uniter`.
 */
public class Uniter {
    private int id; // Identifiant unique
    private String initer; // Nom unique de l'unité

    // Constructeur par défaut
    public Uniter() {
    }

    // Constructeur avec paramètres
    public Uniter(int id, String initer) {
        this.id = id;
        this.initer = initer;
    }

    public Uniter(String initer) {
        this.initer = initer;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIniter() {
        return initer;
    }

    public void setIniter(String initer) {
        this.initer = initer;
    }

    // Méthode pour insérer une unité
    public int insert(Connection conn) throws SQLException {
        String query = "INSERT INTO uniter (initer) VALUES (?) RETURNING id";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.initer);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    this.id = rs.getInt(1); // Récupération de l'ID généré
                }
            }
        }
        return this.id;
    }

    // Méthode pour récupérer toutes les unités
    public static List<Uniter> getAll(Connection conn) throws SQLException {
        String query = "SELECT id, initer FROM uniter";
        List<Uniter> uniterList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Uniter uniter = new Uniter();
                uniter.setId(rs.getInt("id"));
                uniter.setIniter(rs.getString("initer"));
                uniterList.add(uniter);
            }
        }
        return uniterList;
    }

    // Méthode pour récupérer une unité par ID
    public static Uniter getById(Connection conn, int id) throws SQLException {
        String query = "SELECT id, initer FROM uniter WHERE id = ?";
        Uniter uniter = null;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    uniter = new Uniter();
                    uniter.setId(rs.getInt("id"));
                    uniter.setIniter(rs.getString("initer"));
                }
            }
        }
        return uniter;
    }

    // Méthode pour mettre à jour une unité
    public int update(Connection conn) throws SQLException {
        String query = "UPDATE uniter SET initer = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.initer);
            stmt.setInt(2, this.id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes affectées
        }
    }

    // Méthode pour supprimer une unité par ID
    public static int deleteById(Connection conn, int id) throws SQLException {
        String query = "DELETE FROM uniter WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes supprimées
        }
    }

    // Méthode toString pour afficher les informations de l'unité
    @Override
    public String toString() {
        return "Uniter{" +
               "id=" + id +
               ", initer='" + initer + '\'' +
               '}';
    }
}
