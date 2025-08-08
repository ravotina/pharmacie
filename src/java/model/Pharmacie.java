package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Pharmacie {
    private int id;
    private String nom;
    private String adress;
    private String contact;

    // Constructeur par défaut
    public Pharmacie() {
    }

    // Constructeur avec paramètres
    public Pharmacie(int id, String nom, String adress, String contact) {
        this.id = id;
        this.nom = nom;
        this.adress = adress;
        this.contact = contact;
    }

    public Pharmacie(String nom, String adress, String contact) {
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

    // Méthode pour insérer une pharmacie
    public int insert(Connection conn) throws SQLException {
        String query = "INSERT INTO pharmacie (nom, adress, contact) VALUES (?, ?, ?) RETURNING id";
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

    // Méthode pour récupérer toutes les pharmacies
    public static List<Pharmacie> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM pharmacie";
        List<Pharmacie> pharmacieList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pharmacie pharmacie = new Pharmacie();
                pharmacie.setId(rs.getInt("id"));
                pharmacie.setNom(rs.getString("nom"));
                pharmacie.setAdress(rs.getString("adress"));
                pharmacie.setContact(rs.getString("contact"));
                pharmacieList.add(pharmacie);
            }
        }
        return pharmacieList;
    }

    // Méthode pour récupérer une pharmacie par ID
    public static Pharmacie getById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM pharmacie WHERE id = ?";
        Pharmacie pharmacie = null;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pharmacie = new Pharmacie();
                    pharmacie.setId(rs.getInt("id"));
                    pharmacie.setNom(rs.getString("nom"));
                    pharmacie.setAdress(rs.getString("adress"));
                    pharmacie.setContact(rs.getString("contact"));
                }
            }
        }
        return pharmacie;
    }

    // Méthode pour mettre à jour une pharmacie
    public int update(Connection conn) throws SQLException {
        String query = "UPDATE pharmacie SET nom = ?, adress = ?, contact = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.nom);
            stmt.setString(2, this.adress);
            stmt.setString(3, this.contact);
            stmt.setInt(4, this.id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes affectées
        }
    }

    // Méthode pour supprimer une pharmacie par ID
    public static int deleteById(Connection conn, int id) throws SQLException {
        String query = "DELETE FROM pharmacie WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes supprimées
        }
    }

    @Override
    public String toString() {
        return "Pharmacie{" +
               "id=" + id +
               ", nom='" + nom + '\'' +
               ", adress='" + adress + '\'' +
               ", contact='" + contact + '\'' +
               '}';
    }
}
