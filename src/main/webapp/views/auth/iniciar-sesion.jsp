<%-- 
    Document   : iniciar-sesion
    Created on : 10 mar 2026, 3:23:38 p.m.
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Nébula music</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!-- NECESARIO para que funcione el diseño responsive (Mobile First) -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/styles/styles.css"/>
    </head>
    <body>
        <main class="auth-container">
            <section class="auth-card">

                <form class="auth-form"
                      name="form_iniciar_sesion" 
                      method="post" 
                      action="${pageContext.request.contextPath}/autenticacion"
                      enctype="application/x-www-form-urlencoded">

                    <div name="logo_usuario" class="form-logo">
                        <img src="${pageContext.request.contextPath}/assets/img/nebula-03.svg" alt="Logo de nebula music">
                    </div>

                    <div name="titulo" class="form-header">
                        <h3>Inicia sesión</h3>
                        <h4>${applicationScope.appname}</h4>
                        <span class="form-subtitle">Utiliza tus credenciales para iniciar</span>
                    </div>

                    <div name="correo" class="form-group">
                        <label for="correo">Correo</label>
                        <input id="correo" name="correo" type="email" required minlength="5" maxlength="100">
                    </div>

                    <div name="contrasenia" class="form-group">
                        <label for="contrasenia">Contraseña</label>
                        <input id="contrasenia" name="contrasenia" type="password" required>
                    </div>                    
                    <div name="options">
                        <p>¿Aún no tienes una cuenta? <a href="${pageContext.request.contextPath}/views/auth/registro.jsp">Regístrate</a></p>
                        <button type="submit" class="btn-primary">Iniciar</button>
                        <button type="reset" class="btn-secondary">Cancelar</button>
                        <c:if test="${not empty requestScope.error}">
                            <p>${requestScope.error}</p>
                        </c:if>
                    </div>
                </form>
            </section>
        </main>
        <%@include file="/WEB-INF/jsp/fragments/footer.jspf" %>

    </body>
</html>
