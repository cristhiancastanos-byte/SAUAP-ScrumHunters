package com.sauap.servicio;
import java.util.List;

import com.sauap.entidad.Asignacion;
import com.sauap.persistencia.AsignacionDAO;

import java.sql.Time;

public class AsignacionService {

    private final AsignacionDAO asignacionDAO = new AsignacionDAO();

    public void registrarAsignacion(Asignacion nuevaAsignacion) throws Exception {

        if (nuevaAsignacion.getHoraInicio().compareTo(nuevaAsignacion.getHoraFin()) >= 0) {
            throw new Exception("Error de captura: La hora de inicio debe ser anterior a la hora de fin");
        }

        int idProfesor = nuevaAsignacion.getProfesor().getId();
        String dia = nuevaAsignacion.getDia();
        Time inicio = nuevaAsignacion.getHoraInicio();
        Time fin = nuevaAsignacion.getHoraFin();

        long conteo = asignacionDAO.contarTraslapes(idProfesor, dia, inicio, fin);

        if (conteo > 0) {
            throw new Exception("Horario no disponible");

        }

        asignacionDAO.guardar(nuevaAsignacion);
    }
    public List<Asignacion> listarAsignaciones() {
        return asignacionDAO.obtenerAsignacionesOrdenadas();
    }
}