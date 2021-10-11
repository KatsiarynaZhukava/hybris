<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Bands</title>
</head>
    <body>
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>History</th>
                    <th>Album sales</th>
                    <th>Music types</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="band" items="${bands}">
                <tr>
                    <td><a href = "<c:url value = "/bands/${band.id}"/>">${band.name}</a></td>
                    <td>${band.description}</td>
                    <td>${band.albumsSold}</td>
                    <td>
                        <c:forEach var="genre" items="${band.genres}">
                            <c:out value="${genre}  "/>
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </body>
</html>