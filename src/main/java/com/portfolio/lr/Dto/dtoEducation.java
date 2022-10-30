
package com.portfolio.lr.Dto;

import javax.validation.constraints.NotBlank;


public class dtoEducation {
    @NotBlank
    private String nombreEd;
    @NotBlank
    private String descripEd;

    public dtoEducation() {
    }

    public dtoEducation(String nombreEd, String descripEd) {
        this.nombreEd = nombreEd;
        this.descripEd = descripEd;
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
