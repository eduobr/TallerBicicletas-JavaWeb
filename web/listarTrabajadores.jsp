<%-- 
    Document   : gerente
    Created on : 13-may-2019, 17:00:46
    Author     : Zeit
--%>

<%@page import="java.util.List"%>
<%@page import="clases.Cl_Trabajador"%>
<%@page import="DAO.DaoTrabajador"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="cabecera.jsp" %>
        <title>Listar Trabajadores</title>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="navbar.jsp"%>
            <%            DaoTrabajador daoTrabajador = new DaoTrabajador();
                List<Cl_Trabajador> trab = daoTrabajador.listarTrabajadores();
                request.setAttribute("listaTrabajador", trab);

            %>
            <script type="text/javascript">
                $(document).ready(function () {
                    $("#trabajadores").DataTable();
                });
            </script>
            <hr/>
            <form action="ServletTrabajador" method="post">
                <table id="trabajadores" class="table table-dark table-striped">
                    <thead>
                        <tr>
                            <td>Rut</td>
                            <td>Nombre</td>
                            <td>Cargo</td>
                            <td>Dirección</td>
                            <td>Correo</td>
                            <td>Fecha Ingreso</td>
                            <td>Sueldo</td>
                            <td>Estado</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listaTrabajador}" var="trabajador" > 
                            <tr>
                                <td>${trabajador.rut}</td>
                                <td>${trabajador.nombre}</td>
                                <td>${trabajador.cargo}</td>
                                <td>${trabajador.direccion}</td>
                                <td>${trabajador.correo}</td>
                                <td><fmt:formatDate value="${trabajador.fechaIngreso}" pattern="dd-MM-yyyy" /></td>
                                <td>${trabajador.sueldo}</td>
                                <td>${trabajador.estado}</td>
                                <td><input type="hidden" name="txtIdTrab" value="${trabajador.idUsuario}"></td>
                                <td><input type="button" name="btnAccion" class="btn btn-danger" value="Despedir"></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                ${mensaje}
            </form>
        </div>
    </body>
</html>
