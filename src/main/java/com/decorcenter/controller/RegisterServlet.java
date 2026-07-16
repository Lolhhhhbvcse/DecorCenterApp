package com.decorcenter.controller;

import com.decorcenter.dao.UsuarioDAO;
import com.decorcenter.factory.DAOFactory;
import com.decorcenter.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/registro"})
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/registro.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1. Capturamos el campo único del formulario
        String nombreCompleto = req.getParameter("nombre"); 
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmar = req.getParameter("confirmar");

        // --- LÓGICA DE DIVISIÓN DE NOMBRE ---
        String nombre = "";
        String apellido = "";
        
        if (nombreCompleto != null && !nombreCompleto.trim().isEmpty()) {
            String[] partes = nombreCompleto.trim().split(" ", 2); 
            nombre = partes[0]; 
            if (partes.length > 1) {
                apellido = partes[1]; 
            } else {
                apellido = "-"; 
            }
        }

        UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();

        // 2. Validaciones
        if (password == null || !password.equals(confirmar)) {
            req.setAttribute("error", "Las contraseñas no coinciden.");
            req.getRequestDispatcher("/WEB-INF/views/registro.jsp").forward(req, resp);
            return;
        }

        if (usuarioDAO.existeEmail(email)) {
            req.setAttribute("error", "Ya existe una cuenta con ese correo.");
            req.getRequestDispatcher("/WEB-INF/views/registro.jsp").forward(req, resp);
            return;
        }

        // 3. Crear el usuario en texto plano temporalmente
        Usuario usuario = new Usuario(nombre, apellido, email, password, "USUARIO");
        
        System.out.println("Intentando registrar cliente: " + nombre + " | " + apellido + " | " + email);
        
        boolean creado = usuarioDAO.registrar(usuario);

        if (creado) {
            req.setAttribute("mensaje", "Cuenta creada con éxito. Ahora puedes iniciar sesión.");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Ocurrió un error al registrar tu cuenta.");
            req.getRequestDispatcher("/WEB-INF/views/registro.jsp").forward(req, resp);
        }
    }
}
   