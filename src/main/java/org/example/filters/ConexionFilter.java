package org.example.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.example.services.ServiceJdbcException;
import org.example.util.ConexionBaseDatos;
import org.example.util.ConexionBaseDatosDs;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try (Connection conn = ConexionBaseDatosDs.getConexion()) {

            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }

            try {
                request.setAttribute("conn", conn);
                chain.doFilter(request, response);
                conn.commit();
            } catch (SQLException | ServiceJdbcException e) {
                conn.rollback();
                ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException | NamingException throwables) {
            throwables.printStackTrace();
        }
    }
}
