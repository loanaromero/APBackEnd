
package com.portfolio.lr.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombreEd;
    private String descripEd;

    public education() {
    }

    public education(String nombreEd, String descripEd) {
        this.nombreEd = nombreEd;
        this.descripEd = descripEd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEd() {
        return nombreEd;
    }

    public void setNombreEd(String nombreEd) {
        this.nombreEd = nombreEd;
    }

    public String getDescripEd() {
        return descripEd;
    }

    public void setDescripEd(String descripEd) {
        this.descripEd = descripEd;
    }
    
}
