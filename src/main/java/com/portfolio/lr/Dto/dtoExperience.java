
package com.portfolio.lr.Dto;

import javax.validation.constraints.NotBlank;


public class dtoExperience {
    @NotBlank
    private String nombreE;
    @NotBlank
    private String descripE;

    public dtoExperience() {
    }

    public dtoExperience(String nombreE, String descripE) {
        this.nombreE = nombreE;
        this.descripE = descripE;
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
