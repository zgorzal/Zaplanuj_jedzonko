<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%--
  Created by IntelliJ IDEA.
  User: xyz
  Date: 24.05.2021
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<%@include file="/WEB-INF/headHtml/head.jsp"%>

<body>
<jsp:include page="/WEB-INF/app/pageHeader.jsp" />
<section class="dashboard-section">
  <div class="row dashboard-nowrap">
    <jsp:include page="/WEB-INF/app/leftMenu.jsp" />
    <div class="m-4 p-4 width-medium">
      <div class="dashboard-header m-4">
        <div class="dashboard-menu">
          <div class="menu-item border-dashed">
            <a href="/app/recipe/add">
              <i class="far fa-plus-square icon-plus-square"></i>
              <span class="title">dodaj przepis</span>
            </a>
          </div>
          <div class="menu-item border-dashed">
            <a href='<c:url value="/app/plan/add" />'>
              <i class="far fa-plus-square icon-plus-square"></i>
              <span class="title">dodaj plan</span>
            </a>
          </div>
          <div class="menu-item border-dashed">
            <a href='<c:url value="/app/recipe/plan/add" />'>
              <i class="far fa-plus-square icon-plus-square"></i>
              <span class="title">dodaj przepis do planu</span>
            </a>
          </div>
        </div>

        <div class="dashboard-alerts">
          <div class="alert-item alert-info">
            <i class="fas icon-circle fa-info-circle"></i>
            <span class="font-weight-bold">Liczba przepisów: ${recipe_count}</span>
          </div>
          <div class="alert-item alert-light">
            <i class="far icon-calendar fa-calendar-alt"></i>
            <span class="font-weight-bold">Liczba planów: ${plan_count}</span>
          </div>
        </div>
      </div>
      <div class="m-4 p-4 border-dashed">
        <h2 class="dashboard-content-title">
          <span>Ostatnio dodany plan:</span> ${plan_name}
        </h2>
        <table class="table">
          <c:if test="${latest_plan.name==null}">
            <thead>
            <hr>
            <h3 class="dashboard-content-title" style="font-weight: normal">
              Do planu nie dodano jeszcze żadnych przepisów
            </h3>
            </thead>
          </c:if>
          <c:if test="${latest_plan.monday.size()>0}">
            <thead>
            <tr class="d-flex">
              <th class="col-2">Poniedziałek</th>
              <th class="col-8"></th>
              <th class="col-2"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="recipeString" items="${latest_plan.monday}">
              <c:set var="recipeArray" value="${fn:split(recipeString, ';;;')}"/>
              <tr class="d-flex">
                <td class="col-2">${recipeArray[0]}</td>
                <td class="col-8">${recipeArray[1]}</td>
                <td class="col-2"><a href='<c:url value="/app/recipe/details?id=${recipeArray[3]}"/>' class="btn btn-primary rounded-0">Szczegóły</a></td>
              </tr>
            </c:forEach>
          </c:if>
            <c:if test="${latest_plan.tuesday.size()>0}">
            <thead>
            <tr class="d-flex">
              <th class="col-2">Wtorek</th>
              <th class="col-8"></th>
              <th class="col-2"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="recipeString" items="${latest_plan.tuesday}">
              <c:set var="recipeArray" value="${fn:split(recipeString, ';;;')}"/>
              <tr class="d-flex">
                <td class="col-2">${recipeArray[0]}</td>
                <td class="col-8">${recipeArray[1]}</td>
                <td class="col-2"><a href='<c:url value="/app/recipe/details?id=${recipeArray[3]}"/>' class="btn btn-primary rounded-0">Szczegóły</a></td>
              </tr>
            </c:forEach>
            </c:if>
            <c:if test="${latest_plan.wednesday.size()>0}">
            <thead>
            <tr class="d-flex">
              <th class="col-2">Środa</th>
              <th class="col-8"></th>
              <th class="col-2"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="recipeString" items="${latest_plan.wednesday}">
              <c:set var="recipeArray" value="${fn:split(recipeString, ';;;')}"/>
              <tr class="d-flex">
                <td class="col-2">${recipeArray[0]}</td>
                <td class="col-8">${recipeArray[1]}</td>
                <td class="col-2"><a href='<c:url value="/app/recipe/details?id=${recipeArray[3]}"/>' class="btn btn-primary rounded-0">Szczegóły</a></td>
              </tr>
            </c:forEach>
            </c:if>
            <c:if test="${latest_plan.thursday.size()>0}">
            <thead>
            <tr class="d-flex">
              <th class="col-2">Czwartek</th>
              <th class="col-8"></th>
              <th class="col-2"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="recipeString" items="${latest_plan.thursday}">
              <c:set var="recipeArray" value="${fn:split(recipeString, ';;;')}"/>
              <tr class="d-flex">
                <td class="col-2">${recipeArray[0]}</td>
                <td class="col-8">${recipeArray[1]}</td>
                <td class="col-2"><a href='<c:url value="/app/recipe/details?id=${recipeArray[3]}"/>' class="btn btn-primary rounded-0">Szczegóły</a></td>
              </tr>
            </c:forEach>
            </c:if>
            <c:if test="${latest_plan.friday.size()>0}">
            <thead>
            <tr class="d-flex">
              <th class="col-2">Piątek</th>
              <th class="col-8"></th>
              <th class="col-2"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="recipeString" items="${latest_plan.friday}">
              <c:set var="recipeArray" value="${fn:split(recipeString, ';;;')}"/>
              <tr class="d-flex">
                <td class="col-2">${recipeArray[0]}</td>
                <td class="col-8">${recipeArray[1]}</td>
                <td class="col-2"><a href='<c:url value="/app/recipe/details?id=${recipeArray[3]}"/>' class="btn btn-primary rounded-0">Szczegóły</a></td>
              </tr>
            </c:forEach>
            </c:if>
            <c:if test="${latest_plan.saturday.size()>0}">
            <thead>
            <tr class="d-flex">
              <th class="col-2">Sobota</th>
              <th class="col-8"></th>
              <th class="col-2"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="recipeString" items="${latest_plan.saturday}">
              <c:set var="recipeArray" value="${fn:split(recipeString, ';;;')}"/>
              <tr class="d-flex">
                <td class="col-2">${recipeArray[0]}</td>
                <td class="col-8">${recipeArray[1]}</td>
                <td class="col-2"><a href='<c:url value="/app/recipe/details?id=${recipeArray[3]}"/>' class="btn btn-primary rounded-0">Szczegóły</a></td>
              </tr>
            </c:forEach>
            </c:if>
            <c:if test="${latest_plan.sunday.size()>0}">
            <thead>
            <tr class="d-flex">
              <th class="col-2">Niedziela</th>
              <th class="col-8"></th>
              <th class="col-2"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="recipeString" items="${latest_plan.sunday}">
              <c:set var="recipeArray" value="${fn:split(recipeString, ';;;')}"/>
              <tr class="d-flex">
                <td class="col-2">${recipeArray[0]}</td>
                <td class="col-8">${recipeArray[1]}</td>
                <td class="col-2"><a href='<c:url value="/app/recipe/details?id=${recipeArray[3]}"/>' class="btn btn-primary rounded-0">Szczegóły</a></td>
              </tr>
            </c:forEach>
            </c:if>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</section>


<jsp:include page="/WEB-INF/footer.jsp"/>