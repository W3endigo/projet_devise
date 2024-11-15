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

CREATE TABLE ticker(
        name Varchar (5) NOT NULL
	,CONSTRAINT Devise_PK PRIMARY KEY (name)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: cours
#------------------------------------------------------------

CREATE TABLE rate(
        id    Int  Auto_increment  NOT NULL ,
        date  Datetime NOT NULL ,
        value Double NOT NULL ,
        ticker   Varchar (5) NOT NULL
	,CONSTRAINT cours_PK PRIMARY KEY (id)

	,CONSTRAINT cours_Devise_FK FOREIGN KEY (ticker) REFERENCES ticker(name)
)ENGINE=InnoDB;
