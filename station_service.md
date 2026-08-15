
---

#  Spécifications du Projet : Gestion d'une Station-Service

##  1. Objectifs du Projet (Traitements et Logique Métier)

L'application a pour but d'informatiser et d'automatiser la gestion quotidienne d'une station-service. Le système doit être capable de réaliser les opérations suivantes :

* **Gestion des données (CRUD) :** Création, lecture, mise à jour et suppression des enregistrements pour toutes les entités du système (Clients, Produits, Services, Entrées, Achats, Entretiens).
* **Recherche dynamique :** Possibilité de rechercher un client par son nom (utilisation de l'opérateur `LIKE %..%`).
* **Génération de documents :** Création automatique d'un reçu au format **PDF** pour les clients après une prestation d'entretien.
* **Gestion et alerte de stock :** Surveillance continue du stock de produits (carburants/huiles) avec le déclenchement d'une alerte visuelle si la quantité d'un produit passe en dessous de **10 litres**.
* **Analyses Financières et Statistiques :**
* Calcul de la **recette totale** accumulée par la station (achats de carburant + entretiens).
* Génération d'un **histogramme** représentant l'évolution des recettes par mois.
* Identification et affichage du **Top 5 des clients les plus participatifs** (fidélité/dépenses).



---

##  2. Modèle de Données (Structure des Tables)

Le modèle relationnel a été normalisé pour éviter les redondances, faciliter les calculs financiers et garantir l'historisation des prix.

### Entités Principales (Référentiels)

**1. CLIENT** *(Gestion centralisée de la clientèle)*

* `numClient` (String) : Identifiant unique du client **[PK]**
* `nomClient` (String) : Nom complet du client
* `telephone` (String) : Numéro de contact

**2. PRODUIT** *(Catalogue des carburants et lubrifiants)*

* `numProd` (String) : Identifiant unique du produit **[PK]**
* `Designation` (String) : Nom du produit (ex: Gasoil, Sans Plomb)
* `prixUnitaireActuel` (Int) : Prix de vente actuel (utilisé pour les futurs calculs)
* `stockActuel` (Int) : Quantité disponible en cuve/rayon

**3. SERVICE** *(Catalogue des prestations)*

* `numServ` (String) : Identifiant unique du service **[PK]**
* `designation` (String) : Nom de la prestation (ex: Vidange, Lavage)
* `prixActuel` (Int) : Tarif actuel de la prestation

### Entités de Mouvements (Transactions)

**4. ENTREE** *(Approvisionnement des stocks)*

* `numEntree` (String) : Identifiant unique du bon de livraison **[PK]**
* `numProd` (String) : Produit approvisionné **[FK -> PRODUIT]**
* `quantiteAjoutee` (Int) : Volume réceptionné (en litres)
* `dateEntree` (Date) : Date de l'approvisionnement

**5. ACHAT** *(Ventes directes de produits)*

* `numAchat` (String) : Numéro de ticket de caisse **[PK]**
* `numProd` (String) : Produit acheté **[FK -> PRODUIT]**
* `numClient` (String) : Client ayant effectué l'achat **[FK -> CLIENT]**
* `nbrLitre` (Int) : Quantité achetée
* `montantTotal` (Int) : Montant payé au moment de l'achat *(Historisation du prix)*
* `dateAchat` (Date) : Date de la transaction

**6. ENTRETIEN** *(Prestations de services réalisées)*

* `numEntr` (String) : Numéro de la fiche d'intervention **[PK]**
* `numServ` (String) : Service réalisé **[FK -> SERVICE]**
* `numClient` (String) : Client facturé **[FK -> CLIENT]**
* `Immatriculation_voiture` (String) : Plaque d'immatriculation du véhicule concerné
* `montantTotal` (Int) : Montant de la prestation payé à l'instant T *(Historisation du prix)*
* `dateEntretien` (Date) : Date de l'intervention

> **Légende :**
> **[PK]** = Primary Key (Clé Primaire)
> **[FK]** = Foreign Key (Clé Étrangère)

---
