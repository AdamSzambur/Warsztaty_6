<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<c:url value="/" var="mainURL"/>
<main role="main" class="flex-shrink-0">
    <br>
    <div class="container">
        <div class="rounded border p-5">
        <h4 class="cover-heading">Nowa wiadomość do ${recipientName}</h4>
            <span class="error">${msg}</span>
        <p>
            <form:form method="post" modelAttribute="message" cssClass="form-group col-md-12">
                    <form:hidden path="senderId"/>
                    <form:hidden path="recipientId"/>

                <div class="form-group">
                    <label for="sender">Nadawca</label>
                    <input type="text" value="${senderName}" class="form-control" readonly id="sender">
                </div>
                <div class="form-group">
                    <label for="recipient">Odbiorca</label>
                    <input type="text" value="${recipientName}" class="form-control" readonly id="recipient">
                </div>
                <div class="form-group">
                    <label for="title">Tytuł</label>
                    <form:input path="title" cssClass="form-control" id="title" placeholder="Tytuł wiadomości"/>
                    <form:errors path="title" cssClass="error" element="div" />
                </div>
                <div class="form-group">
                    <label for="text">Treść wiadomości</label>
                    <form:textarea path="text" rows="5" cssClass="form-control" placeholder="Treść wiadomości"/><br>
                    <form:errors path="text" cssClass="error" element="div" />
                </div>
                <button type="submit" class="btn btn-primary">Wyślij wiadomość</button>
            </form:form>
        </p>
        </div>
    </div>





</main>
<jsp:include page="footer.jsp"/>

