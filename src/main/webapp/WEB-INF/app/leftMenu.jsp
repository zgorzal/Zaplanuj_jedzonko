<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: damian
  Date: 21.05.2021
  Time: 00:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="nav flex-column long-bg">
    <li class="nav-item">
        <a class="nav-link" href='<c:url value="/app/dashboard" />'>
            <span>Pulpit</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href='<c:url value="/app/recipe/list" />'>
            <span>Przepisy</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href='<c:url value="/app/plan/list" />'>
            <span>Plany</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href='<c:url value="/app/user/edit" />'>
            <span>Edytuj dane</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link disabled" href='<c:url value="/app/user/changePass" />'>
            <span>Zmień hasło</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
    <li class="nav-item">
        <%--                tu powinien być warunek sprawdzający czy użytkownik jest super user i ma dostęp do tej pozycji menu po zalogowaniu --%>
        <a class="nav-link" href='<c:url value="/app/users/list" />'>
            <span>Użytkownicy</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
</ul>