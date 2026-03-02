package com.sauap.vista;

import com.sauap.entidad.UnidadAprendizaje;
import com.sauap.servicio.UnidadService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class UnidadBean implements Serializable {

    private UnidadService service = new UnidadService();
    private List<UnidadAprendizaje> listaUnidades;
    private UnidadAprendizaje nuevaUnidad;

    @PostConstruct
    public void init() {
        limpiarFormulario();
        consultarUnidades();
    }

    public void consultarUnidades() {
        listaUnidades = service.listarUnidades();
    }

    public void limpiarFormulario() {
        this.nuevaUnidad = new UnidadAprendizaje();
    }

    public void guardar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (nuevaUnidad.getHorasClase() == 0 && nuevaUnidad.getHorasTaller() == 0 && nuevaUnidad.getHorasLab() == 0) {
                context.addMessage("dialogs:msgGlobal",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe asignar al menos 1 hora en algún campo"));
                PrimeFaces.current().ajax().addCallbackParam("savedSuccess", false);
                return;
            }

            service.guardarUnidad(nuevaUnidad);
            consultarUnidades();

            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Materia registrada correctamente"));

            PrimeFaces.current().ajax().addCallbackParam("savedSuccess", true);
            limpiarFormulario();
        } catch (Exception e) {
            context.addMessage("dialogs:msgGlobal",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Error: " + e.getMessage()));
            PrimeFaces.current().ajax().addCallbackParam("savedSuccess", false);
        }
    }
    public List<UnidadAprendizaje> getListaUnidades() { return listaUnidades; }
    public UnidadAprendizaje getNuevaUnidad() { return nuevaUnidad; }
    public void setNuevaUnidad(UnidadAprendizaje nuevaUnidad) { this.nuevaUnidad = nuevaUnidad; }
}