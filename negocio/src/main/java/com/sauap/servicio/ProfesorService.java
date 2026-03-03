package com.sauap.servicio;

import com.sauap.entidad.Profesor;
import com.sauap.persistencia.ProfesorDAO;
import java.util.regex.Pattern;

public class ProfesorService {

    private ProfesorDAO profesorDAO = new ProfesorDAO();
    private static final String RFC_REGEX = "^[A-ZÑ&]{4}\\d{6}[A-Z0-9]{3}$";

    public void guardarProfesor(Profesor profesor) throws Exception {
        if (profesor.getRfc() == null || profesor.getRfc().trim().isEmpty()) {
            throw new Exception("Error: El RFC es obligatorio.");
        }

        String rfcUpper = profesor.getRfc().toUpperCase().trim();

        // 1. Validar el formato exacto del RFC
        if (!Pattern.matches(RFC_REGEX, rfcUpper)) {
            throw new Exception("Error: Formato de RFC inválido. Use 4 letras, 6 números y homoclave.");
        }

        // 2. Validar que no exista un profesor con ese mismo RFC en la BD
        if (profesorDAO.existeRFC(rfcUpper)) {
            throw new Exception("Error: El RFC ingresado ya pertenece a un profesor registrado.");
        }

        // Si pasa todas las pruebas, lo guardamos
        profesor.setRfc(rfcUpper);
        profesorDAO.guardar(profesor);
    }
}