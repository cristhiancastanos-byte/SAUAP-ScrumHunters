package com.sauap.vista;

import com.sauap.entidad.Profesor;
import com.sauap.servicio.ProfesorService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named(value = "profesorBean")
@RequestScoped
public class ProfesorBean implements Serializable {

    private Profesor profesor = new Profesor();
    private ProfesorService service = new ProfesorService();

    private String mensaje;
    private boolean exito; // Controla el color del panel final

    public ProfesorBean() {
    }

    public void registrar() {
        try {
            service.guardarProfesor(profesor);
            this.mensaje = "Profesor Registrado exitosamente";
            this.exito = true;
            this.profesor = new Profesor(); // Limpiamos el formulario
        } catch (Exception e) {
            this.mensaje = e.getMessage(); // Captura el texto de error del Service
            this.exito = false;
        }
    }

    // Getters y Setters obligatorios
    public Profesor getProfesor() { return profesor; }
    public void setProfesor(Profesor profesor) { this.profesor = profesor; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public boolean isExito() { return exito; }
    public void setExito(boolean exito) { this.exito = exito; }
}