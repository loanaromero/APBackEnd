
package com.portfolio.lr.Dto;

import javax.validation.constraints.NotBlank;


public class dtoProject {
    @NotBlank
    private String nombreP;
    @NotBlank
    private String descripP;
    @NotBlank
    private String imgP;
    @NotBlank
    private String linkP;

    public dtoProject() {
    }

    public dtoProject(String nombreP, String descripP, String imgP, String linkP) {
        this.nombreP = nombreP;
        this.descripP = descripP;
        this.imgP = imgP;
        this.linkP = linkP;
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
