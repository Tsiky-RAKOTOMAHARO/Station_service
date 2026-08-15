## Phase 1 : Base de Données

> Préparation du stockage des données.

* **Étape 1.1 :** Traduction du modèle de données validé en un script SQL de création de tables (définition des clés primaires et étrangères).
* **Étape 1.2 :** Exécution du script pour initialiser la base de données.

---

## Phase 2 : Architecture du Projet Java

> Structuration du projet (gestionnaire Maven) pour l'intégration des dépendances.

* **Étape 2.1 :** Définition de l'arborescence (Pattern MVC couplé au pattern DAO) pour la séparation de l'accès aux données et de l'interface.
* **Étape 2.2 :** Création des classes "Modèles" (Entités) représentant les tables de la base.
* **Étape 2.3 :** Sélection et ajout des dépendances nécessaires au projet.

---

## Phase 3 : Couche d'Accès aux Données (Backend)

> Gestion de la communication entre Java et PostgreSQL.

* **Étape 3.1 :** Création d'une classe Singleton pour la gestion de la connexion unique (Driver JDBC).
* **Étape 3.2 :** Création des interfaces et des classes DAO par entité intégrant la logique CRUD (`INSERT`, `SELECT`, `UPDATE`, `DELETE`).
* **Étape 3.3 :** Implémentation des requêtes spécifiques (recherche `LIKE`, calculs de statistiques type "Top 5").

---

## Phase 4 : Interface Graphique (Frontend JavaFX)

> Conception des écrans.

* **Étape 4.1 :** Maquettage des écrans principaux (Menu principal, Gestion des Ventes, Stocks, Tableau de bord).
* **Étape 4.2 :** Création des vues via des fichiers FXML pour séparer le design du code.
* **Étape 4.3 :** Création des contrôleurs JavaFX pour la gestion des événements (clics, navigation).

---

## Phase 5 : Intégration et Traitements Avancés

> Assemblage et fonctionnalités métier.

* **Étape 5.1 :** Liaison entre les contrôleurs JavaFX et les classes DAO (ex: affichage de données via `TableView`).
* **Étape 5.2 :** Implémentation de la logique de gestion des stocks (via triggers SQL ou logique métier Java).
* **Étape 5.3 :** Intégration d'une bibliothèque dédiée à la génération de reçus PDF.
* **Étape 5.4 :** Création d'un histogramme des recettes sur le tableau de bord via `BarChart`.

---

## Phase 6 : Finalisation

* **Étape 6.1 :** Tests fonctionnels complets.
* **Étape 6.2 :** Optimisation du code et mise en place de la gestion des erreurs (contrôle de saisie, validation des champs).


# Organisation des dossiers

````
.
├── pom.xml
├── README.md
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── station
│   │   │           ├── controller
│   │   │           │   ├── MainController.java
│   │   │           │   ├── StockController.java
│   │   │           │   └── VenteController.java
│   │   │           ├── dao
│   │   │           │   ├── ClientDao.java
│   │   │           │   └── ProduitDao.java
│   │   │           ├── database
│   │   │           │   └── DatabaseConnection.java
│   │   │           ├── Main.java
│   │   │           ├── model
│   │   │           └── service
│   │   │               └── PdfGenerator.java
│   │   └── resources
│   │       ├── css
│   │       │   └── style.css
│   │       ├── fxml
│   │       │   ├── dashboard.fxml
│   │       │   ├── stocks.fxml
│   │       │   └── ventes.fxml
│   │       └── images
│   └── test
└── station_service.md
