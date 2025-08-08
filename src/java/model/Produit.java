package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Produit {
    private int id;
    private String nom;
    private String description;
    private Laboratoir laboratoir; // Objet Laboratoir représentant la clé étrangère
    private Age age;  
    private TypeProduit typeProduit;
    

    // Constructeur par défaut
    public Produit() {
    }

    // Constructeur avec paramètres
    public Produit(int id, String nom, String description, Laboratoir laboratoir) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.laboratoir = laboratoir;
    }

    // Constructeur sans ID (pour insertion)
    public Produit(String nom, String description, Laboratoir laboratoir) {
        this.nom = nom;
        this.description = description;
        this.laboratoir = laboratoir;
    }
    
    // Constructeur avec tous les paramètres
    public Produit(int id, String nom, String description, Laboratoir laboratoir, Age age) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.laboratoir = laboratoir;
        this.age = age;
    }

    // Constructeur sans ID (pour insertion)
    public Produit(String nom, String description, Laboratoir laboratoir, Age age) {
        this.nom = nom;
        this.description = description;
        this.laboratoir = laboratoir;
        this.age = age;
    }
    
    // Constructeur avec tous les paramètres
    public Produit(int id, String nom, String description, Laboratoir laboratoir, Age age ,TypeProduit typeProduit ) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.laboratoir = laboratoir;
        this.age = age;
        this.typeProduit = typeProduit;
    }

    // Constructeur sans ID (pour insertion)
    public Produit(String nom, String description, Laboratoir laboratoir, Age age , TypeProduit typeProduit) {
        this.nom = nom;
        this.description = description;
        this.laboratoir = laboratoir;
        this.age = age;
        this.typeProduit = typeProduit;
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

    public Laboratoir getLaboratoir() {
        return laboratoir;
    }

    public void setLaboratoir(Laboratoir laboratoir) {
        this.laboratoir = laboratoir;
    }
    
    
    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }
    
    public TypeProduit getTypeProduit() {
        return this.typeProduit;
    }

    public void setTypeProduit(TypeProduit typeProduit) {
        this.typeProduit = typeProduit;
    }

    // Méthode pour insérer un produit
    public int insert(Connection conn) throws SQLException {
        String query = "INSERT INTO produit (nom, description, id_laboratoir , id_age , id_typeproduit ) VALUES (?, ?, ? , ? , ?) RETURNING id";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.nom);
            stmt.setString(2, this.description);
            stmt.setInt(3, this.laboratoir.getId());
            stmt.setInt(4, this.age.getId());
            stmt.setInt(5, this.typeProduit.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    this.id = rs.getInt(1); // Récupération de l'ID généré
                }
            }
        }
        return this.id;
    }

    // Méthode pour récupérer tous les produits
    public static List<Produit> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM produit";
        List<Produit> produitList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produit produit = new Produit();
                produit.setId(rs.getInt("id"));
                produit.setNom(rs.getString("nom"));
                produit.setDescription(rs.getString("description"));
                produit.setLaboratoir(Laboratoir.getById(conn, rs.getInt("id_laboratoir")));
                produit.setAge(Age.getById(conn, rs.getInt("id_age")));
                produit.setTypeProduit(TypeProduit.getById(conn, rs.getInt("id_typeproduit")));
                produitList.add(produit);
            }
        }
        return produitList;
    }

    // Méthode pour récupérer un produit spécifique par ID
    public static Produit getById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM produit WHERE id = ?";
        Produit produit = null;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    produit = new Produit();
                    produit.setId(rs.getInt("id"));
                    produit.setNom(rs.getString("nom"));
                    produit.setDescription(rs.getString("description"));
                    produit.setLaboratoir(Laboratoir.getById(conn, rs.getInt("id_laboratoir")));
                    produit.setAge(Age.getById(conn, rs.getInt("id_age")));
                    produit.setTypeProduit(TypeProduit.getById(conn, rs.getInt("id_typeproduit")));
                    
                }
            }
        }
        return produit;
    }

    // Méthode pour mettre à jour un produit
    public int update(Connection conn) throws SQLException {
        String query = "UPDATE produit SET nom = ?, description = ?, id_laboratoir = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.nom);
            stmt.setString(2, this.description);
            stmt.setInt(3, this.laboratoir.getId());
            stmt.setInt(4, this.id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes affectées
        }
    }

    // Méthode pour supprimer un produit par ID
    public static int deleteById(Connection conn, int id) throws SQLException {
        String query = "DELETE FROM produit WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes supprimées
        }
    }

    @Override
    public String toString() {
        return "Produit{" +
               "id=" + id +
               ", nom='" + nom + '\'' +
               ", description='" + description + '\'' +
               ", laboratoir=" + laboratoir +
               '}';
    }
}
