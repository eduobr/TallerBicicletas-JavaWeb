<%-- 
    Document   : listarDespacho
    Created on : 27-may-2019, 4:23:25
    Author     : Zeit
--%>

<%@page import="clases.Cl_Despacho"%>
<%@page import="java.util.List"%>
<%@page import="DAO.DaoDespacho"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Despachos</title>
        <%@include file="cabecera.jsp" %>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="navbar.jsp" %>
            <h1>Traza Despacho</h1>
            <%            DaoDespacho daoDespacho = new DaoDespacho();
                List<Cl_Despacho> listaDespachos = daoDespacho.obtenerTrazaDespacho(usuario.getIdPersona());
                request.setAttribute("listaDespachos", listaDespachos);
            %>
            <script type="text/javascript">
                $(document).ready(function () {
                    $("#despachoCli").DataTable();
                });
            </script>        
            <table id="despachoCli" class="table table-dark table-striped">
                <thead>
                    <tr>
                        <td>Tipo Producto</td>
                        <td>Modelo</td>
                        <td>Fecha Pedido</td>
                        <td>Fecha Despacho</td>
                        <td>Fecha Recepcion</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listaDespachos}" var="despacho" > 
                        <tr>
                            <td>${despacho.tipoProducto}</td>
                            <td>${despacho.modelo}</td>
                            <td>${despacho.fechaPedido}</td>
                            <td>${despacho.fechaDespacho}</td>
                            <td>${despacho.fechaRecepcion}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
