<%-- 
    Document   : index
    Created on : 29-abr-2019, 9:37:05
    Author     : Zeit
--%>

<%@page import="clases.Cl_Producto"%>
<%@page import="DAO.DaoProducto"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="cabecera.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="navbar.jsp" %>
            <h1>Comprar Productos</h1>    <%-- start web service invocation --%><hr/>
            <p style="color:white;">${mensaje}</p>
            <%            DaoProducto daoProducto = new DaoProducto();
                List<Cl_Producto> productos = daoProducto.obtenerProductos();
                request.setAttribute("listaProductos", productos);
            %>
            <div class="bd-example">
                <div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
                    <ol class="carousel-indicators">
                        <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>
                        <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
                        <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
                    </ol>
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img src="img/bici1.jpg" class="d-block w-100" alt="...">
                            <div class="carousel-caption d-none d-md-block">
                                <h5>Compra en nuestra Tienda</h5>
                                <p>Tenemos los mejores Productos</p>
                            </div>
                        </div>
                        <div class="carousel-item">
                            <img src="img/bici2.jpg" class="d-block w-100" alt="...">
                            <div class="carousel-caption d-none d-md-block">
                                <h5>Crea los mejores recuerdos</h5>
                                <p>Junto a nosotros</p>
                            </div>
                        </div>
                        <div class="carousel-item">
                            <img src="img/bici3.jpg" class="d-block w-100" alt="...">
                            <div class="carousel-caption d-none d-md-block">
                                <h5>Vive una aventura</h5>
                                <p>Sobre ruedas</p>
                            </div>
                        </div>
                    </div>
                    <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>
            <hr/>
            <div>
                <c:forEach items="${listaProductos}" var="producto" > 
                    <form action="ServletCarrito" method="post" style="display: inline-block; justify-content:space-around; margin-right: 10px; margin-left: 10px; margin-bottom: 15px;">
                        <div class="card" style="width: 18rem;">
                            <h4 class="card-title" style="padding:4px">${producto.nombre}</h4>
                            <img src="data:image/png;base64, ${producto.imagen}" class="card-img-top" alt="..." width="150" height="220">
                            <div class="card-body">

                                <p class="card-text">${producto.descripcion}</p>
                            </div>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">Modelo: ${producto.modelo}</li>
                                <li class="list-group-item">Precio: ${producto.precio}</li>
                                <li class="list-group-item">Descuento: ${producto.descuento}%</li>
                                <li class="list-group-item">Stock: ${producto.stock}</li>
                            </ul>
                            <div class="card-body">
                                <%
                                if (sesion.getAttribute("usuario") != null) {%>
                                <input type="hidden" name="txtNomProd" value="${producto.nombre}">
                                <input type="hidden" name="txtModeloProd" value="${producto.modelo}">
                                <input type="hidden" name="txtDescProd" value="${producto.descripcion}">
                                <input type="hidden" name="txtPrecProd" value="${producto.precio}">
                                <input type="hidden" name="txtDescuentoProd" value="${producto.descuento}">
                                <input type="hidden" name="txtFoto" value="${producto.imagen}">
                                <input type="hidden" name="txtIdProd" value="${producto.idProducto}">
                                <input name="btnAccion" type="submit" class="btn btn-outline-warning btn-block" value="Agregar al Carrito">
                                <%}%>
                            </div>
                        </div>
                    </form>
                </c:forEach>    
            </div>
            
        </div>
    </body>
</html>
