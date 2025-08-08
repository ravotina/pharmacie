package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Laboratoir {
    private int id;
    private String nom;
    private String adress;
    private String contact;

    // Constructeur par défaut
    public Laboratoir() {
    }

    // Constructeur avec paramètres
    public Laboratoir(int id, String nom, String adress, String contact) {
        this.id = id;
        this.nom = nom;
        this.adress = adress;
        this.contact = contact;
    }

    public Laboratoir(String nom, String adress, String contact) {
        this.nom = nom;
        this.adress = adress;
        this.contact = contact;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    // Méthode pour insérer un laboratoire
    public int insert(Connection conn) throws SQLException {
        String query = "INSERT INTO laboratoir (nom, adress, contact) VALUES (?, ?, ?) RETURNING id";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.nom);
            stmt.setString(2, this.adress);
            stmt.setString(3, this.contact);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    this.id = rs.getInt(1); // Récupération de l'ID généré
                }
            }
        }
        return this.id;
    }

    // Méthode pour récupérer tous les laboratoires
    public static List<Laboratoir> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM laboratoir";
        List<Laboratoir> labList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Laboratoir lab = new Laboratoir();
                lab.setId(rs.getInt("id"));
                lab.setNom(rs.getString("nom"));
                lab.setAdress(rs.getString("adress"));
                lab.setContact(rs.getString("contact"));
                labList.add(lab);
            }
        }
        return labList;
    }

    // Méthode pour récupérer un laboratoire par ID
    public static Laboratoir getById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM laboratoir WHERE id = ?";
        Laboratoir lab = null;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    lab = new Laboratoir();
                    lab.setId(rs.getInt("id"));
                    lab.setNom(rs.getString("nom"));
                    lab.setAdress(rs.getString("adress"));
                    lab.setContact(rs.getString("contact"));
                }
            }
        }
        return lab;
    }

    // Méthode pour mettre à jour un laboratoire
    public int update(Connection conn) throws SQLException {
        String query = "UPDATE laboratoir SET nom = ?, adress = ?, contact = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.nom);
            stmt.setString(2, this.adress);
            stmt.setString(3, this.contact);
            stmt.setInt(4, this.id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes affectées
        }
    }

    // Méthode pour supprimer un laboratoire par ID
    public static int deleteById(Connection conn, int id) throws SQLException {
        String query = "DELETE FROM laboratoir WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes supprimées
        }
    }

    @Override
    public String toString() {
        return "Laboratoir{" +
               "id=" + id +
               ", nom='" + nom + '\'' +
               ", adress='" + adress + '\'' +
               ", contact='" + contact + '\'' +
               '}';
    }
}
