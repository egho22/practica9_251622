<%-- 
    Document   : play-music
    Created on : 10 mar 2026, 3:24:11 p.m.
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Nébula music</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/styles/styles.css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <%@include file="/WEB-INF/jsp/fragments/header.jspf"%>

        <main class="playlist-container">
            <header class="playlist-header">
                <div class="playlist-cover">
                    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTC6J1XutGfXxa2Z1z2Xl8PPtjgt5CYNK09Mg&s" alt="Cover Top Groups">
                    <div class="cover-overlay">
                        <span class="playlist-type">PLAYLIST</span>
                        <h1>Top Groups of 2020</h1>
                        <p class="playlist-description">The Global Top Groups of 2020. Cover: <span>BTS</span></p>
                        <p class="playlist-stats"><b>Nébula Music</b> • 29,131 "me gusta" • 50 canciones, 3 hr 14 min</p>
                    </div>
                </div>
            </header>

            <section class="playlist-controls">
                <button class="play-btn">▶</button>
                <button class="heart-btn">♥</button>
                <button class="more-btn">...</button>
            </section>

            <section class="playlist-table-container">
                <table class="song-table">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>TÍTULO</th>
                            <th>ÁLBUM</th>
                            <th>FECHA INCORPORACIÓN</th>
                            <th><span class="icon-clock">🕒</span></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="song-row active">
                            <td class="index">1</td>
                            <td class="title-cell">
                                <img src="https://via.placeholder.com/40" alt="BTS">
                                <div class="song-info">
                                    <span class="song-title">Dynamite</span>
                                    <span class="song-artist">BTS</span>
                                </div>
                            </td>
                            <td>Dynamite (DayTime Version)</td>
                            <td>1 dic 2020</td>
                            <td>3:19</td>
                        </tr>
                        <tr class="song-row">
                            <td class="index">2</td>
                            <td class="title-cell">
                                <img src="https://via.placeholder.com/40" alt="Maroon 5">
                                <div class="song-info">
                                    <span class="song-title">Memories</span>
                                    <span class="song-artist">Maroon 5</span>
                                </div>
                            </td>
                            <td>JORDI (Deluxe)</td>
                            <td>1 dic 2020</td>
                            <td>3:09</td>
                        </tr>
                    </tbody>
                </table>
            </section>
        </main>

        <%@include file="/WEB-INF/jsp/fragments/footer.jspf" %>


    </body>

</html>