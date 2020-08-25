<%-- 
    Document   : login
    Created on : 18-may-2019, 10:15:42
    Author     : Zeit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <%@include file="cabecera.jsp" %>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="navbar.jsp"%>
            <h1>Iniciar Sesión</h1>
            <form action="ServletLogin" method="post">
                <table border="1">
                    <tbody>
                        <tr>
                            <td>Usuario</td>
                            <td><input type="text" name="txtUsuario" value=""></td>
                        </tr>
                        <tr>
                            <td>Contraseña</td>
                            <td><input type="text" name="txtPass" value=""></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="Ingresar"></td>
                        </tr>
                    </tbody>
                </table>
            </form>

            <%            if (request.getAttribute("mensaje") != null) {
                    out.println(request.getAttribute("mensaje"));
                }
                if (sesion.getAttribute("usuario") != null) {
                    out.println("El usuario esta logeado");
                    //ClUsuario usuario = (ClUsuario)sesion.getAttribute("usuario");
                    out.println(usuario.getUser().toString());
                }
            %>
        </div>
    </body>
</html>
