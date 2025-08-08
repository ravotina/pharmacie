CREATE TABLE uniter(
   id SERIAL,
   initer VARCHAR(50) ,
   PRIMARY KEY(id),
   UNIQUE(initer)
);

CREATE TABLE matier_premier(
   id SERIAL,
   nom VARCHAR(50)  NOT NULL,
   prix MONEY,
   id_uniter INTEGER NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_uniter) REFERENCES uniter(id)
);

CREATE TABLE laboratoir(
   id SERIAL,
   nom VARCHAR(50) ,
   adress VARCHAR(50) ,
   contact VARCHAR(50) ,
   PRIMARY KEY(id),
   UNIQUE(contact)
);

CREATE TABLE pharmacie(
   id SERIAL,
   nom VARCHAR(50) ,
   adress VARCHAR(50) ,
   contact VARCHAR(50) ,
   PRIMARY KEY(id),
   UNIQUE(contact)
);

CREATE TABLE maladie(
   id SERIAL,
   nom VARCHAR(50)  NOT NULL,
   description VARCHAR(50) ,
   PRIMARY KEY(id)
);

CREATE TABLE stock_produit(
   id SERIAL,
   daty DATE NOT NULL,
   description VARCHAR(50) ,
   id_pharmacie INTEGER NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_pharmacie) REFERENCES pharmacie(id)
);

CREATE TABLE produit(
   id SERIAL,
   nom VARCHAR(50)  NOT NULL,
   description VARCHAR(50) ,
   id_laboratoir INTEGER NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_laboratoir) REFERENCES laboratoir(id)
);

CREATE TABLE stock_matiere(
   id SERIAL,
   daty DATE NOT NULL,
   description VARCHAR(50) ,
   id_laboratoir INTEGER NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_laboratoir) REFERENCES laboratoir(id)
);

CREATE TABLE mouvement_stock_matier(
   id SERIAL,
   entre NUMERIC(5,2)  ,
   sorti NUMERIC(5,2)  ,
   prix MONEY,
   id_matier_premier INTEGER NOT NULL,
   id_stock_matiere INTEGER NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_matier_premier) REFERENCES matier_premier(id),
   FOREIGN KEY(id_stock_matiere) REFERENCES stock_matiere(id)
);

CREATE TABLE produit_detaille(
   id SERIAL,
   prix MONEY,
   date_expiration DATE NOT NULL,
   date_fabrication DATE NOT NULL,
   id_produit INTEGER NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_produit) REFERENCES produit(id)
);

CREATE TABLE mouvement_stock_produit(
   id SERIAL,
   entre NUMERIC(5,2)  ,
   sorti NUMERIC(5,2)  ,
   prix MONEY,
   id_produit_detaille INTEGER NOT NULL,
   id_stock_produit INTEGER NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_produit_detaille) REFERENCES produit_detaille(id),
   FOREIGN KEY(id_stock_produit) REFERENCES stock_produit(id)
);

CREATE TABLE construct_produit(
   id SERIAL,
   quantiter NUMERIC(10,2)  ,
   id_produit_detaille INTEGER NOT NULL,
   id_matier_premier INTEGER NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_produit_detaille) REFERENCES produit_detaille(id),
   FOREIGN KEY(id_matier_premier) REFERENCES matier_premier(id)
);

CREATE TABLE querison(
   id_maladie INTEGER,
   id_produit INTEGER,
   pourcentage_de_guerison NUMERIC(3,2)  ,
   PRIMARY KEY(id_maladie, id_produit),
   FOREIGN KEY(id_maladie) REFERENCES maladie(id),
   FOREIGN KEY(id_produit) REFERENCES produit(id)
);


CREATE TABLE typeproduit(
   id SERIAL,
   nom VARCHAR(50) ,
   PRIMARY KEY(id)
);

ALTER TABLE matier_premier
ALTER COLUMN prix TYPE decimal(10,2);



ALTER TABLE vendeur
ADD COLUMN id_sexe int;

ALTER TABLE produit
ADD CONSTRAINT fk_produit FOREIGN KEY (id_typeproduit)
REFERENCES typeproduit(id);



ALTER TABLE produit ALTER COLUMN nom TYPE VARCHAR(255);
ALTER TABLE produit ALTER COLUMN description TYPE VARCHAR(255);



-- Mettre à jour l'ID d'âge pour le produit "Paracétamol"
UPDATE produit
SET id_age = 1
WHERE id = 1;

-- Mettre à jour l'ID d'âge pour le produit "Ibuprofène"
UPDATE produit
SET id_age = 2
WHERE id = 2;

-- Mettre à jour l'ID d'âge pour le produit "Vitamine C"
UPDATE produit
SET id_age = 3
WHERE id = 3;

-- Mettre à jour l'ID d'âge pour le produit "Amoxicilline"
UPDATE produit
SET id_age = 4
WHERE id = 4;

-- Mettre à jour l'ID d'âge pour le produit "eferalgon"
UPDATE Age
SET description = 'bebe'
WHERE id = 1;


INSERT INTO typeproduit (nom) VALUES ('Injectable');
INSERT INTO typeproduit (nom) VALUES ('Buvable');
INSERT INTO typeproduit (nom) VALUES ('Comprimer');



UPDATE produit SET id_typeproduit = 1 WHERE id = 6; -- Doliprane Enfant : Injectable
UPDATE produit SET id_typeproduit = 2 WHERE id = 1; -- Paracétamol : Buvable
UPDATE produit SET id_typeproduit = 2 WHERE id = 2; -- Ibuprofène : Buvable
UPDATE produit SET id_typeproduit = 2 WHERE id = 5; -- Eferalgon : Buvable
UPDATE produit SET id_typeproduit = 3 WHERE id = 3; -- Vitamine C : Comprimer
UPDATE vendeur SET id_sexe = 1 WHERE id = ; -- Amoxicilline : Comprimer




// liste client nividy androany




<form action="<%= request.getContextPath() %>/VenteControleur" method="GET">
                            <div class="row mb-3">
                            <label for="selectLaboratoir" class="col-sm-2 col-form-label">Type produit</label>
                            <div class="col-sm-10">
                                <select class="form-select" id="selectLaboratoir" name="id_type_produit" >
                                    <option value="">type produit</option>
                                    <!-- Options de laboratoires Ã  remplir dynamiquement -->

                                    <% 
                                        if(les_type_produit!=null){
                                            for(TypeProduit type_produit : les_type_produit){ %>
                                            <option value="<%= type_produit.getId() %>"><%= type_produit.getNom() %> </option>
                                        <% }
                                        }
                                    %>
                                </select>
                            </div>
                            </div>
                                
                                
                           <div class="row mb-3">
                            <label for="selectLaboratoir" class="col-sm-2 col-form-label">Age</label>
                            <div class="col-sm-10">
                                <select class="form-select" id="selectLaboratoir" name="idage" >
                                    <option value="">Age</option>
                                    <!-- Options de laboratoires Ã  remplir dynamiquement -->

                                    <% 
                                        if(les_age!=null){
                                            for(Age une_age : les_age){ %>
                                            <option value="<%= une_age.getId() %>"><%= une_age.getAgeDebut() %> - <%= une_age.getAgeFin() %> </option>
                                        <% }
                                        }
                                    %>
                                </select>
                            </div>
                            </div>
                                
                                
                         <!-- Bouton de soumission -->
                        <div class="row mb-3">
                            <div class="col-sm-10 offset-sm-2">
                                <button type="submit" name="signef" value="recherche" class="btn btn-primary">recherche</button>
                            </div>
                        </div>
              
                    </form>



INSERT INTO sexe (nom) VALUES ('Femme');
INSERT INTO sexe (nom) VALUES ('Homme');




<div class="row mb-3">
                            <label for="selectLaboratoir" class="col-sm-2 col-form-label">Vendeur</label>
                            <div class="col-sm-10">
                                <select class="form-select" id="selectLaboratoir" name="idVendeur" >
                                    <option value="">Vendeur</option>
                                    <!-- Options de laboratoires Ã  remplir dynamiquement -->

                                    <% 
                                        if(les_maladie!=null){
                                            for(Vendeur une_maladie : les_vendeur){ %>
                                            <option value="<%= une_maladie.getId() %>"><%= une_maladie.getNom() %>  <%= une_maladie.getPrenom() %> </option>
                                        <% }
                                        }
                                    %>
                                </select>
                            </div>
                            </div>