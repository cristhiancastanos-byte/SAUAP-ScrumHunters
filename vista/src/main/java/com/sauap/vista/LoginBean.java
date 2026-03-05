package com.sauap.vista;

import com.sauap.entidad.Usuario;
import com.sauap.servicio.UsuarioService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String correo;
    private String contrasena;
    private UsuarioService usuarioService;

    public LoginBean() {

        this.usuarioService = new UsuarioService();
    }


    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }


    public String entrar() {
        Usuario usuario = usuarioService.validarLogin(correo, contrasena);

        if (usuario != null) {

            this.correo = null;
            this.contrasena = null;


            return "profesor.xhtml?faces-redirect=true";
        } else {

            this.correo = null;
            this.contrasena = null;


            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña incorrectos", null));

            return null;
        }
    }
}