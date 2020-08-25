<%-- 
    Document   : navbar
    Created on : 18-may-2019, 13:42:14
    Author     : Zeit
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="clases.Cl_Producto"%>
<%@page import="clases.Cl_Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row">
    <div class="col-8">
        <img src="img/banner.jpg" width="900" height="200" alt="banner"/>
    </div>
    <div class="col-4">
        <div id="login" class="mt-4" style="display:none">
            <form action="ServletLogin" method="post">
                <table>
                    <tbody>
                        <tr>
                            <td>Usuario</td>
                            <td><input type="text" class="form-control" name="txtUsuario" value="" required=""></td>
                        </tr>
                        <tr>
                            <td>Contraseña</td>
                            <td><input type="password"  class="form-control" name="txtPass" value="" required=""></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" class="btn btn-primary btn-block" value="Ingresar"></td>
                        </tr>
                    </tbody>
                </table>
            </form>
            
        </div>
    </div>
</div>
<nav class="navbar navbar-dark bg-dark navbar-expand-lg">
    <a class="navbar-brand" href="#">Menu</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav mr-auto">
            <%
                HttpSession sesion = request.getSession();

                Cl_Usuario usuario = (Cl_Usuario) sesion.getAttribute("usuario");

            %>
            <% if (sesion.getAttribute("usuario") != null) {%>

            <!--HttpSession sesion = request.getSession();-->
            <%if (usuario.getIdTipoUsuario() == 1) {%>
            <!--MENU GERENTE-->
            <li class="nav-item">
                <a class="nav-link" href="ingresarTrabajador.jsp">Agregar Trabajadores</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="listarTrabajadores.jsp">Listar Trabajadores</a>
            </li>
            <%}
                if (usuario.getIdTipoUsuario() == 7) {
            %>
            <!--MENU CLIENTE-->
            <li class="nav-item ">
                <a class="nav-link" href="listarProductos.jsp">Ver Productos</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Arriendo
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="ingresarArriendo.jsp">Solicitar Arriendo</a>
                    <a class="dropdown-item" href="listarArriendos.jsp">Listar Arriendos</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Reparaciones
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="ingresarReparacion.jsp">Solicitar Reparacion</a>
                    <a class="dropdown-item" href="listarReparacionesCli.jsp">Listar Reparaciones</a>
                </div>
            </li>
            <li class="nav-item ">
                <a class="nav-link" href="carrito-compras.jsp">Carrito de Compras</a>
            </li>
            <% }
                if (usuario.getIdTipoUsuario() == 4) {%>
            <!--MENU TÉCNICO-->
            <li class="nav-item ">
                <a class="nav-link" href="listarReparaciones.jsp">Ver Reparaciones</a>
            </li>
            <li class="nav-item ">
                <a class="nav-link" href="armarBicicleta.jsp">Armar Bicicleta</a>
            </li>
            <%}
                if (usuario.getIdTipoUsuario() == 2) {%>
            <!--Supervisor-->
            <li class="nav-item ">
                <a class="nav-link" href="gestionarProductos.jsp">Gestionar Productos Comprados</a>
            </li>
            <li class="nav-item ">
                <a class="nav-link" href="gestionarBicicletasArmadas.jsp">Gestionar Bicicltas Armadas</a>
            </li>
            <li class="nav-item ">
                <a class="nav-link" href="listarProductosEnEspera.jsp">Poner en Venta</a>
            </li>
            <%}%>
        </ul>
        <ul class="navbar-nav my-2 my-lg-0">
            <li class="nav-item">
                <a class="nav-link" href="cerrar-sesion.jsp">Cerrar Sesión</a>
            </li>
        </ul>

        <%} else {
        %>
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="listarProductos.jsp">Ver Productos <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item" style="margin-left: 850px;">
                <input type="button" class="btn btn-primary" id="btnIniciarSesion" value="Iniciar Sesión">
            </li>
            <li class="nav-item">
                <a class="btn btn-success" href="registro.jsp">Registrarse</a>
            </li>
        </ul>  
        <%}%>
    </div>
</nav>

