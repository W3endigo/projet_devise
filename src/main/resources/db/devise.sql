#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------

#------------------------------------------------------------
# Base de donnees: exchange_rates_db
#------------------------------------------------------------
CREATE DATABASE exchange_rates_db;

USE exchange_rates_db;

#------------------------------------------------------------
# Table: devise
#------------------------------------------------------------

CREATE TABLE devise(
        nom Varchar (5) NOT NULL
	,CONSTRAINT Devise_PK PRIMARY KEY (nom)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: cours
#------------------------------------------------------------

CREATE TABLE cours(
        id    Int  Auto_increment  NOT NULL ,
        date  Datetime NOT NULL ,
        cours Double NOT NULL ,
        nom   Varchar (5) NOT NULL
	,CONSTRAINT cours_PK PRIMARY KEY (id)

	,CONSTRAINT cours_Devise_FK FOREIGN KEY (nom) REFERENCES devise(nom)
)ENGINE=InnoDB;
