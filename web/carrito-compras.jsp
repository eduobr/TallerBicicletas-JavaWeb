<%-- 
    Document   : carrito-compras
    Created on : 26-may-2019, 15:21:04
    Author     : Zeit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrito de Compras</title>
        <%@include file="cabecera.jsp" %>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="navbar.jsp" %>
            <h1>Carrito de Compras</h1>
            <form action="ServletCarrito" method="post">
                <c:forEach items="${carrito}" var="producto" >
                    <div style="display: inline-block; justify-content:space-around">
                        <table class="table table-dark table-striped">
                            <tr>
                                <td>Nombre:</td>
                                <td>${producto.nombre}</td>
                                <td><input type="hidden" name="txtNomProd" value="${producto.nombre}"></td>
                            </tr>
                            <tr>
                                <td>Modelo:</td>
                                <td>${producto.modelo}</td>
                                <td><input type="hidden" name="txtModeloProd" value="${producto.modelo}"></td>
                            </tr>
                            <tr>
                                <td>Descripción:</td>
                                <td>${producto.descripcion}</td>
                                <td><input type="hidden" name="txtDescProd" value="${producto.descripcion}"></td>
                            </tr>
                            <tr>
                                <td>Precio:</td>
                                <td>${producto.precio}</td>
                                <td><input type="hidden" name="txtPrecProd" value="${producto.precio}"></td>
                            </tr>
                            <tr>
                                <td>Descuento:</td>
                                <td>${producto.descuento}</td>
                                <td><input type="hidden" name="txtDescuentoProd" value="${producto.descuento}"></td>
                            </tr>
                            <tr>
                                <td>Foto:</td>
                                <td><img src="data:image/png;base64, ${producto.imagen}" width="150" height="150"></td>
                                <td><input type="hidden" name="txtFoto" value="${producto.imagen}"></td>
                            </tr>
                            <tr>
                                <td>Cantidad</td>
                                <td><input type="number" name="txtCantidad${producto.idProducto}" min="0" value="" ></td>
                            </tr>
                            <tr>
                                <td><input type="hidden" name="txtIdProd" value="${producto.idProducto}"></td>
                                    <%                                if (sesion.getAttribute("carrito") != null) {%>
                                <td><input name="btnAccion" type="submit" class="btn btn-danger" value="Eliminar Producto"></td>     
                                    <%}
                                    %>

                            </tr>
                        </table>
                    </div>
                </c:forEach> 
                <%                                if (sesion.getAttribute("carrito") != null) {%>
                <input type="submit" name="btnAccion" class="btn btn-success" value="Comprar Producto">
                <%}%>
            </form>
            ${mensaje}
        </div>
    </body>
</html>
