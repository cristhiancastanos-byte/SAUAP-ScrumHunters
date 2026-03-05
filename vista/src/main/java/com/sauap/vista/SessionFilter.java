package com.sauap.vista;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class SessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String loginURI = req.getContextPath() + "/login.xhtml";

        boolean estaLogueado = (session != null && session.getAttribute("usuarioLogueado") != null);

        boolean esPeticionLogin = req.getRequestURI().equals(loginURI);
        boolean esPeticionRecurso = req.getRequestURI().startsWith(req.getContextPath() + "/jakarta.faces.resource/");

        if (estaLogueado || esPeticionLogin || esPeticionRecurso) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(loginURI);
        }
    }
}