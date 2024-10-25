<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registro de Usuario</title>
</head>
<body>
    <h1>Registro de Usuario</h1>
    <form action="RegisterServlet" method="post">
        Nombre de usuario: <input type="text" name="username" required><br>
        Contraseña: <input type="password" name="password" required><br>
        Correo electrónico: <input type="email" name="email" required><br>
        <input type="submit" value="Registrar">
    </form>
    <c:if test="${not empty errorMessage}">
        <p style="color:red;">${errorMessage}</p>
    </c:if>
</body>
</html>
