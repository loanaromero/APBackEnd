
package com.portfolio.lr.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String nombreE;
    private String descripE;

    public experience() {
    }

    public experience(String nombreE, String descripE) {
        this.nombreE = nombreE;
        this.descripE = descripE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreE() {
        return nombreE;
    }

    public void setNombreE(String nombreE) {
        this.nombreE = nombreE;
    }

    public String getDescripE() {
        return descripE;
    }

    public void setDescripE(String descripE) {
        this.descripE = descripE;
    }
    
}
