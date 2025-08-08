/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Ravo tina
 * Created: 7 janv. 2025
 */

-- Insérer des données dans la table uniter
INSERT INTO uniter (initer) VALUES 
('Kilogramme'), 
('Litre'), 
('Unité');

-- Insérer des données dans la table matier_premier
INSERT INTO matier_premier (nom, prix, id_uniter) VALUES 
('Eau distillée', 500, 1),
('Sucre', 2000, 1),
('Sel', 1000, 1),
('Alcool', 1500, 2),
('Vitamines', 2500, 3);

-- Insérer des données dans la table laboratoir
INSERT INTO laboratoir (nom, adress, contact) VALUES 
('Laboratoire Alpha', 'Lot I A 23', '0321234567'),
('Laboratoire Beta', 'Lot II B 45', '0329876543');

-- Insérer des données dans la table pharmacie
INSERT INTO pharmacie (nom, adress, contact) VALUES 
('Pharmacie Centrale', 'Rue Principale 1', '0321122334'),
('Pharmacie de la Paix', 'Avenue 12', '0324455667');

-- Insérer des données dans la table maladie
INSERT INTO maladie (nom, description) VALUES 
('Fièvre', 'Température élevée'),
('Mal de tête', 'Douleur intense à la tête'),
('Grippe', 'Maladie virale avec symptômes de rhume'),
('Diabète', 'Excès de sucre dans le sang'),
('Hypertension', 'Pression artérielle élevée'),
('Anémie', 'Manque de globules rouges'),
('Asthme', 'Maladie respiratoire');

-- Insérer des données dans la table stock_produit
INSERT INTO stock_produit (daty, description, id_pharmacie) VALUES 
(CURRENT_DATE, 'Stock initial', 1),
(CURRENT_DATE, 'Stock durgence', 2),
(CURRENT_DATE, 'Stock régulier', 1),
(CURRENT_DATE, 'Stock spécial', 2),
(CURRENT_DATE, 'Réapprovisionnement', 1),
(CURRENT_DATE, 'Promotion', 2),
(CURRENT_DATE, 'Stock saisonnier', 1),
(CURRENT_DATE, 'Stock normal', 2);

-- Insérer des données dans la table produit
INSERT INTO produit (nom, description, id_laboratoir) VALUES 
('Paracétamol', 'Médicament contre la douleur', 1),
('Ibuprofène', 'Anti-inflammatoire', 1),
('Vitamine C', 'Complément alimentaire', 2),
('Amoxicilline', 'Antibiotique', 2);

-- Insérer des données dans la table stock_matiere
INSERT INTO stock_matiere (daty, description, id_laboratoir) VALUES 
(CURRENT_DATE, 'Stock initial de matières premières', 1),
(CURRENT_DATE, 'Réapprovisionnement matières premières', 2),
(CURRENT_DATE, 'Stock régulier', 1),
(CURRENT_DATE, 'Stock spécial', 2),
(CURRENT_DATE, 'Matières durgence', 1),
(CURRENT_DATE, 'Matières promotionnelles', 2),
(CURRENT_DATE, 'Saisonnier', 1),
(CURRENT_DATE, 'Normal', 2);

-- Insérer des données dans la table mouvement_stock_matier
INSERT INTO mouvement_stock_matier (entre, sorti, prix, id_matier_premier, id_stock_matiere) VALUES 
(50, 10, 500, 1, 1),
(30, 5, 1000, 2, 2),
(25, 2, 2000, 3, 3),
(40, 20, 1500, 4, 4),
(60, 15, 2500, 5, 5),
(45, 10, 1800, 2, 6),
(55, 20, 3000, 1, 7),
(35, 12, 1500, 3, 8),
(65, 18, 500, 1, 1),
(75, 25, 1000, 2, 2),
(85, 30, 2000, 3, 3),
(95, 35, 1500, 4, 4),
(105, 40, 2500, 5, 5),
(115, 45, 1800, 2, 6);

-- Insérer des données dans la table produit_detaille
INSERT INTO produit_detaille (prix, date_expiration, date_fabrication, id_produit) VALUES 
(1500, '2025-12-31', '2024-01-01', 1),
(2000, '2025-12-31', '2024-01-01', 2),
(2500, '2026-12-31', '2025-01-01', 3),
(3000, '2026-12-31', '2025-01-01', 4),
(1800, '2025-06-30', '2023-06-30', 1),
(2200, '2025-06-30', '2023-06-30', 2),
(2750, '2026-06-30', '2024-06-30', 3);

-- Insérer des données dans la table mouvement_stock_produit
INSERT INTO mouvement_stock_produit (entre, sorti, prix, id_produit_detaille, id_stock_produit) VALUES 
(10, 2, 1500, 1, 1),
(20, 5, 2000, 2, 2),
(15, 3, 2500, 3, 3),
(12, 4, 3000, 4, 4),
(25, 10, 1800, 5, 5),
(18, 6, 2200, 6, 6),
(22, 7, 2750, 7, 7),
(30, 8, 1500, 1, 8),
(14, 9, 2000, 2, 1),
(35, 10, 2500, 3, 2),
(40, 11, 3000, 4, 3),
(22, 5, 1800, 5, 4),
(25, 6, 2200, 6, 5),
(20, 7, 2750, 7, 6);

-- Insérer des données dans la table construct_produit
INSERT INTO construct_produit (quantiter, id_produit_detaille, id_matier_premier) VALUES 
(10, 1, 1),
(20, 2, 2),
(30, 3, 3),
(40, 4, 4),
(15, 5, 5),
(25, 6, 1),
(35, 7, 2),
(45, 1, 3);

-- Insérer des données dans la table querison
INSERT INTO querison (id_maladie, id_produit, pourcentage_de_guerison) VALUES 
(2, 2, 75.00),
(3, 3, 85.00),
(4, 4, 90.00),
(5, 1, 65.00),
(6, 2, 70.00),
(7, 3, 50.00),
(1, 4, 95.00),
(2, 1, 88.00),
(3, 2, 78.00);
