<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Concert</title>
</head>
    <body>
        <h4>Venue: ${concert.venue}</h4>
        <p>Date: ${concert.date}</p>
        <p>Type: ${concert.concertType}</p>
        <p>Days until: ${concert.daysUntil}</p>
        <p>Bands performance order: </p>
        <table>
            <c:forEach items="${concert.bandToPerformanceOrderMap}" var="mapElement">
            <tr>
                <td>${mapElement.key}</td>
                <td>${mapElement.value.name}</td>
            </tr>
            </c:forEach>
            <table>
    </body>
</html>