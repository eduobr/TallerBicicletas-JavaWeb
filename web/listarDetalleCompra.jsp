<%-- 
    Document   : listarDetalleCompra
    Created on : 07-jun-2019, 21:57:20
    Author     : Zeit
--%>
<%@page import="clases.Cl_Detalle_Compra"%>
<%@page import="clases.Cl_Compra"%>
<%@page import="DAO.DaoCompra"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalle de Compra</title>
        <%@include file="cabecera.jsp" %>
    </head>
    <body>
        <div class="container-fluid">
            <h1>Detalle de Compra!</h1>
            <%@include file="navbar.jsp" %>
            <script type="text/javascript">
                $(document).ready(function () {
                    $("#detalleCompra").DataTable();
                });
            </script>  
            <table id="detalleCompra" class="table table-striped table-dark">
                <thead>
                    <tr>
                        <td>Producto</td>
                        <td>Modelo</td>
                        <td>Precio</td>
                        <td>Cantidad</td>
                        <td>Proveedor</td>
                        <td>Foto</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listaDetalle}" var="detalle">
                        <tr>
                            <td>${detalle.nombre}</td>
                            <td>${detalle.modelo}</td>
                            <td>${detalle.precio}</td>
                            <td>${detalle.cantidad}</td>
                            <td>${detalle.proveedor}</td>
                            <td><img src="data:image/png;base64, ${detalle.imagen}" width="150" height="150"></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
