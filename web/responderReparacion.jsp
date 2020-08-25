<%-- 
    Document   : responderReparacion
    Created on : 04-jul-2019, 23:32:33
    Author     : Zeit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <h1>Responder Reparación</h1>
            <form action="ServletTrabajador" method="post">
                <table class="table table-striped table-dark">
                    <tr>
                        <td>Respuesta</td>
                        <td>
                            <select class="form-control" name="cboRespuestaRep">
                                <option value="Aceptado">Aceptar</option>
                                <option value="Rechazado">Rechazar</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Descripción</td>
                        <td>
                            <textarea rows="4" name="txtDescripcionRep" class="form-control" required pattern=""></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td><input type="hidden" name="txtIdReparacion" value="${idReparacion}"></td>
                        <td>
                            <input type="submit" name="btnAccion" class="btn btn-warning" value="Responder"></textarea>
                        </td>
                    </tr>
                </table>
            </form>
            ${mensaje}
        </div>
    </body>
</html>
