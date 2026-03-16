<%-- 
    Document   : abot
    Created on : 10 mar 2026, 3:18:37 p.m.
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Nosotros - Nébula</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/styles/styles.css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

    </head>

    <body>
        <%@include file="/WEB-INF/jsp/fragments/header.jspf"%>
        
        <header class="about-header">
            <img src="${pageContext.request.contextPath}/assets/img/header2.jpg" />
            <h1>Acerca de nosotros</h1>
        </header>

        <main class="about-main">

            <!-- PLANES -->
            <section class="pricing-section">
                <h2 class="section-title">Planes y precios</h2>

                <div class="pricing-grid">

                    <div class="pricing-card">
                        <h3>Gratuita</h3>
                        <p>Obtén lo mejor de la música sin costo, gozando de unos cuantos anuncios.</p>

                        <h4>Características</h4>
                        <ul>
                            <li>Música on demand 24/7.</li>
                            <li>Arma hasta 30 playlists.</li>
                            <li>Agrega a tus amigos y visualiza su actividad.</li>
                        </ul>

                        <div class="price-box">
                            <span class="price">$0</span>
                            <span class="frecuency">al mes</span>
                        </div>
                    </div>

                    <div class="pricing-card popular">
                        <h3>Básica</h3>
                        <p>Lo mejor de dos mundos a un costo muy bajo.</p>

                        <h4>Características</h4>
                        <ul>
                            <li>Reproduce tu música sin anuncios.</li>
                            <li>Crea playlists ilimitadas.</li>
                            <li>Conoce tus tendencias a lo largo del año.</li>
                        </ul>

                        <div class="price-box">
                            <span class="price">$75</span>
                            <span class="frecuency">al mes</span>
                        </div>
                    </div>

                    <div class="pricing-card">
                        <h3>Premium</h3>
                        <p>Tu música con todos los poderes.</p>

                        <h4>Características</h4>
                        <ul>
                            <li>10 tokens mensuales para descargar música.</li>
                            <li>Sonido de mayor calidad.</li>
                            <li>Acceso anticipado a lanzamientos.</li>
                        </ul>

                        <div class="price-box">
                            <span class="price">$150</span>
                            <span class="frecuency">al mes</span>
                        </div>
                    </div>

                </div>
            </section>


            <!-- MAPA -->
            <section class="map-section">
                <h2 class="section-title">Visítanos</h2>
                <div class="map-wrapper">
                    <iframe
                        src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3539.201030798224!2d-109.97440986335279!3d27.49412277197101!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x86c83e1541796a29%3A0xb25ec95d470fe150!2sEdificio%201800!5e0!3m2!1ses!2smx!4v1758661870295!5m2!1ses!2smx"
                        allowfullscreen="" loading="lazy">
                    </iframe>
                </div>
            </section>


            <!-- NEWSLETTER -->
            <section class="newsletter-section">
                <div class="newsletter-card">
                    <h2>Suscríbete a nuestro newsletter</h2>
                    <form name="newsletter" class="newsletter-form">
                        <input name="txt_email" placeholder="tucorreo@dominio.com" type="email" required>
                        <button type="submit" class="btn-primary">
                            Suscribir
                        </button>
                    </form>
                </div>
            </section> 

        </main>
        <%@include file="/WEB-INF/jsp/fragments/footer.jspf" %>

    </body>

</html>