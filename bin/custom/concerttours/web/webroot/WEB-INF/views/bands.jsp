<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Bands</title>
</head>
    <body>
        <h1>Band List</h1>
        <ul>
            <c:forEach var="band" items="${bands}">
                <li>
                    <a href="./bands/${band.id}"><img src="${band.imageURL}" />${band.name}</a>
                </li>
            </c:forEach>
        </ul>
    </body>
</html>