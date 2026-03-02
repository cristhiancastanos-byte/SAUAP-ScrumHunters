package com.sauap.servicio;

import com.sauap.entidad.UnidadAprendizaje;
import com.sauap.persistencia.UnidadDAO;
import java.util.List;

public class UnidadService {
    private UnidadDAO unidadDAO = new UnidadDAO();

    public void guardarUnidad(UnidadAprendizaje unidad) throws Exception {
        if (unidad.getHorasClase() > 4 || unidad.getHorasTaller() > 4 || unidad.getHorasLab() > 4) {
            throw new Exception("Valor inválido (0-4)");
        }
        unidadDAO.guardar(unidad);
    }

    public List<UnidadAprendizaje> listarUnidades() {
        return unidadDAO.obtenerTodas();
    }
}