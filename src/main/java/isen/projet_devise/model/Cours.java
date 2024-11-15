package isen.projet_devise.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private double cours;
    private String ticker;

    // Constructeur sans arguments requis pour JPA
    public Cours() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getCours() {
        return cours;
    }

    public void setCours(double cours) {
        this.cours = cours;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
}
