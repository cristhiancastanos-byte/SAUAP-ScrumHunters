package com.sauap.servicio;

import com.sauap.entidad.Profesor;
import com.sauap.persistencia.ProfesorDAO;

import java.util.List;
import java.util.regex.Pattern;

public class ProfesorService {

    private ProfesorDAO profesorDAO = new ProfesorDAO();
    private static final String RFC_REGEX = "^[A-ZÑ&]{4}\\d{6}[A-Z0-9]{3}$";

    public void guardarProfesor(Profesor profesor) throws Exception {
        if (profesor.getRfc() == null || profesor.getRfc().trim().isEmpty()) {
            throw new Exception("Error: El RFC es obligatorio.");
        }

        String rfcUpper = profesor.getRfc().toUpperCase().trim();


        if (!Pattern.matches(RFC_REGEX, rfcUpper)) {
            throw new Exception("Error: Formato de RFC inválido. Use 4 letras, 6 números y homoclave.");
        }


        if (profesorDAO.existeRFC(rfcUpper)) {
            throw new Exception("Error: El RFC ingresado ya pertenece a un profesor registrado.");
        }

        profesor.setRfc(rfcUpper);
        profesorDAO.guardar(profesor);
    }

    public List<Profesor> listarProfesores() {
        return profesorDAO.obtenerTodos();
    }

    public Profesor buscarPorId(int id) {
        return profesorDAO.buscarPorId(id);
    }
}