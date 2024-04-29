package org.example.util;

import javax.naming.InitialContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.naming.InitialContext;

public class ConexionBaseDatosDs {

    public static Connection getConexion() throws SQLException, NamingException{

        Context initContext = null;
        initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:comp/env");
        DataSource ds = (DataSource) envContext.lookup("jdbc/mysqlDB");
        return ds.getConnection();
    }
}
