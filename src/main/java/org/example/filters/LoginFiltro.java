package org.example.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.services.LoginService;
import org.example.services.LoginServiceCookieImpl;
import org.example.services.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

@WebFilter("/carro/*")
public class LoginFiltro implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LoginService service = new LoginServiceSessionImpl();
        Optional< String > username = service.getUsername((HttpServletRequest) servletRequest);
        if(username.isPresent()){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuario no autorizado");
        }
    }
}
