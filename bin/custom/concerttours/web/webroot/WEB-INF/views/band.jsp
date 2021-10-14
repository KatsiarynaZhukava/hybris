<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Bands</title>
</head>
    <body>
        <p>${band.name}</p>
        <p><img src="${band.imageURL}"/></p>
        <p>${band.description}</p>
        <p>Album sales: ${band.albumsSold}</p>
        <p>Members: </p>
        <table>
            <thead>
                <th>Name</th>
                <th>Instrument</th>
            </thead>
            <tbody>
                <c:forEach var="bandMember" items="${band.members}">
                    <tr>
                        <td><c:out value="${bandMember.fullName}"/></td>
                        <td><c:out value="${bandMember.instrument}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <h5>Music types: </h5>
        <c:forEach var="genre" items="${band.genres}">
            <p><c:out value="${genre}"/></p>
        </c:forEach>
        <ul>
            <c:forEach var="tour" items="${band.tours}">
                <li><a href="../tours/${tour.id}">${tour.tourName}</a>(number of concerts: ${tour.numberOfConcerts})</li>
            </c:forEach>
        </ul>
        <a href="../bands">Back to Band List</a>
    </body>
</html>