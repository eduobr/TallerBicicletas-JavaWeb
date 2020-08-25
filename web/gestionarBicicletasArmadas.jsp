<%-- 
    Document   : gestionarBicicletasArmadas
    Created on : 07-jun-2019, 10:32:51
    Author     : Zeit
--%>

<%@page import="clases.Cl_Bicicleta_Armada"%>
<%@page import="DAO.DaoBicicleta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="cabecera.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bicicletas Armadas</title>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="navbar.jsp" %>
            <%            DaoBicicleta daoBicicleta = new DaoBicicleta();
                List<Cl_Bicicleta_Armada> listaBicicletas = daoBicicleta.obtenerBicicletasArmadas();
                request.setAttribute("listaBicicletas", listaBicicletas);
            %>
            <h1>Bicicletas Armadas!</h1>
            <form action="ServletGestion" method="post">
                <table class="table table-striped table-dark">
                    <thead>
                        <tr>
                            <td>Nombre</td>
                            <td>Modelo</td>
                            <td>Estado</td>
                            <td>Imagen</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listaBicicletas}" var="bici">
                            <tr>
                                <td>
                                    ${bici.nombre}
                                </td>
                                <td>
                                    ${bici.modelo}
                                </td>
                                <td>
                                    ${bici.estado}
                                </td>
                                <td><img src="data:image/png;base64, ${bici.imagen}" width="150" height="150"></td>
                                <td><input type="hidden" name="txtIdBicicleta" value="${bici.idBicicleta}"></td>
                                <td><input type="submit" name="btnAccion" class="btn btn-success" value="Aceptar Bicicleta">
                                    <input type="submit" name="btnAccion" class="btn btn-danger" value="Rechazar Bicicleta"></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>
        </div>

    </body>
</html>
