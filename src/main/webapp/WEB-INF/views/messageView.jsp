<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<c:url value="/" var="mainURL"/>
<main role="main" class="flex-shrink-0">
    <br>
    <div class="container">
        <div class="rounded border p-5">
            <h4 class="cover-heading">Podgląd wiadomości</h4>
        <p>

            <form class="form-group col-md-12">


                <div class="form-group">
                    <label for="sender">Nadawca</label>
                    <input type="text" value="${message.sender.fullName}" class="form-control" readonly id="sender">
                </div>
                <div class="form-group">
                    <label for="recipient">Odbiorca</label>
                    <input type="text" value="${message.recipient.fullName}" class="form-control" readonly id="recipient">
                </div>
                <div class="form-group col-md-3">
                    <label for="created">Utworzono</label>
                    <input type="text" value="${message.created}" class="form-control" readonly id="created">
                </div>
                <div class="form-group">
                    <label for="title">Tytuł</label>
                    <input type="text" id="title" class="form-control" value="${message.title}" readonly>
                </div>
                <div class="form-group">
                    <label for="text">Treść wiadomości</label>
                    <textarea rows="5" class="form-control" id="text" readonly>${message.text}</textarea>
                </div>
            </form>

        </p>
        </div>
    </div>





</main>
<jsp:include page="footer.jsp"/>

