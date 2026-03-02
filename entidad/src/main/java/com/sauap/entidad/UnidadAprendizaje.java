package com.sauap.entidad;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;

@Entity
@Table(name = "Unidad_Aprendizaje")
public class UnidadAprendizaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unidad")
    private Long id;

    @NotBlank(message = "Nombre obligatorio")
    @Size(max = 50, message = "Máximo 50 caracteres")
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Min(value = 0, message = "Valor inválido (0-4)")
    @Max(value = 4, message = "Valor inválido (0-4)")
    @Column(name = "h_clase", nullable = false)
    private Integer horasClase = 0;

    @Min(value = 0, message = "Valor inválido (0-4)")
    @Max(value = 4, message = "Valor inválido (0-4)")
    @Column(name = "h_taller", nullable = false)
    private Integer horasTaller = 0;

    @Min(value = 0, message = "Valor inválido (0-4)")
    @Max(value = 4, message = "Valor inválido (0-4)")
    @Column(name = "h_lab", nullable = false)
    private Integer horasLab = 0;

    public UnidadAprendizaje() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Integer getHorasClase() { return horasClase; }
    public void setHorasClase(Integer horasClase) { this.horasClase = horasClase; }
    public Integer getHorasTaller() { return horasTaller; }
    public void setHorasTaller(Integer horasTaller) { this.horasTaller = horasTaller; }
    public Integer getHorasLab() { return horasLab; }
    public void setHorasLab(Integer horasLab) { this.horasLab = horasLab; }
}