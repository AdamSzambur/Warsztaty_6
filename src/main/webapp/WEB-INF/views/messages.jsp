<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<c:url value="/" var="mainURL"/>
<main role="main" class="flex-shrink-0">
    <br>
    <div class="container">

        <div class="form-row">
            <div class="form-group col-md-8">
                <h4 class="cover-heading">Lista wiadomosci</h4>
            </div>
        </div>

        <div class="form-row">
            <div class="form-group col-md-8">
                <form method="get">
                    <select name="box" onchange="this.form.submit()"
                            class="custom-select mr-sm-2" id="inlineFormCustomSelect">
                        <option value="inbox" <c:if test="${box.equals('inbox')}">selected</c:if>>Skrzynka odbiorcza</option>
                        <option value="outbox" <c:if test="${box.equals('outbox')}">selected</c:if>>Skrzynka nadawcza</option>
                    </select>
                </form>
            </div>
        </div>


        <p>


        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Nadawca</th>
                <th scope="col">Odbiorca</th>
                <th scope="col">Data wysłania</th>
                <th scope="col">Treść wiadomości</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${messages}" var="message" varStatus="loopNum">
                <tr class="<c:if test='${!message.readed && box.equals("inbox")}'>table-primary</c:if>">
                    <th scope="row"></th>
                    <td onclick="window.location.href='${mainURL}message/view?id=${message.id}'">
                        <c:if test="${message.sender.email.equals(principal.email)}">
                            ${message.sender.fullName}
                        </c:if>
                        <c:if test="${!message.sender.email.equals(principal.email)}">
                            <a href="${mainURL}message/add?id=${message.sender.id}" title="Wyślij wiadomość"><i class="far fa-envelope"></i> ${message.sender.fullName}</a>
                        </c:if>
                    </td>
                    <td onclick="window.location.href='${mainURL}message/view?id=${message.id}'">
                        <c:if test="${message.recipient.email.equals(principal.email)}">
                            ${message.recipient.fullName}
                        </c:if>
                        <c:if test="${!message.recipient.email.equals(principal.email)}">
                            <a href="${mainURL}message/add?id=${message.recipient.id}" title="Wyślij wiadomość"><i class="far fa-envelope"></i> ${message.recipient.fullName}</a>
                        </c:if>
                    </td>
                    <td onclick="window.location.href='${mainURL}message/view?id=${message.id}'">${message.created}</td>
                    <td onclick="window.location.href='${mainURL}message/view?id=${message.id}'"><c:out value="${message.text.length()>30 ? message.text.substring(0,30).concat('...') : message.text}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>


<%--        <table class="table table-striped">--%>
<%--            <tbody>--%>
<%--            <c:forEach items="${tweets}" var="tweet" varStatus="i">--%>
<%--                <tr>--%>
<%--                    <td>--%>
<%--                        <div class="card">--%>
<%--                            <div class="card-header">--%>
<%--&lt;%&ndash;                                Wysyłamy wiadomość tylko uzytkownikom innym niz aktualnie zalogowany&ndash;%&gt;--%>
<%--                                <c:if test="${tweet.user.email.equals(principal.email)}">--%>
<%--                                    ${tweet.user.fullName}--%>
<%--                                </c:if>--%>
<%--                                <c:if test="${!tweet.user.email.equals(principal.email)}">--%>
<%--                                    <a href="${mainURL}message/add?id=${tweet.user.id}" title="Wyślij wiadomość"><i class="far fa-envelope"></i> ${tweet.user.fullName}</a>--%>
<%--                                </c:if>--%>
<%--                                ,Utworzono : ${tweet.created},--%>
<%--                            </div>--%>
<%--                            <div class="card-body">--%>
<%--                            <p class="card-text">${tweet.text}</p>--%>
<%--                            <a href="${mainURL}tweet?id=${tweet.id}" class="btn btn-primary"><i class="fas fa-search-plus"></i> Pokaż szczegóły</a>--%>
<%--                            <p class="card-text" style="text-align: right">Liczba komentarzy <i class="far fa-comments"></i> ${tweet.commentsNumber}</p>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--            </c:forEach>--%>
<%--            </tbody>--%>
<%--        </table>--%>
        </p>
    </div>
</main>
<jsp:include page="footer.jsp"/>

