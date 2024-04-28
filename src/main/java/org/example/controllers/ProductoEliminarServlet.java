package org.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.models.Producto;
import org.example.services.ProductoService;
import org.example.services.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/producto/eliminar")
public class ProductoEliminarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImpl(conn);

        long id;
        try{
            id = Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0L;
        }
        if (id > 0){
            Optional<Producto> o = service.porId(id);
            if (o.isPresent()){
                service.eliminar(id);
                resp.sendRedirect(req.getContextPath() + "/productos");
            }else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Producto no encontrado");
            }
        }else{
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Falta el parametro id");
        }

    }
}
