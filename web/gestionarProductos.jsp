<%-- 
    Document   : gestionarProductos
    Created on : 02-jun-2019, 17:58:34
    Author     : Zeit
--%>
<%@page import="clases.Cl_Compra"%>
<%@page import="DAO.DaoCompra"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestionar Productos</title>
        <%@include file="cabecera.jsp" %>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="navbar.jsp" %>
            <h1>Gestionar Productos!</h1>
            <%            DaoCompra daoCompra = new DaoCompra();
                List<Cl_Compra> listaCompras = daoCompra.obtenerCompras();
                request.setAttribute("listaCompras", listaCompras);
            %>
            <form action="ServletGestion" method="post">
                <table class="table table-striped table-dark">
                    <tr>
                        <td>Estado</td>
                        <td>Fecha de Compra</td>
                        <td>Total</td>
                    </tr>
                    <c:forEach items="${listaCompras}" var="compra">
                        <tr>
                            <td>${compra.estado}</td>
                            <td>${compra.fecha}</td>
                            <td>${compra.total}</td>
                            <td><input type="hidden" name="txtIdCompra" value="${compra.idCompra}"></td>
                            <td><input type="submit" name="btnAccion" class="btn btn-success" value="Comprar">
                                <input type="submit" name="btnAccion" class="btn btn-danger" value="Rechazar">
                                <input type="submit" name="btnAccion" class="btn btn-info" value="Ver Detalle"></td>
                        </tr>
                    </c:forEach>
                </table>
                ${mensaje}
            </form>
        </div>

    </body>
</html>
