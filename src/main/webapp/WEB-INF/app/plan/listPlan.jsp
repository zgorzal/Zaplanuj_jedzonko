<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: damian
  Date: 21.05.2021
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<%@include file="/WEB-INF/headHtml/head.jsp"%>
<%--<jsp:include page="../../headHtml/head.jsp" />--%>

<body>
<jsp:include page="/WEB-INF/app/pageHeader.jsp" />

<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <jsp:include page="/WEB-INF/app/leftMenu.jsp" />

        <div class="m-4 p-3 width-medium">
            <div class="dashboard-content border-dashed p-3 m-4 view-height">
                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding">
                        <h3 class="color-header text-uppercase">LISTA PLANÓW</h3>
                    </div>
                    <div class="col d-flex justify-content-end mb-2 noPadding">
                        <a href='<c:url value="/app/plan/add" />' class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Dodaj plan</a>
                    </div>
                </div>

                <div class="schedules-content">
                    <table class="table border-bottom">
                        <thead>
                        <tr class="d-flex">
                            <th class="col-1">ID</th>
                            <th class="col-2">NAZWA</th>
                            <th class="col-7">OPIS</th>
                            <th class="col-2 center">AKCJE</th>
                        </tr>
                        </thead>
                        <tbody class="text-color-lighter">

<%--                        Fragment do FOR i i - id w wyświetlanej tabeli--%>
                        <c:forEach items="${plans}" var="plan" varStatus="index">
                        <tr class="d-flex">
                            <td class="col-1">${index.count}</td>
                            <td class="col-2">${plan.name}</td>
                            <td class="col-7">${plan.description}</td>
                            <td class="col-2 d-flex align-items-center justify-content-center flex-wrap">
                                <a href='<c:url value="/app/plan/delete?id=${plan.id}" />' class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                                <a href='<c:url value="/app/plan/detail?id=${plan.id}" />' class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                                <a href='<c:url value="/app/plan/edit?id=${plan.id}" />' class="btn btn-warning rounded-0 text-light m-1">Edytuj</a>
                            </td>
                        </tr>
                        </c:forEach>
<%--                        <tr class="d-flex">--%>
<%--                            <td class="col-1">2</td>--%>
<%--                            <td class="col-2">Plan jarski na bezmięsny tydzień</td>--%>
<%--                            <td class="col-7">--%>
<%--                                Pojęcie kuchnia wegetariańska określa pożywienie, które ani nie zawiera mięsa, ani nie zostało przygotowane na bazie pochodzącej z mięsa (np. na rosole drobiowym). Laktoowowegetarianie (najczęściej spotykany typ wegetarian w zachodnim świecie) spożywają nabiał, laktowegetarianie wykluczają jaja, ale nie inne produkty nabiałowe.--%>
<%--                            </td>--%>
<%--                            <td class="col-2 d-flex align-items-center justify-content-center flex-wrap"><a href="#" class="btn btn-danger rounded-0 text-light m-1">Usuń</a>--%>
<%--                                <a href="/app-details-schedules.html" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>--%>
<%--                                <a href="/app-edit-schedules.html" class="btn btn-warning rounded-0 text-light m-1">Edytuj</a>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>