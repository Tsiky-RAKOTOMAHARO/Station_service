
CREATE TABLE CLIENT (
    numClient   VARCHAR(20)   PRIMARY KEY,
    nomClient   VARCHAR(100)  NOT NULL,
    telephone   VARCHAR(20)
);

CREATE TABLE PRODUIT (
    numProd             VARCHAR(20)   PRIMARY KEY,
    designation         VARCHAR(100)  NOT NULL,
    prixUnitaireActuel  INT           NOT NULL CHECK (prixUnitaireActuel >= 0),
    stockActuel         INT           NOT NULL DEFAULT 0 CHECK (stockActuel >= 0)
);

CREATE TABLE SERVICE (
    numServ     VARCHAR(20)   PRIMARY KEY,
    designation VARCHAR(100)  NOT NULL,
    prixActuel  INT           NOT NULL CHECK (prixActuel >= 0)
);

CREATE TABLE ENTREE (
    numEntree        VARCHAR(20)  PRIMARY KEY,
    numProd          VARCHAR(20)  NOT NULL REFERENCES PRODUIT(numProd),
    quantiteAjoutee  INT          NOT NULL CHECK (quantiteAjoutee > 0),
    dateEntree       DATE         NOT NULL DEFAULT CURRENT_DATE
);

CREATE TABLE ACHAT (
    numAchat     VARCHAR(20)  PRIMARY KEY,
    numProd      VARCHAR(20)  NOT NULL REFERENCES PRODUIT(numProd),
    numClient    VARCHAR(20)  NOT NULL REFERENCES CLIENT(numClient),
    nbrLitre     INT          NOT NULL CHECK (nbrLitre > 0),
    montantTotal INT          NOT NULL CHECK (montantTotal >= 0),
    dateAchat    DATE         NOT NULL DEFAULT CURRENT_DATE
);

CREATE TABLE ENTRETIEN (
    numEntr                 VARCHAR(20)  PRIMARY KEY,
    numServ                 VARCHAR(20)  NOT NULL REFERENCES SERVICE(numServ),
    numClient               VARCHAR(20)  NOT NULL REFERENCES CLIENT(numClient),
    Immatriculation_voiture VARCHAR(20)  NOT NULL,
    montantTotal            INT          NOT NULL CHECK (montantTotal >= 0),
    dateEntretien           DATE         NOT NULL DEFAULT CURRENT_DATE
);