<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<c:url value="/" var="mainURL"/>
<main role="main" class="flex-shrink-0">
    <br>
    <div class="container">
        <div class="rounded border p-5">
        <h4 class="cover-heading">Dane zarejestrowanego użytkownika</h4>
            <span class="error">${msg}</span>
        <p>
            <form:form method="post" modelAttribute="data" cssClass="form-group col-md-4">

                <div class="form-group">
                    <label for="firstName">Imię</label>
                    <form:input path="firstName" cssClass="form-control" id="firstName"/>
                    <form:errors path="firstName" cssClass="error" element="div" />
                </div>

                <div class="form-group">
                    <label for="lastName">Nazwisko</label>
                    <form:input path="lastName" cssClass="form-control" id="lastName"/>
                    <form:errors path="lastName" cssClass="error" element="div" />
                </div>

                <div class="form-group">
                    <label for="email">Adres e-mail</label>
                    <form:input path="email" cssClass="form-control" id="email" readonly="true"/>
                    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                    <form:errors path="email" cssClass="error" element="div" />
                </div>


                <div class="form-group">
                    <label for="password">Hasło</label>
                    <form:password path="password" cssClass="form-control" id="password"/>
                    <form:errors path="password" cssClass="error" element="div" />
                </div>

                <div class="form-group">
                    <label for="rePassword">Powtórz hasło</label>
                    <form:password path="rePassword" cssClass="form-control" id="rePassword"/>
                    <form:errors path="rePassword" cssClass="error" element="div" />
                </div>
                <button type="submit" class="btn btn-primary">Zaktualizuj dane użytkownika</button>
            </form:form>
        </p>
        </div>
    </div>





</main>
<jsp:include page="footer.jsp"/>

