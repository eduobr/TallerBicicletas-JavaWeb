<%-- 
    Document   : registro
    Created on : 01-jun-2019, 12:34:16
    Author     : Zeit
--%>

<%@page import="clases.Cl_Comuna"%>
<%@page import="DAO.DaoPersona"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
        <%@include file="cabecera.jsp" %>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="navbar.jsp" %>
            <%            DaoPersona daoPersona = new DaoPersona();
                List<Cl_Comuna> listaComunas = daoPersona.obtenerComunas();
                request.setAttribute("listaComunas", listaComunas);
            %>
            <h1>Registrarse!</h1>
            <form action="ServletCliente" method="post">
                <table class="table table-striped table-dark">
                    <tbody>
                        <tr>
                            <td>Rut</td>
                            <td><input type="text" name="txtRut" class="form-control" value=""></td>
                        </tr>
                        <tr>
                            <td>Nombre</td>
                            <td><input type="text" name="txtNombre" class="form-control" value=""></td>
                        </tr>
                        <tr>
                            <td>Apellido</td>
                            <td><input type="text" name="txtApellido" class="form-control" value=""></td>
                        </tr>
                        <tr>
                            <td>Edad</td>
                            <td><input type="text" name="txtEdad" class="form-control" value=""></td>
                        </tr>
                        <tr>
                            <td>Direccion</td>
                            <td><input type="text" name="txtDireccion" class="form-control" value=""></td>
                        </tr>
                        <tr>
                            <td>Correo</td>
                            <td><input type="email" name="txtCorreo" class="form-control" value=""></td>
                        </tr>
                        <tr>
                            <td>Comuna</td>
                            <td>
                                <select name="cboComuna" class="form-control">
                                    <c:forEach items="${listaComunas}" var="comuna" > 
                                        <option value="${comuna.idComuna}">${comuna.comuna}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                    <td>Usuario</td>
                    <td><input type="text" name="txtUsuario" class="form-control" value=""></td>
                    </tr>
                    <tr>
                        <td>Contraseña</td>
                        <td><input type="password" name="txtPass" class="form-control" value=""></td>
                    </tr>
                    <tr>
                        <td><input type="reset" value="Limpiar" class="btn btn-primary btn-block"></td>
                        <td><input type="submit" name="btnAccion" class="btn btn-success btn-block" value="Registrarse"></td>
                    </tr>
                    </tbody>
                </table>
            </form>
            ${mensaje}
        </div>
    </body>
</html>
