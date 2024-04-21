package org.example.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.example.models.Carro;

@WebListener
public class AplicacionListener  implements ServletContextListener,
            ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("Aplicación iniciada");
        servletContext = sce.getServletContext();
        servletContext.setAttribute("mensaje", "Hola desde el contexto");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("Aplicación destruida");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("Petición destruida");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("Petición iniciada");
        sre.getServletContext().setAttribute("mensaje", "Hola desde la petición");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("Sesión creada HTTP");
        Carro carro = new Carro();
        HttpSession session = se.getSession();
        session.setAttribute("carro", carro);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("Sesión destruida HTTP");
    }

}
