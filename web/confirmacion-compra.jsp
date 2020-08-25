<%-- 
    Document   : confirmacion-compra
    Created on : 26-may-2019, 19:43:32
    Author     : Zeit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <h1>Confirmacion Compra</h1>
            <<form action="ServletCarrito" method="post">
                <c:forEach items="${carrito}" var="producto" >
                    <div style="display: inline-block; justify-content:space-around">
                        <table style="border: 1px 1px 1px 1px; border-style: solid; display: flex">
                            <tr>
                                <td>Nombre:</td>
                                <td>${producto.nombre}</td>
                                <td><input type="hidden" name="txtNomProd" value="${producto.nombre}"></td>
                            </tr>
                            <tr>
                                <td>Foto:</td>
                                <td><img src="data:image/png;base64, ${producto.imagen}" width="150" height="150"></td>
                                <td><input type="hidden" name="txtFoto" value="${producto.imagen}"></td>
                            </tr>
                            <tr>
                                <td>Precio:</td>
                                <td>${producto.precio}</td>
                                <td><input type="hidden" name="txtNomProd" value="${producto.cantidad}"></td>
                            </tr>
                            <tr>
                                <td>Descuento:</td>
                                <td>${producto.descuento}</td>
                                <td><input type="hidden" name="txtDescuentoProd" value="${producto.descuento}"></td>
                            </tr>
                            <tr>
                                <td>Cantidad:</td>
                                <td>${producto.cantidad}</td>
                                <td><input type="hidden" name="txtNomProd" value="${producto.cantidad}"></td>
                            </tr>

                        </table>
                    </div>
                </c:forEach>
                <table>
                    <tr>
                        <td>Total:</td>
                        <td>$${total}</td>
                        <td><input type="hidden" name="txtTotal" value="${total}"></td>
                    </tr>
                </table>
                <table>
                    <tr>
                        <td>N°Trajeta:</td>
                        <td><input type="text" name="txtNumTarjeta"></td>
                    </tr>
                    <tr>
                        <td>Titular de la Tarjeta</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>Caducidad</td>
                        <td></td>
                    </tr>
                </table>
                <input type="submit" name="btnAccion" value="Confirmar Compra">
            </form>
            ${mensaje}
        </div>
    </body>
</html>
