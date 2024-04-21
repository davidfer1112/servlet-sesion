# Documentación Servlet Session

## Descripción
Este proyecto demuestra el manejo de sesiones en aplicaciones web Java utilizando Servlets. Se enfoca en la gestión de un carrito de compras, permitiendo agregar, actualizar y eliminar productos, así como iniciar y cerrar sesión.

## Requisitos

Para ejecutar y desarrollar este proyecto necesitas tener instalado:

- JDK 17: Requerido para compilar y ejecutar el proyecto Java.
- Apache Maven: Utilizado para gestionar las dependencias del proyecto y facilitar el proceso de construcción.
- Apache Tomcat: Servidor web utilizado para desplegar la aplicación.

## Configuración del Proyecto
La configuración del proyecto se realiza a través del archivo `pom.xml`, el cual contiene las dependencias necesarias para el proyecto y la configuración específica para el plugin de Apache Tomcat, permitiendo el despliegue de la aplicación directamente desde Maven.

### Dependencias
El proyecto depende de la API de Jakarta EE y de la biblioteca Jackson para el manejo de JSON. Estas dependencias se definen en el archivo `pom.xml` como se muestra a continuación:

```xml
<dependencies>
    <dependency>
        <groupId>jakarta.platform</groupId>
        <artifactId>jakarta.jakartaee-api</artifactId>
        <version>9.1.0</version>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.12.3</version>
    </dependency>
</dependencies>
```

## Configuración de Maven para Tomcat
Para facilitar el despliegue de la aplicación en un servidor Tomcat directamente desde Maven, se incluye la configuración del plugin tomcat7-maven-plugin en el pom.xml. Esta configuración especifica la URL del servidor Tomcat y las credenciales de acceso.

```xml
<plugin>
    <groupId>org.apache.tomcat.maven</groupId>
    <artifactId>tomcat7-maven-plugin</artifactId>
    <version>2.2</version>
    <configuration>
        <url>http://localhost:8080/manager/text</url>
        <username>admin</username>
        <password>123</password>
    </configuration>
</plugin>
```

## Estructura del Proyecto
El proyecto se organiza en varios paquetes y archivos clave, que incluyen:

- Servlets: Clases Java que extienden HttpServlet y manejan solicitudes HTTP GET o POST relacionadas con el carrito de compras y la sesión de usuario.
- Servicios: Interfaces y clases que simulan la lógica de negocio y el acceso a datos para productos y autenticación de usuarios.
- Modelos: Clases que representan entidades o modelos de datos como productos, carrito de compras e ítems del carrito.
- Vistas: Archivos JSP y HTML para la presentación de datos y formularios de interacción con el usuario.

### Servlets Principales

#### ProductosServlet
Maneja la visualización de productos disponibles y permite a los usuarios autenticados agregar productos al carrito de compras.

```java
@WebServlet({"/productos.html", "/productos"})
public class ProductosServlet extends HttpServlet{
    // Implementación del método doGet
}
```

#### AgregarCarroServlet
Permite agregar un producto al carrito de compras y redirige al usuario a la vista del carrito.

```java
@WebServlet("/agregar-carro")
public class AgregarCarroServlet extends HttpServlet{
    // Implementación del método doGet
}
```

#### ActualizarCarroServlet
Permite actualizar las cantidades de los productos en el carrito o eliminarlos.

```java
@WebServlet("/actualizar-carro")
public class ActualizarCarroServlet extends HttpServlet{
    // Implementación del método doPost
}
```

### Vistas
El proyecto incluye varias páginas JSP y HTML que sirven como interfaz de usuario para interactuar con los Servlets, como:

- index.html: Página principal con enlaces a las diferentes funcionalidades.
- login.jsp: Formulario de inicio de sesión.
- ver-carro.jsp: Vista para mostrar el carrito de compras.

## Ejecución del Proyecto
Para ejecutar el proyecto, utiliza el siguiente comando de Maven, el cual compilará el proyecto y lo desplegará en el servidor Tomcat configurado:

    mvn tomcat7:deploy
