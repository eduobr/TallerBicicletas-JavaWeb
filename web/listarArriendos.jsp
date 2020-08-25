

<%@page import="clases.Cl_Arriendo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DAO.DaoArriendo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar Arriendos</title>
        <%@include file="cabecera.jsp" %>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="navbar.jsp" %>
            <h1>Lista de Arriendos</h1>
            <%            DaoArriendo daoArriendo = new DaoArriendo();
                List<Cl_Arriendo> arriendos = daoArriendo.listarArriendos(usuario.getIdPersona());
                request.setAttribute("listaArriendos", arriendos);
            %>
            <hr/>
            <script type="text/javascript">
                $(document).ready(function () {
                    $("#arriendos").DataTable();
                });
            </script>
            <table id="arriendos" class="table table-striped table-dark">
                <thead>
                    <tr>
                        <td>Municipalidad</td>
                        <td>Fecha de Inicio</td>
                        <td>Fecha Termino</td>
                        <td>Tipo Pago</td>
                        <td>Total</td>
                        <td>Deposito Garantia</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listaArriendos}" var="arriendo" > 
                        <tr>
                            <td>${arriendo.municipalidad}</td>
                            <td><fmt:formatDate value="${arriendo.fechaInicio}" pattern="dd-MM-yyyy" /></td>
                            <td><fmt:formatDate value="${arriendo.fechaTermino}" pattern="dd-MM-yyyy" /></td>
                            <td>${arriendo.tipoPago}</td>
                            <td>${arriendo.total}</td>
                            <td>${arriendo.depositoGarantia}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            ${mensaje}
        </div>
    </body>
</html>
