/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ravo tina
 */
public class Dbconnection {

    // URL de connexion à PostgreSQL
    private static final String URL = "jdbc:postgresql://localhost:5432/pharmacie"; // Remplacez "nom_base" par le nom de votre base
    private static final String USER = "postgres"; // Remplacez "nom_utilisateur" par votre nom d'utilisateur PostgreSQL
    private static final String PASSWORD = "postgres"; // Remplacez "mot_de_passe" par votre mot de passe PostgreSQL

    /**
     * Méthode pour établir une connexion avec la base de données
     * 
     * @return Connection objet représentant la connexion
     * @throws SQLException si une erreur de connexion se produit
     */
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            // Charger le driver PostgreSQL
            Class.forName("org.postgresql.Driver");

            // Établir la connexion
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Connexion réussie !");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver PostgreSQL introuvable : " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erreur de connexion : " + e.getMessage());
            throw e;
        }
        return connection;
    }

    /**
     * Méthode pour fermer une connexion
     * 
     * @param connection La connexion à fermer
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connexion fermée avec succès !");
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}

