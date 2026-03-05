package com.sauap.vista;

import com.sauap.entidad.Asignacion;
import com.sauap.entidad.Profesor;
import com.sauap.entidad.UnidadAprendizaje;
import com.sauap.servicio.AsignacionService;
import com.sauap.servicio.ProfesorService;
import com.sauap.servicio.UnidadService;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.sql.Time;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Named("asignacionBean")
@ViewScoped
public class AsignacionBean implements Serializable {

    private static final long serialVersionUID = 1L;


    private final ProfesorService profesorService = new ProfesorService();
    private final UnidadService unidadService = new UnidadService();
    private final AsignacionService asignacionService = new AsignacionService();

    private List<Profesor> listaProfesores;
    private List<UnidadAprendizaje> listaUnidades;
    private List<String> listaDias;

    private Integer idProfesorSeleccionado;
    private Integer idUnidadSeleccionada;
    private String diaSeleccionado;

    private Date horaInicio;
    private Date horaFin;

    @PostConstruct
    public void init() {
        listaDias = Arrays.asList("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado");

        listaProfesores = profesorService.listarProfesores();
        listaUnidades = unidadService.listarUnidades();
    }

    public void guardar() {
        FacesContext fc = FacesContext.getCurrentInstance();

        try {
            if (idProfesorSeleccionado == null || idUnidadSeleccionada == null || diaSeleccionado == null
                    || horaInicio == null || horaFin == null) {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error", "Horario inválido / Campo obligatorio"));
                return;
            }

            Profesor profesor = profesorService.buscarPorId(idProfesorSeleccionado);
            UnidadAprendizaje unidad = unidadService.buscarPorId(idUnidadSeleccionada);

            if (profesor == null || unidad == null) {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error", "Horario inválido / Campo obligatorio"));
                return;
            }

            Time inicio = new Time(horaInicio.getTime());
            Time fin = new Time(horaFin.getTime());

            Asignacion a = new Asignacion();
            a.setProfesor(profesor);
            a.setUnidad(unidad);
            a.setDia(diaSeleccionado);
            a.setHoraInicio(inicio);
            a.setHoraFin(fin);

            asignacionService.registrarAsignacion(a);

            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Éxito", "Asignación guardada correctamente"));

            limpiar();

        } catch (Exception e) {
            String msg = e.getMessage();
            if (msg == null || msg.trim().isEmpty()) msg = "Horario no disponible";
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", msg));
        }
    }

    public void limpiar() {
        idProfesorSeleccionado = null;
        idUnidadSeleccionada = null;
        diaSeleccionado = null;
        horaInicio = null;
        horaFin = null;
    }


    public List<Profesor> getListaProfesores() { return listaProfesores; }
    public List<UnidadAprendizaje> getListaUnidades() { return listaUnidades; }
    public List<String> getListaDias() { return listaDias; }

    public Integer getIdProfesorSeleccionado() { return idProfesorSeleccionado; }
    public void setIdProfesorSeleccionado(Integer idProfesorSeleccionado) { this.idProfesorSeleccionado = idProfesorSeleccionado; }

    public Integer getIdUnidadSeleccionada() { return idUnidadSeleccionada; }
    public void setIdUnidadSeleccionada(Integer idUnidadSeleccionada) { this.idUnidadSeleccionada = idUnidadSeleccionada; }

    public String getDiaSeleccionado() { return diaSeleccionado; }
    public void setDiaSeleccionado(String diaSeleccionado) { this.diaSeleccionado = diaSeleccionado; }

    public Date getHoraInicio() { return horaInicio; }
    public void setHoraInicio(Date horaInicio) { this.horaInicio = horaInicio; }

    public Date getHoraFin() { return horaFin; }
    public void setHoraFin(Date horaFin) { this.horaFin = horaFin; }
}