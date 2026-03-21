<%-- 
    Document   : comunidad
    Created on : 10 mar 2026, 3:22:37 p.m.
    Author     : PC
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Nébula music</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/styles/styles.css"/>
    </head>

    <body>
        <%@include file="/WEB-INF/jsp/fragments/header.jspf"%>
        <main>
            <section>
                <h3 style="margin: 10px; margin: 0 auto; text-align: center; display: block; font-size: 3rem;">Bienvenidos los nuevos usuarios</h3>
                <p>Total de usuarios registrados:
                    <strong><c:out value="${requestScope.totalUsuarios}"></c:out></strong>
                </p>
                <p>Bienvenido:
                    <strong><c:out value="${sessionScope}"></c:out></strong>
                </p>
                <p>Aplicación:
                    <strong><c:out value="${applicationScope.appname}"></c:out></strong>
                </p>
                <c:if test="${empty requestScope.usuarios}">
                    <p>No hay usuarios registrados aún</p>
                </c:if>
                <c:if test="${not empty requestScope.usuarios}">
                    <div class="users-grid">
                        <c:forEach var="usuario" items="${requestScope.usuarios}">
                            <div class="user-card">
                                <div class="user-avatar">
                                    <img src="${pageContext.request.contextPath}/assets/img/user.jpg" alt="John Wick">
                                </div>

                                <div class="user-info">
                                    <h4>${usuario.nombre}</h4>
                                    <span class="email"> ${usuario.correo}</span>
                                </div>

                                <div class="user-data">
                                    <span><strong>Usuario:</strong> ${usuario.pseudonimo}</span>
                                    <span><strong>Fecha de nacimiento: </strong> ${usuario.fechaNacimiento}</span>
                                    <c:if test="${usuario.cuenta eq 'premium'}">
                                        <span class="badge premium">${usuario.cuenta}</span>
                                    </c:if>
                                    <c:if test="${usuario.cuenta eq 'basica'}">
                                        <span class="badge basic">${usuario.cuenta}</span>
                                    </c:if>
                                    <c:if test="${usuario.cuenta eq 'gratis'}">
                                        <span class="badge free">${usuario.cuenta}</span>
                                    </c:if>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
                <div class="paginacion" style="text-align:center; margin-top: 30px;">

                    <c:if test="${requestScope.paginaActual > 1}">
                        <a href="${pageContext.request.contextPath}/comunidad?pagina=${requestScope.paginaActual - 1}"
                           style="margin: 0 8px;">
                            Anterior
                        </a>
                    </c:if>

                    <c:forEach var="i" begin="1" end="${requestScope.totalPaginas}">
                        <c:choose>
                            <c:when test="${i == requestScope.paginaActual}">
                                <span style="margin: 0 6px; font-weight: bold; text-decoration: underline;">
                                    <c:out value="${i}" />
                                </span>
                            </c:when>
                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/comunidad?pagina=${i}"
                                   style="margin: 0 6px;">
                                    <c:out value="${i}" />
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:if test="${requestScope.paginaActual < requestScope.totalPaginas}">
                        <a href="${pageContext.request.contextPath}/comunidad?pagina=${requestScope.paginaActual + 1}"
                           style="margin: 0 8px;">
                            Siguiente
                        </a>
                    </c:if>
                </div>
            </section>
        </main>

        <%@include file="/WEB-INF/jsp/fragments/footer.jspf" %>


    </body>

</html>
