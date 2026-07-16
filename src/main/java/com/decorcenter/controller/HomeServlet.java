package com.decorcenter.controller;

import com.decorcenter.factory.DAOFactory;
import com.decorcenter.model.Categoria;
import com.decorcenter.model.Producto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "HomeServlet", urlPatterns = {"/home", "/"})
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String categoriaParam = req.getParameter("categoria");
        String buscar = req.getParameter("buscar");

        // Lista principal (catálogo) según filtro de búsqueda o categoría
        List<Producto> productos;
        if (buscar != null && !buscar.trim().isEmpty()) {
            productos = DAOFactory.getProductoDAO().buscarPorNombre(buscar.trim());
        } else if (categoriaParam != null && !categoriaParam.isEmpty()) {
            productos = DAOFactory.getProductoDAO().listarPorCategoria(Integer.parseInt(categoriaParam));
        } else {
            productos = DAOFactory.getProductoDAO().listarTodos();
        }

        // Lista de "favoritos / más vendidos": siempre todos los productos, pero en ORDEN INVERSO
        // Así las imágenes (y el orden de productos) son DISTINTAS a las del catálogo
        List<Producto> todos = DAOFactory.getProductoDAO().listarTodos();
       // Lista de "favoritos": tomar solo los productos del 15 al 20 en orden natural
List<Producto> favoritos = new ArrayList<>();
for (Producto p : todos) {
    if (p.getId() >= 15 && p.getId() <= 20) {
        favoritos.add(p);
    }
}
        List<Categoria> categorias = DAOFactory.getCategoriaDAO().listarTodas();

        req.setAttribute("productos", productos);
        req.setAttribute("favoritos", favoritos);   // <-- lista distinta (orden invertido)
        req.setAttribute("categorias", categorias);
        req.setAttribute("categoriaSeleccionada", categoriaParam);
        req.setAttribute("buscar", buscar);

        req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
    }
}
