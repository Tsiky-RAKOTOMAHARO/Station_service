--CLIENT
INSERT INTO CLIENT (numClient, nomClient, telephone) VALUES
('C001', 'Rakoto Jean',        '0341234567'),
('C002', 'Rabe Marie',         '0331122334'),
('C003', 'Andria Paul',        '0329988776'),
('C004', 'Rasoa Nirina',       '0348765432'),
('C005', 'Rakotomalala Eric',  '0335566778');

--PRODUIT 
INSERT INTO PRODUIT (numProd, designation, prixUnitaireActuel, stockActuel) VALUES
('P001', 'Gasoil',          4200, 500),
('P002', 'Sans Plomb',      4800, 300),
('P003', 'Huile Moteur 5W40', 15000, 8),   -- sous le seuil de 10 -> alerte stock
('P004', 'Pétrole Lampant', 3500, 150);

-- SERVICE
INSERT INTO SERVICE (numServ, designation, prixActuel) VALUES
('S001', 'Vidange',              50000),
('S002', 'Lavage complet',       15000),
('S003', 'Changement de pneus',  30000),
('S004', 'Diagnostic électronique', 40000);

--ENTREE
INSERT INTO ENTREE (numEntree, numProd, quantiteAjoutee, dateEntree) VALUES
('E001', 'P001', 1000, '2026-06-01'),
('E002', 'P002', 800,  '2026-06-01'),
('E003', 'P003', 20,   '2026-06-05'),
('E004', 'P004', 500,  '2026-06-10'),
('E005', 'P001', 500,  '2026-07-01');

-- ACHAT
INSERT INTO ACHAT (numAchat, numProd, numClient, nbrLitre, montantTotal, dateAchat) VALUES
('A001', 'P001', 'C001', 30, 126000, '2026-06-03'),
('A002', 'P002', 'C002', 20, 96000,  '2026-06-04'),
('A003', 'P001', 'C003', 50, 210000, '2026-06-15'),
('A004', 'P003', 'C001', 2,  30000,  '2026-06-16'),
('A005', 'P002', 'C004', 15, 72000,  '2026-07-02'),
('A006', 'P001', 'C005', 40, 168000, '2026-07-05'),
('A007', 'P004', 'C002', 25, 87500,  '2026-07-10');

-- ENTRETIEN 
INSERT INTO ENTRETIEN (numEntr, numServ, numClient, Immatriculation_voiture, montantTotal, dateEntretien) VALUES
('T001', 'S001', 'C001', '1234-TAA', 50000, '2026-06-05'),
('T002', 'S002', 'C002', '5678-TBB', 15000, '2026-06-10'),
('T003', 'S003', 'C003', '9012-TCC', 30000, '2026-06-20'),
('T004', 'S001', 'C004', '3456-TDD', 50000, '2026-07-01'),
('T005', 'S004', 'C005', '7890-TEE', 40000, '2026-07-08'),
('T006', 'S002', 'C001', '1234-TAA', 15000, '2026-07-12');