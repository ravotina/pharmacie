package model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Vente {
    private int id;
    private Date daty; // Correspond à la colonne DATE
    private double prix; // Correspond à NUMERIC(10,2)
    private ProduitDetaille produitDetaille; // Représente la clé étrangère vers produit_detaille
    private Client client;
    private Vendeur vendeur;
    double commission;
    

    // Constructeur par défaut
    public Vente() {
    }

    // Constructeur avec paramètres
    public Vente(int id, Date daty, double prix, ProduitDetaille produitDetaille) {
        this.id = id;
        this.daty = daty;
        this.prix = prix;
        this.produitDetaille = produitDetaille;
    }

    // Constructeur sans ID (pour insertion)
    public Vente(Date daty, double prix, ProduitDetaille produitDetaille) {
        this.daty = daty;
        this.prix = prix;
        this.produitDetaille = produitDetaille;
    }
    
    public Vente(Date daty, double prix, ProduitDetaille produitDetaille , Client client) {
        this.daty = daty;
        this.prix = prix;
        this.produitDetaille = produitDetaille;
        this.client = client;
    }
    
    
    public Vente(Date daty, double prix, ProduitDetaille produitDetaille , Client client , Vendeur vendeur  , double commission) {
        this.daty = daty;
        this.prix = prix;
        this.produitDetaille = produitDetaille;
        this.client = client;
        this.vendeur = vendeur;
        this.commission = commission;
    }
   

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public ProduitDetaille getProduitDetaille() {
        return produitDetaille;
    }

    public void setProduitDetaille(ProduitDetaille produitDetaille) {
        this.produitDetaille = produitDetaille;
    }
    
    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    
    public Vendeur getVendeur() {
        return this.vendeur;
    }

    public void setVendeur(Vendeur vendeur) {
        this.vendeur = vendeur;
    }
    
    
    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    // Méthode pour insérer une vente
    public int insert(Connection conn) throws SQLException {
        String query = "INSERT INTO vente (daty, prix, id_1 , id_2 , id_3 , commission) VALUES (?, ?, ? , ? , ? , ?) RETURNING id";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, this.daty);
            stmt.setDouble(2, this.prix);
            stmt.setInt(3, this.produitDetaille.getId());
            stmt.setInt(4, this.client.getId());
            stmt.setInt(5, this.vendeur.getId());
            stmt.setDouble(6, this.commission);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    this.id = rs.getInt(1); // Récupération de l'ID généré
                }
            }
        }
        return this.id;
    }

    // Méthode pour récupérer toutes les ventes
    public static List<Vente> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM vente";
        List<Vente> venteList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Vente vente = new Vente();
                vente.setId(rs.getInt("id"));
                vente.setDaty(rs.getDate("daty"));
                vente.setPrix(rs.getDouble("prix"));
                vente.setProduitDetaille(ProduitDetaille.getById(conn, rs.getInt("id_1")));
                vente.setClient(Client.getById(conn, rs.getInt("id_2")));
                vente.setVendeur(Vendeur.getById(conn, rs.getInt("id_3")));
                vente.setCommission(rs.getDouble("commission"));
                venteList.add(vente);
            }
        }
        return venteList;
    }

    // Méthode pour récupérer une vente spécifique par ID
    public static Vente getById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM vente WHERE id = ?";
        Vente vente = null;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    vente = new Vente();
                    vente.setId(rs.getInt("id"));
                    vente.setDaty(rs.getDate("daty"));
                    vente.setPrix(rs.getDouble("prix"));
                    vente.setProduitDetaille(ProduitDetaille.getById(conn, rs.getInt("id_1")));
                    vente.setClient(Client.getById(conn, rs.getInt("id_2")));
                    vente.setVendeur(Vendeur.getById(conn, rs.getInt("id_3")));
                    vente.setCommission(rs.getDouble("commission"));
                }
            }
        }
        return vente;
    }

    // Méthode pour mettre à jour une vente
    public int update(Connection conn) throws SQLException {
        String query = "UPDATE vente SET daty = ?, prix = ?, id_1 = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, this.daty);
            stmt.setDouble(2, this.prix);
            stmt.setInt(3, this.produitDetaille.getId());
            stmt.setInt(4, this.id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes affectées
        }
    }

    // Méthode pour supprimer une vente par ID
    public static int deleteById(Connection conn, int id) throws SQLException {
        String query = "DELETE FROM vente WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate(); // Retourne le nombre de lignes supprimées
        }
    }
    
    
    public static double sommecommition(List<Vente> les_vente){
        double resultat  = 0;
        if(les_vente != null){
            for(Vente une_vente : les_vente){
                resultat = resultat + une_vente.getCommission();
            }  
        }
        
        return resultat;
    }
    
    
    
    // String id_vendeur,
    
    public static List<Vente> filtrerVentes(List<Vente> les_vente,  String date_min, String date_max , String Sexe_id) {
        List<Vente> nouveau_vente = new ArrayList<>();

        for (Vente une_vente : les_vente) {
            boolean ajout = false;

            try {
                
                if(!Sexe_id.isEmpty() && !date_min.trim().isEmpty() && !date_max.trim().isEmpty()){
                    LocalDate localDate1 = LocalDate.parse(date_min.trim());
                    LocalDate localDate2 = LocalDate.parse(date_max.trim());
                    Date date_vente_sql1 = Date.valueOf(localDate1);
                    Date date_vente_sql2 = Date.valueOf(localDate2);
                    int sexe_id_int = Integer.parseInt(Sexe_id);
                    
                    if(une_vente.getDaty().after(date_vente_sql1) &&
                        une_vente.getDaty().before(date_vente_sql2) &&
                        une_vente.getVendeur().getSexe().getId() == sexe_id_int){
                        ajout = true;
                    }
                    
                }
                // Cas où seulement id_vendeur et date_min sont renseignés
                else if (!Sexe_id.isEmpty() && !date_min.trim().isEmpty() && date_max.trim().isEmpty()) {
                    LocalDate localDate1 = LocalDate.parse(date_min.trim());
                    Date date_vente_sql1 = Date.valueOf(localDate1);
                    //int int_id_vendeur = Integer.parseInt(id_vendeur);
                    int sexe_id_int = Integer.parseInt(Sexe_id);

                    if (une_vente.getDaty().after(date_vente_sql1) &&
                        une_vente.getVendeur().getSexe().getId() == sexe_id_int) {
                        ajout = true;
                    }
                }
                // Cas où seulement id_vendeur et date_max sont renseignés
                else if (!Sexe_id.isEmpty() && date_min.trim().isEmpty() && !date_max.trim().isEmpty()) {
                    LocalDate localDate2 = LocalDate.parse(date_max.trim());
                    Date date_vente_sql2 = Date.valueOf(localDate2);
                    //int int_id_vendeur = Integer.parseInt(id_vendeur);
                     int sexe_id_int = Integer.parseInt(Sexe_id);

                    if (une_vente.getDaty().before(date_vente_sql2) &&
                        une_vente.getVendeur().getSexe().getId() == sexe_id_int) {
                        ajout = true;
                    }
                }
                // Cas où seulement id_vendeur est renseigné
                else if (!Sexe_id.isEmpty() && date_min.trim().isEmpty() && date_max.trim().isEmpty()) {
                    //int int_id_vendeur = Integer.parseInt(id_vendeur);
                    int sexe_id_int = Integer.parseInt(Sexe_id);

                    if (une_vente.getVendeur().getSexe().getId() == sexe_id_int) {
                        ajout = true;
                    }
                }
                // Cas où aucun critère n'est renseigné
                else if (Sexe_id.isEmpty() && date_min.trim().isEmpty() && date_max.trim().isEmpty()) {
                    ajout = true; // Ajout de toutes les ventes si aucun filtre n'est appliqué
                }

                if (ajout) {
                    nouveau_vente.add(une_vente);
                }

            } catch (Exception e) {
                // Gestion des erreurs de parsing ou autres exceptions
                System.err.println("Erreur lors du traitement d'une vente : " + e.getMessage());
            }
        }

        return nouveau_vente;
    }

    @Override
    public String toString() {
        return "Vente{" +
               "id=" + id +
               ", daty=" + daty +
               ", prix=" + prix +
               ", produitDetaille=" + produitDetaille +
               '}';
    }
}
