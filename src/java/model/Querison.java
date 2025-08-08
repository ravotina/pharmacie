package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Querison {
    private Maladie maladie; // Objet Maladie représentant la clé étrangère
    private Produit produit; // Objet Produit représentant la clé étrangère
    private double pourcentageDeGuerison; // Changement de type de NUMERIC à double

    // Constructeur par défaut
    public Querison() {
    }

    // Constructeur avec paramètres
    public Querison(Maladie maladie, Produit produit, double pourcentageDeGuerison) {
        this.maladie = maladie;
        this.produit = produit;
        this.pourcentageDeGuerison = pourcentageDeGuerison;
    }

    // Getters et setters
    public Maladie getMaladie() {
        return maladie;
    }

    public void setMaladie(Maladie maladie) {
        this.maladie = maladie;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public double getPourcentageDeGuerison() {
        return pourcentageDeGuerison;
    }

    public void setPourcentageDeGuerison(double pourcentageDeGuerison) {
        this.pourcentageDeGuerison = pourcentageDeGuerison;
    }

    // Méthode pour insérer une guérison dans la table
    public void insert(Connection conn) throws SQLException {
        String query = "INSERT INTO querison (id_maladie, id_produit, pourcentage_de_guerison) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, this.maladie.getId());
            stmt.setInt(2, this.produit.getId());
            stmt.setDouble(3, this.pourcentageDeGuerison);
            stmt.executeUpdate();
        }
    }

    // Méthode pour récupérer toutes les guérisons
    public static List<Querison> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM querison";
        List<Querison> querisonList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Maladie maladie = Maladie.getById(conn, rs.getInt("id_maladie"));
                Produit produit = Produit.getById(conn, rs.getInt("id_produit"));
                double pourcentageDeGuerison = rs.getDouble("pourcentage_de_guerison");

                Querison querison = new Querison(maladie, produit, pourcentageDeGuerison);
                querisonList.add(querison);
            }
        }
        return querisonList;
    }

    // Méthode pour récupérer une guérison spécifique par les clés étrangères
    public static Querison getByMaladieAndProduit(Connection conn, int idMaladie, int idProduit) throws SQLException {
        String query = "SELECT * FROM querison WHERE id_maladie = ? AND id_produit = ?";
        Querison querison = null;
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idMaladie);
            stmt.setInt(2, idProduit);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Maladie maladie = Maladie.getById(conn, rs.getInt("id_maladie"));
                    Produit produit = Produit.getById(conn, rs.getInt("id_produit"));
                    double pourcentageDeGuerison = rs.getDouble("pourcentage_de_guerison");

                    querison = new Querison(maladie, produit, pourcentageDeGuerison);
                }
            }
        }
        return querison;
    }

    // Méthode pour mettre à jour une guérison
    public void update(Connection conn) throws SQLException {
        String query = "UPDATE querison SET pourcentage_de_guerison = ? WHERE id_maladie = ? AND id_produit = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, this.pourcentageDeGuerison);
            stmt.setInt(2, this.maladie.getId());
            stmt.setInt(3, this.produit.getId());
            stmt.executeUpdate();
        }
    }

    // Méthode pour supprimer une guérison
    public static void deleteByMaladieAndProduit(Connection conn, int idMaladie, int idProduit) throws SQLException {
        String query = "DELETE FROM querison WHERE id_maladie = ? AND id_produit = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idMaladie);
            stmt.setInt(2, idProduit);
            stmt.executeUpdate();
        }
    }

    @Override
    public String toString() {
        return "Querison{" +
               "maladie=" + maladie +
               ", produit=" + produit +
               ", pourcentageDeGuerison=" + pourcentageDeGuerison +
               '}';
    }
}
