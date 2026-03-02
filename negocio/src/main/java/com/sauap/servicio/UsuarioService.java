package com.sauap.servicio;

import com.sauap.entidad.Usuario;
import com.sauap.persistencia.UsuarioDAO;

public class UsuarioService {

    private UsuarioDAO usuarioDAO;

    public UsuarioService() {

        this.usuarioDAO = new UsuarioDAO();
    }
    public Usuario validarLogin(String correo, String contrasenaIngresada) {

        Usuario usuarioEnBD = usuarioDAO.buscarPorUsername(correo);

        if (usuarioEnBD != null) {
            String contrasenaBD = usuarioEnBD.getPassword();


            if (contrasenaBD.equals(contrasenaIngresada)) {
                return usuarioEnBD;
            }
        }
        return null;
    }
}