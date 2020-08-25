<%-- 
    Document   : listarReparaciones
    Created on : 27-may-2019, 2:09:46
    Author     : Zeit
--%>

<%@page import="clases.Cl_Reparacion"%>
<%@page import="DAO.DaoReparacion"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reparaciones</title>
        <%@include file="cabecera.jsp" %>
    </head>
    <body>
        <div class="container-fluid">
            <%  DaoReparacion daoReparacion = new DaoReparacion();
                List<Cl_Reparacion> listaReparacion = daoReparacion.obtenerReparaciones();
                request.setAttribute("listaReparaciones", listaReparacion);
            %>
            <!--<script type="text/javascript">
                $(document).ready(function () {
                    $("#reparacionesTec").DataTable();
                });
            </script>-->
            <%@include file="navbar.jsp" %>
            <h1>Reparaciones</h1>
            <table id="reparacionesTec" class="table table-striped table-dark">
                <thead>
                    <tr>
                        <td>Rut</td>
                        <td>Nombre</td>
                        <td>Descripcion</td>
                        <td>Fecha Pedido</td>
                        <td>Fecha Entrega</td>
                        <td>Estado</td>
                    </tr>
                </thead>
                <tbody>
                <form action="ServletTrabajador" method="post">
                    <c:forEach items="${listaReparaciones}" var="reparacion" > 
                        <tr>
                            <td>${reparacion.rut}</td>
                            <td>${reparacion.nombre}</td>
                            <td>${reparacion.descripcion}</td>
                            <td>${reparacion.fechaPedido}</td>
                            <td>${reparacion.fechaEntrega}</td>
                            <td>${reparacion.estado}</td>
                            <td><img src="data:image/png;base64, ${reparacion.imagen}" width="150" height="150"></td>
                            <td><input type="hidden" name="txtIdReparacion" value="${reparacion.idReparacion}"></td>
                            <td><input type="submit" name="btnAccion" class="btn btn-warning" value="Responder Solicitud"></td>
                        </tr>
                    </c:forEach>
                </form>
                </tbody>
            </table>
            ${mensaje}
        </div>
    </body>
</html>
