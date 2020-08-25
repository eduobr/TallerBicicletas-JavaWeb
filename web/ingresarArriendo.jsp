<%-- 
    Document   : ingresarArriendo
    Created on : 25-may-2019, 19:56:24
    Author     : Zeit
--%>

<%@page import="clases.Cl_Producto"%>
<%@page import="java.util.List"%>
<%@page import="DAO.DaoArriendo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="cabecera.jsp" %>
        <title>Ingresar Arriendo</title>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="navbar.jsp" %>
            <h1>Ingresar Arriendo</h1>
            <form action="ServletCliente" method="post">
                <table class="table table-striped table-dark">
                    <tr>    <%-- start web service invocation --%>
                    <%                try {
                            wsUsuario.Service1 service = new wsUsuario.Service1();
                            wsUsuario.IServicio port = service.getBasicHttpBindingIServicio();
                            // TODO process result here
                            wsUsuario.ArrayOfClMunicipalidad result = port.obtenerMunicipalidades();
                            request.setAttribute("listaMuni", result.getClMunicipalidad());
                        } catch (Exception ex) {
                            // TODO handle custom exceptions here
                        }
                    %>
                    <%-- end web service invocation --%><hr/>

                    <td>Municipalidad</td>
                    <td>
                        <select name="cboMunicipalidad" class="form-control">
                            <option>Seleccione</option>
                            <c:forEach items="${listaMuni}" var="muni">
                                <option value="${muni.municipalidad.getValue()}">${muni.municipalidad.getValue()}</option>
                            </c:forEach>
                        </select>
                    </td>
                    </tr>
                    <tr>
                        <td>Fecha Inicio</td>
                        <!--Expresion Regular para Fecha y Hora-->
                        <td><input type="text" name="txtFecInicio" class="form-control" required="" pattern="^([0-2][0-9]|3[0-1])(\/|-)(0[1-9]|1[0-2])\2(\d{4})(\s)([0-1][0-9]|2[0-3])(:)([0-5][0-9])$"></td>
                    </tr>
                    <tr>
                        <td>Fecha Termino</td>
                        <td><input type="text" name="txtFecTermino" class="form-control" required="" pattern="^([0-2][0-9]|3[0-1])(\/|-)(0[1-9]|1[0-2])\2(\d{4})(\s)([0-1][0-9]|2[0-3])(:)([0-5][0-9])$"></td>
                    </tr>
                    <tr>
                        <td>Tipo Pago</td>
                        <td>
                            <select name="cboTipoPago" class="form-control">
                                <option>Seleccione</option>
                                <option value="Efectivo">Efectivo</option>
                                <option value="Credito">Credito</option>
                                <option value="Debito">Debito</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Deposito Garantia</td>
                        <td><input type="number" min="3000" name="txtDeposito" value="" class="form-control" required="[0-9]"></td>
                    </tr>
                    <tr>
                        <td>Total</td>
                        <td><input type="text" name="txtTotal" value="" class="form-control" required=""></td>
                    </tr>
                    <tr>
                        <td>Bicicleta</td>
                        <%
                            DaoArriendo daoArriendo = new DaoArriendo();
                            List<Cl_Producto> listaBici = daoArriendo.obtenerBicicleta();
                            request.setAttribute("listaBici", listaBici);
                        %>
                        <td>
                            <select name="cboBiciArriendo" class="form-control">
                                <c:forEach items="${listaBici}" var="bici">
                                    <option value="${bici.idProducto}">${bici.modelo}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><input type="hidden" name="txtIdPersona" value="${usuario.idPersona}" class="form-control"></td>
                        <td><input type="submit" name="btnAccion" value="Ingresar Arriendo" class="btn btn-success"></td>
                    </tr>
                </table>
            </form>
            ${mensaje}
        </div>
    </body>
</html>
