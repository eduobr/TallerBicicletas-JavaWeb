<%-- 
    Document   : armarBicicleta
    Created on : 19-may-2019, 18:33:33
    Author     : Zeit
--%>

<%@page import="clases.Cl_Tipo_Producto"%>
<%@page import="DAO.DaoProducto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Armar Bicicleta</title>
        <%@include file="cabecera.jsp" %>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="navbar.jsp" %>
            <%            DaoProducto daoProducto = new DaoProducto();
                List<Cl_Tipo_Producto> listaTipoProd = daoProducto.obtenerTipoProdTecnico();
                request.setAttribute("listaTipoProd", listaTipoProd);
            %>
            <h1>Armar Bicicleta!</h1>
            <form action="ServletTrabajador" method="post" enctype="multipart/form-data">
                <table class="table table-striped table-dark">
                    <tr>
                        <td>Modelo</td>
                        <td><input type="text" name="txtModelo" class="form-control" value="" required pattern="[A-Za-z]{5-7}"></td>
                    </tr>
                    <tr>
                        <td>Descripcion</td>
                        <td><textarea name="txtDescripcion" class="form-control" value="" rows="4" minlength="10" maxlength="100" required pattern="[A-Za-z0-9]{5-100}"></textarea></td>
                    </tr>
                    <tr>
                        <td>Tipo Bicicleta</td>
                        <td>
                            <select name="cboTipoBici" class="form-control">
                                <c:forEach items="${listaTipoProd}" var="tipoProd">
                                    <option value="${tipoProd.idTipoProducto}">${tipoProd.tipoProducto}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Aro</td>
                        <td><input type="number" min="8" max="29" name="txtAro" class="form-control" value="" required></td>
                    </tr>
                    <tr>
                        <td>Cantidad</td>
                        <td><input type="number" min="1" name="txtCantidad" class="form-control" value="" required></td>
                    </tr>
                    <tr>
                        <td>Foto</td>
                        <td><input type="file" name="flBicicleta" class="form-control" value="" required="" accept="image/x-png,image/gif,image/jpeg"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" name="btnAccion" class="btn btn-success btn-block" value="Armar Bicicleta"></td>
                    </tr>
                </table>
            </form>
        </div>
        ${mensaje}
    </body>
</html>
