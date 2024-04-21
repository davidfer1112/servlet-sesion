package org.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.models.Producto;
import org.example.services.*;

import java.io.IOException;
import java.util.List;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos"})
public class ProductosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImpl();
        List<Producto> productos = service.getAll();

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {


            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <title>Listado de productos</title>");
            out.println("    </head>");
            out.println("    <body>");
            out.println("        <h1>Listado de productos</h1>");
            if (usernameOptional.isPresent()){
                out.println("        <h2 style= 'color:blue;'>Bienvenido " + usernameOptional.get() + "</h2>");
            }
            out.println("        <table>");
            out.println("            <tr>");
            out.println("                <th>Id</th>");
            out.println("                <th>Nombre</th>");
            out.println("                <th>Tipo</th>");
            if (usernameOptional.isPresent() && usernameOptional.get().equals("admin")) {
                out.println("                <th>Precio</th>");
            }
            out.println("            </tr>");
            productos.forEach(p -> {
                out.println("            <tr>");
                out.println("                <td>" + p.getId() + "</td>");
                out.println("                <td>" + p.getNombre() + "</td>");
                out.println("                <td>" + p.getTipo() + "</td>");
                if (usernameOptional.isPresent() && usernameOptional.get().equals("admin")) {
                    out.println("                <td>" + p.getPrecio() + "</td>");
                }
                out.println("            </tr>");
            });
            out.println("        </table>");

            out.println("    </body>");
            out.println("</html>");

        }

    }
}
