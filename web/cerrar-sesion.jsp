<%-- 
    Document   : cerrar-sesion
    Created on : 18-may-2019, 14:31:02
    Author     : Zeit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    session.invalidate();
    response.sendRedirect("listarProductos.jsp");
%>
