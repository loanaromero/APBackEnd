
package com.portfolio.lr.security.Dto;

import javax.validation.constraints.NotBlank;


public class LoginUser {
    @NotBlank
    private String NombreUsuario;
    @NotBlank
    private String password;

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
