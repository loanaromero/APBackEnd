
package com.portfolio.lr.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    private String nombreP;
    @NotNull
    private String descripP;
    private String imgP;
    private String linkP;

    public project() {
    }

    public project(String nombreP, String descripP, String imgP, String linkP) {
        this.nombreP = nombreP;
        this.descripP = descripP;
        this.imgP = imgP;
        this.linkP = linkP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getDescripP() {
        return descripP;
    }

    public void setDescripP(String descripP) {
        this.descripP = descripP;
    }

    public String getImgP() {
        return imgP;
    }

    public void setImgP(String imgP) {
        this.imgP = imgP;
    }

    public String getLinkP() {
        return linkP;
    }

    public void setLinkP(String linkP) {
        this.linkP = linkP;
    }
    
}
