
<%@ page contentType="UTF-8" import="java.util.*" import="org.example.models.*" %>

<%
List<Producto> productos = (List<Producto>) request.getAttribute("productos");
Optional<String> username = (Optional<String>) request.getAttribute("username");
    String mensajeRequest = (String) request.getAttribute("mensaje");
    String mensajeGlobal = (String) request.getServletContext().getAttribute("mensaje");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Listado de productos</title>
</head>
<body>
    <h1>Listado de productos</h1>

    <%if(username.isPresent()){%>
    <div>
        Hola <%=username.get()%>, bienvenido
        <p><a href="<%=request.getContextPath()%>/producto/form">Crear [+]</a></p>
    </div>
    <%}%>

    <table>
        <tr>
            <th>Id</th>
            <th>Nombre</th>
            <th>Tipo</th>
            <%if(username.isPresent()){%>
            <th>Precio</th>
            <th>Agregar</th>
            <th>Editar</th>
            <th>Eliminar</th>
            <%}%>
        </tr>
        <% for(Producto p: productos){%>
            <tr>
                <td><%=p.getId()%></td>
                <td><%=p.getNombre()%></td>
                <td><%=p.getCategoria().getNombre()%></td>
                <%if(username.isPresent()){%>
                <td><%=p.getPrecio()%></td>
                <td><a href="<%=request.getContextPath()%>/carro/agregar?id=<%=p.getId()%>">Agregar</a></td>
                <td><a href="<%=request.getContextPath()%>/producto/form?id=<%=p.getId()%>">Editar</a></td>
                <td><a onclick="return confirm('¿Estás seguro de eliminar este producto?')"
                        href="<%=request.getContextPath()%>/producto/eliminar?id=<%=p.getId()%>">Eliminar</a></td>
                <%}%>
            </tr>
        <%}%>
    </table>

    <p><%=mensajeGlobal%></p>
    <p><%=mensajeRequest%></p>

</body>
</html>