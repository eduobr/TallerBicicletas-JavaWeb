<%-- 
    Document   : listarReparacionesCli
    Created on : 01-jun-2019, 17:54:56
    Author     : Zeit
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="clases.Cl_Reparacion"%>
<%@page import="DAO.DaoReparacion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Reparaciones</title>
        <%@include file="cabecera.jsp" %>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="navbar.jsp" %>
            <h1>Reparaciones Solicitadas</h1>
            <%  DaoReparacion daoReparacion = new DaoReparacion();
                List<Cl_Reparacion> listaReparacion = daoReparacion.obtenerReparacionesCli(usuario.getIdPersona());
                request.setAttribute("listaReparaciones", listaReparacion);
            %>
            <script type="text/javascript">
                $(document).ready(function () {
                    $("#reparacionesCli").DataTable();
                });
            </script>
            <table id="reparacionesCli" class="table table-striped table-dark">
                <thead>
                    <tr>
                        <td>Rut</td>

                        <td>Nombre</td>

                        <td>Descripcion</td>

                        <td>Fecha Pedido</td>

                        <td>Fecha Entrega</td>

                        <td>Estado</td>

                        <td>Foto</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listaReparaciones}" var="reparacion" > 
                        <tr>
                            <td>${reparacion.rut}</td>

                            <td>${reparacion.nombre}</td>

                            <td>${reparacion.descripcion}</td>

                            <td>${reparacion.fechaPedido}</td>

                            <td>${reparacion.fechaEntrega}</td>

                            <td>${reparacion.estado}</td>

                            <td><img src="data:image/png;base64, ${reparacion.imagen}" width="150" height="150"></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
</body>
</html>
