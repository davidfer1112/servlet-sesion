package org.example.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.example.services.ServiceJdbcException;
import org.example.util.ConexionBaseDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Connection conn = null;
        try {
            conn = ConexionBaseDatos.getConexion();

            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }

            servletRequest.setAttribute("conn", conn);
            filterChain.doFilter(servletRequest, servletResponse);
            conn.commit();
        } catch (SQLException | ServiceJdbcException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackException) {
                    // Manejar la excepción de rollback, si es necesario
                    rollbackException.printStackTrace();
                }
            }
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            throw new ServletException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException closeException) {
                    // Manejar la excepción de cierre, si es necesario
                    closeException.printStackTrace();
                }
            }
        }
    }
}

