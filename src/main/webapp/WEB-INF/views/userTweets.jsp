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
                <h4 class="cover-heading">Lista wszystkich tweet'ów użytkownika </h4>
            </div>
        </div>

            <form:form modelAttribute="tweet" method="post">
                <div class="input-group mb-3" >
                    <form:textarea path="text" rows="2" cssClass="form-control" placeholder="Treść nowego tweet'a"/><br>
                   <div class="input-group-append">
                        <button class="btn btn-outline-primary" type="button" id="button-addon3" onclick="$(this).closest('form').submit()">Dodaj</button>
                    </div>
                </div>
                <form:errors path="text" cssClass="error" element="div" />
            </form:form>
        <p>
        <table class="table table-striped">
            <tbody>
            <c:forEach items="${tweets}" var="tweet" varStatus="i">
                <tr>
                    <td>
                        <div class="card">
                            <div class="card-header">
                                ${tweet.user.fullName}, Utworzono : ${tweet.created}
                            </div>
                            <div class="card-body">
                            <p class="card-text">${tweet.text}</p>
                            <a href="${mainURL}tweet?id=${tweet.id}" class="btn btn-primary"><i class="fas fa-search-plus"></i> Pokaż szczegóły</a>
                            <p class="card-text" style="text-align: right">Liczba komentarzy <i class="far fa-comments"></i> ${tweet.commentsNumber}</p>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </p>
    </div>
</main>
<jsp:include page="footer.jsp"/>

