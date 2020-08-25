<%-- 
    Document   : listarProductosEnEspera
    Created on : 09-jun-2019, 14:19:06
    Author     : Zeit
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="clases.Cl_Producto"%>
<%@page import="java.util.List"%>
<%@page import="DAO.DaoProducto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos en Espera</title>
        <%@include file="cabecera.jsp" %>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="navbar.jsp" %>
            <h1>Productos en Espera!</h1>
            <%            DaoProducto daoProducto = new DaoProducto();
                List<Cl_Producto> listaProd = daoProducto.obtenerProductosEnEspera();
                request.setAttribute("listaProd", listaProd);
            %>
            <!--Crear fomulario dentro del formulario-->
            <table class="table table-striped table-dark">
                <c:forEach items="${listaProd}" var="prod">
                    <form action="ServletGestion" method="post">
                        <tr>
                            <td>Nombre:</td>
                            <td>${prod.nombre}</td>
                            <td><input type="hidden" name="txtNomProd" value="${prod.nombre}" class="form-control"></td>
                            <td>Modelo:</td>
                            <td>${prod.modelo}</td>
                            <td><input type="hidden" name="txtModeloProd" value="${prod.modelo}" class="form-control"></td>
                            <td><input type="hidden" name="txtDescProd" value="${prod.descripcion}" class="form-control"></td>
                            <td>Cantidad</td>
                            <c:if test="${prod.aro>0}">
                                <td>${prod.aro}</td>
                            </c:if>
                            <td>Precio:</td>
                            <td><input type="number" min="1" name="txtPrecProd" value="" class="form-control" required pattern="[0-9]"></td>
                            <td>Descuento:</td>
                            <td><input type="number" min="0" name="txtDescuentoProd" value="" class="form-control" required pattern="[0-9]"></td>
                            <td>Foto:</td>
                            <td><img src="data:image/png;base64, ${prod.imagen}" width="150" height="150"></td>
                            <td><input type="hidden" name="txtFoto" value="${prod.imagen}"></td>
                            <td><input type="hidden" name="txtIdProd" value="${prod.idProducto}"></td>
                                <%
                                    if (sesion.getAttribute("usuario") != null) {%>
                            <td><input name="btnAccion" type="submit" value="Poner En Venta"></td>    
                                <%}
                                %>

                        </tr>
                    </form>
                </c:forEach>
            </table>
            ${mensaje}
        </div>
    </body>
</html>
