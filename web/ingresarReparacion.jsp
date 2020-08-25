<%-- 
    Document   : ingresarReparacion
    Created on : 26-may-2019, 1:31:01
    Author     : Zeit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="cabecera.jsp" %>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="navbar.jsp" %>
            <h1>Ingresar Reparacion</h1>
            <form action="ServletCliente" method="post"  enctype="multipart/form-data">
                <table class="table table-striped table-dark">
                    <tr>
                        <td>Descripcion</td>
                        <td><textarea class="form-control" name="txtDescripcion" rows="4"  minlength="10" maxlength="100" required pattern="[A-Za-z0-9]{5-100}"></textarea></td>
                    </tr>
                    <tr>
                        <td>Fecha Entrega</td>
                        <td><input type="text" class="form-control" name="txtFechaEntrega" required pattern="^([0-2][0-9]|3[0-1])(\/|-)(0[1-9]|1[0-2])\2(\d{4})$"></td>          
                    </tr>
                    <tr>
                        <td>Foto</td>
                        <td><input type="file" class="form-control" name="filFoto" required="" accept="image/x-png,image/gif,image/jpeg"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" name="btnAccion" class="btn btn-success" value="Ingresar Reparacion"></td>
                    </tr>
                </table>
            </form>
            ${mensaje}
        </div>
    </body>
</html>
