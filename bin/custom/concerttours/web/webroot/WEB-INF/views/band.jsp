<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Bands</title>
</head>
    <body>
        <p>${band.name}</p>
        <p>${band.history}</p>
        <p>Album sales: ${band.albumSales}</p>
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
    </body>
</html>