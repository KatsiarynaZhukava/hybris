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
                </tr>
            </thead>
            <tbody>
            <c:forEach var="band" items="${bands}">
                <tr>
                    <td><a href = "<c:url value = "/bands/${band.code}"/>">${band.name}</a></td>
                    <td>${band.history}</td>
                    <td>${band.albumSales}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </body>
</html>