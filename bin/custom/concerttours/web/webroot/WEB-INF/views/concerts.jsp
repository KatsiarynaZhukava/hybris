<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Concerts</title>
</head>
    <body>
        <table>
            <thead>
                <tr>
                    <td>Venue</td>
                    <td>Date</td>
                    <td>Type</td>
                    <td>DaysUntil</td>
                    <td>Hashtag</td>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="concert" items="${concerts}">
                <tr>
                    <td><a href = "<c:url value = "/concerts/${concert.code}"/>">${concert.venue}</a></td>
                    <td>${concert.date}</td>
                    <td>${concert.concertType}</td>
                    <td>${concert.daysUntil}</td>
                    <td>${concert.hashtag}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </body>
</html>