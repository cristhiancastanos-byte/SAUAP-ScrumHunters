package com.sauap.entidad;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Time;
@Entity
@Table(name = "Asignacion")
public class Asignacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignacion")
    private Integer idAsignacion;

    // --- Relación con Profesor ---
    @ManyToOne
    @JoinColumn(name = "id_profesor", nullable = false)
    private Profesor profesor;

    // --- Relación con Unidad de Aprendizaje ---

    @ManyToOne
    @JoinColumn(name = "id_unidad", referencedColumnName = "id_unidad", nullable = false)
    private UnidadAprendizaje unidad;


    @Column(name = "dia", length = 15, nullable = false)
    private String dia;

    @Column(name = "hora_inicio", nullable = false)
    private Time horaInicio;

    @Column(name = "hora_fin", nullable = false)
    private Time horaFin;


    public Asignacion() {
    }


    public Asignacion(Profesor profesor, UnidadAprendizaje unidad, String dia, Time horaInicio, Time horaFin) {
        this.profesor = profesor;
        this.unidad = unidad;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getIdAsignacion() { return idAsignacion; }
    public void setIdAsignacion(Integer idAsignacion) { this.idAsignacion = idAsignacion; }

    public Profesor getProfesor() { return profesor; }
    public void setProfesor(Profesor profesor) { this.profesor = profesor; }

    public UnidadAprendizaje getUnidad() { return unidad; }
    public void setUnidad(UnidadAprendizaje unidad) { this.unidad = unidad; }

    public String getDia() { return dia; }
    public void setDia(String dia) { this.dia = dia; }

    public Time getHoraInicio() { return horaInicio; }
    public void setHoraInicio(Time horaInicio) { this.horaInicio = horaInicio; }

    public Time getHoraFin() { return horaFin; }
    public void setHoraFin(Time horaFin) { this.horaFin = horaFin; }
}