<%-- 
    Document   : ingresarTrabajador
    Created on : 14-may-2019, 13:27:02
    Author     : Zeit
--%>
<%@page import="clases.Cl_Comuna"%>
<%@page import="DAO.DaoPersona"%>
<%@page import="clases.Cl_Tipo_Trabajador"%>
<%@page import="DAO.DaoTrabajador"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ingresar Trabajador</title>
        <%@include file="cabecera.jsp" %>
    </head>
    <body>
        <div class="container-fluid">
            
            <%@include file="navbar.jsp"%>
            <%            DaoTrabajador daoTrabajador = new DaoTrabajador();
                DaoPersona daoPersona = new DaoPersona();
                List<Cl_Tipo_Trabajador> listaTipoTrab = daoTrabajador.obtenerTipoTrabajador();
                List<Cl_Comuna> listaComunas = daoPersona.obtenerComunas();
                request.setAttribute("listaTipoTrab", listaTipoTrab);
                request.setAttribute("listaComunas", listaComunas);
            %>
            <h1>Ingresar Trabajador</h1>
            <form action="ServletTrabajador" method="post" enctype="multipart/form-data">
                <table class="table table-striped table-dark">
                    <tbody>
                        <tr>
                            <td>Rut</td>
                            <td><input type="text" name="txtRut" class="form-control" value=""></td>
                        </tr>
                        <tr>
                            <td>Nombre</td>
                            <td><input type="text" name="txtNombre" class="form-control" value="" minlength="3" required pattern="[A-Za-z]{3-10}"></td>
                        </tr>
                        <tr>
                            <td>Apellido</td>
                            <td><input type="text" name="txtApellido" class="form-control" value="" required pattern="[A-Za-z]{3-10}"></td>
                        </tr>
                        <tr>
                            <td>Edad</td>
                            <td><input type="number" name="txtEdad" class="form-control" min="18" value="" required pattern="[0-9]"></td>
                        </tr>
                        <tr>
                            <td>Direccion</td>
                            <td><input type="text" name="txtDireccion" class="form-control" required pattern="[A-Za-z]{3-20}"></td>
                        </tr>
                        <tr>
                            <td>Correo</td>
                            <td><input type="email" name="txtCorreo" class="form-control" value="" required></td>
                        </tr>
                        <tr>
                            <td>Comuna</td>
                            <td>
                                <select name="cboComuna" class="form-control">
                                    <c:forEach items="${listaComunas}" var="comuna" > 
                                        <option value="${comuna.idComuna}">${comuna.comuna}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Sueldo</td>
                            <td><input type="number" min="280000" name="txtSueldo" class="form-control" value="" required pattern="[0-9]"></td>
                        </tr>
                        <tr>
                            <td>Cotizaciones AFP</td>
                            <td><input type="file" name="flAfp" class="form-control" required accept="image/x-png,image/gif,image/jpeg,application/pdf"></td>
                        </tr>
                        <tr>
                            <td>Certificado Salud</td>
                            <td><input type="file" name="flSalud" class="form-control" value="" required accept="image/x-png,image/gif,image/jpeg,application/pdf"></td>
                        </tr>
                        <tr>
                            <td>Fotocopia Carnet</td>
                            <td><input type="file" name="flCarnet" class="form-control" value="" required accept="image/x-png,image/gif,image/jpeg,application/pdf"></td>
                        </tr>
                        <tr>
                            <td>Certificado de Nacimiento</td>
                            <td><input type="file" name="flCerNacimiento" class="form-control" value="" required accept="image/x-png,image/gif,image/jpeg,application/pdf"></td>
                        </tr>
                        <tr>
                            <td>Certificado de Residencia</td>
                            <td><input type="file" name="flCerResidencia" class="form-control" value="" required accept="image/x-png,image/gif,image/jpeg,application/pdf"></td>
                        </tr>
                        <tr>
                            <td>Tipo de Trabajador</td>
                            <td>
                                <select name="cboTipoTrabajador" class="form-control">
                                    <c:forEach items="${listaTipoTrab}" var="tipo" > 
                                        <option value="${tipo.idTipoTrabajo}">${tipo.tipoTrabajo}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Usuario</td>
                            <td><input type="text" name="txtUsuario" class="form-control" value="" minlength="3" required pattern="[A-Za-z0-9]{3-10}" ></td>
                        </tr>
                        <tr>
                            <td>Contraseña</td>
                            <td><input type="password" name="txtPass" class="form-control" value="" minlength="3" required pattern="[A-Za-z0-9]{3-100}" ></td>
                        </tr>
                        <tr>
                            <td><input type="reset" value="Limpiar" class="btn btn-primary btn-block"></td>
                            <td><input type="submit" name="btnAccion" class="btn btn-success btn-block" value="Guardar Trabajador"></td>
                        </tr>
                    </tbody>
                </table>
            </form>

            ${mensaje}
        </div>
    </body>
</html>
