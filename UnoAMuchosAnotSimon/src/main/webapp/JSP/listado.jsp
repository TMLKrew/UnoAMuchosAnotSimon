<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="contexto" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado</title>
        <link rel="stylesheet" type="text/css" href="${contexto}/CSS/estilo.css" />
    </head>
    <body>

        <div id="principal">
            <h2>Listado de profesores</h2>
            <ul>
                <c:forEach var="item" items="${listado}">


                    <li>${item.nombre}</li>
                    <ol>
                        <c:forEach items="${item.libros}" var="libro">
                            <li>${libro.titulo}</li>
                            </c:forEach>
                    </ol>


                </c:forEach>
            </ul>
            <br />
            <p><a href="${contexto}/" class="enlace">Men&uacute; inicial</a></p>
        </div>
    </body>
</html>