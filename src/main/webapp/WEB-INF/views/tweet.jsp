<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<c:url value="/" var="mainURL"/>
<script src="${mainURL}resources/js/tweet.js"></script>
<main role="main" class="flex-shrink-0">
    <br>
    <div class="container">

        <div class="form-row">
            <div class="form-group col-md-8">
                <h4 class="cover-heading">Szczegóły tweet'a </h4>
            </div>
        </div>
        <p>
        <table class="table">
            <tbody>

                <tr>
                    <td colspan="6">
                        <div class="card">
                            <div class="card-header">
                                <c:if test="${tweet.user.email.equals(principal.email)}">
                                    ${tweet.user.fullName}
                                </c:if>
                                <c:if test="${!tweet.user.email.equals(principal.email)}">
                                <a href="${mainURL}message/add?id=${tweet.user.id}" title="Wyślij wiadomość"><i class="far fa-envelope"></i> ${tweet.user.fullName}</a>
                                </c:if>
                                ,Utworzono : ${tweet.created},
                            </div>
                            <div class="card-body">
                            <p class="card-text">${tweet.text}</p>
                            <a href="#" class="btn btn-primary"><i class="far fa-comments"></i> Dodaj komentarz</a>
                            <p class="card-text" style="text-align: right">Liczba komentarzy <i class="far fa-comments"></i> ${tweet.commentsNumber}</p>
                            </div>
                            <div class="card-footer" data-error="<c:out value="${error == 0 ? 1 : 0}"/>">
                                <form:form modelAttribute="newComment" method="post">
                                    <form:hidden path="tweetId"/>
                                    <div class="input-group mb-3">
                                        <form:input path="text" cssClass="form-control" placeholder="Nowy komentarz"/>
                                        <div class="input-group-append">
                                            <button class="btn btn-outline-secondary" type="submit">
                                                <i class="fas fa-comment-plus"></i> Dodaj</button>
                                        </div>
                                    </div>
                                    <form:errors path="text" cssClass="error" element="div" />
                                </form:form>
                            </div>
                        </div>
                    </td>
                </tr>


                <c:forEach var="comment" items="${tweet.comments}">
                <tr>
                    <td></td>
                    <td colspan="5">
                        <div class="card">
                            <div class="card-header">

                                <c:if test="${comment.user.email.equals(principal.email)}">
                                    ${comment.user.fullName}
                                </c:if>
                                <c:if test="${!comment.user.email.equals(principal.email)}">
                                    <a href="${mainURL}message/add?id=${comment.user.id}" title="Wyślij wiadomość"><i class="far fa-envelope"></i> ${comment.user.fullName}</a>
                                </c:if>
                                ,Utworzono : ${comment.created},
                            </div>
                            <div class="card-body">
                                <p class="card-text">${comment.text}</p>
                                <a href="#" class="btn btn-primary"><i class="far fa-comments"></i> Dodaj komentarz</a>
                            </div>
                            <div class="card-footer" data-error="<c:out value="${error == comment.id ? 1 : 0}"/>">
                                <form:form modelAttribute="newComment" method="post">
                                    <form:hidden path="tweetId"/>
                                    <form:hidden path="parentId" value="${comment.id}"/>
                                    <div class="input-group mb-3">
                                        <form:input path="text" cssClass="form-control" placeholder="Nowy komentarz"/>
                                        <div class="input-group-append">
                                            <button class="btn btn-outline-secondary" type="submit">
                                                <i class="fas fa-comment-plus"></i> Dodaj</button>
                                        </div>
                                    </div>
                                    <form:errors path="text" cssClass="error" element="div" />
                                </form:form>
                            </div>
                        </div>
                    </td>
                </tr>
                    <c:forEach var="child1" items="${comment.children}">
                        <tr>
                            <td></td>
                            <td></td>
                            <td colspan="4">
                                <div class="card">
                                    <div class="card-header">

                                        <c:if test="${child1.user.email.equals(principal.email)}">
                                            ${child1.user.fullName}
                                        </c:if>
                                        <c:if test="${!child1.user.email.equals(principal.email)}">
                                            <a href="${mainURL}message/add?id=${child1.user.id}" title="Wyślij wiadomość"><i class="far fa-envelope"></i> ${child1.user.fullName}</a>
                                        </c:if>
                                        ,Utworzono : ${child1.created},
                                    </div>
                                    <div class="card-body">
                                        <p class="card-text">${child1.text}</p>
                                        <a href="#" class="btn btn-primary"><i class="far fa-comments"></i> Dodaj komentarz</a>
                                    </div>
                                    <div class="card-footer" data-error="<c:out value="${child1.id == null ? 1 : 0}"/>">
                                        <form:form modelAttribute="newComment" method="post">
                                            <form:hidden path="tweetId"/>
                                            <form:hidden path="parentId" value="${child1.id}"/>
                                            <div class="input-group mb-3">
                                                <form:input path="text" cssClass="form-control" placeholder="Nowy komentarz"/>
                                                <div class="input-group-append">
                                                    <button class="btn btn-outline-secondary" type="submit">
                                                        <i class="fas fa-comment-plus"></i> Dodaj</button>
                                                </div>
                                            </div>
                                            <form:errors path="text" cssClass="error" element="div" />
                                        </form:form>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <c:forEach var="child2" items="${child1.children}">
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td colspan="3">
                                    <div class="card">
                                        <div class="card-header">

                                            <c:if test="${child2.user.email.equals(principal.email)}">
                                                ${child2.user.fullName}
                                            </c:if>
                                            <c:if test="${!child2.user.email.equals(principal.email)}">
                                                <a href="${mainURL}message/add?id=${child2.user.id}" title="Wyślij wiadomość"><i class="far fa-envelope"></i> ${child2.user.fullName}</a>
                                            </c:if>
                                            ,Utworzono : ${child2.created},
                                        </div>
                                        <div class="card-body">
                                            <p class="card-text">${child2.text}</p>
                                            <a href="#" class="btn btn-primary"><i class="far fa-comments"></i> Dodaj komentarz</a>
                                       </div>
                                        <div class="card-footer" data-error="<c:out value="${child2 == null ? 1 : 0}"/>">
                                            <form:form modelAttribute="newComment" method="post">
                                                <form:hidden path="tweetId"/>
                                                <form:hidden path="parentId" value="${child2.id}"/>
                                                <div class="input-group mb-3">
                                                    <form:input path="text" cssClass="form-control" placeholder="Nowy komentarz"/><br>
                                                    <div class="input-group-append">
                                                        <button class="btn btn-outline-secondary" type="submit">
                                                            <i class="fas fa-comment-plus"></i> Dodaj</button>
                                                    </div>
                                                </div>
                                                <form:errors path="text" cssClass="error" element="div" />
                                            </form:form>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <c:forEach var="child3" items="${child2.children}">
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td colspan="2">
                                        <div class="card">
                                            <div class="card-header">

                                                <c:if test="${child3.user.email.equals(principal.email)}">
                                                    ${child3.user.fullName}
                                                </c:if>
                                                <c:if test="${!child3.user.email.equals(principal.email)}">
                                                    <a href="${mainURL}message/add?id=${child3.user.id}" title="Wyślij wiadomość"><i class="far fa-envelope"></i> ${child3.user.fullName}</a>
                                                </c:if>
                                                ,Utworzono : ${child3.created},
                                            </div>
                                            <div class="card-body">
                                                <p class="card-text">${child3.text}</p>
                                                <a href="#" class="btn btn-primary"><i class="far fa-comments"></i> Dodaj komentarz</a>
                                            </div>
                                            <div class="card-footer" data-error="<c:out value="${child3 == null ? 1 : 0}"/>">
                                                <form:form modelAttribute="newComment" method="post">
                                                    <form:hidden path="tweetId"/>
                                                    <form:hidden path="parentId" value="${child3.id}"/>
                                                    <div class="input-group mb-3">
                                                        <form:input path="text" cssClass="form-control" placeholder="Nowy komentarz"/><br>
                                                        <div class="input-group-append">
                                                            <button class="btn btn-outline-secondary" type="submit">
                                                                <i class="fas fa-comment-plus"></i> Dodaj</button>
                                                        </div>
                                                    </div>
                                                    <form:errors path="text" cssClass="error" element="div" />
                                                </form:form>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <c:forEach var="child4" items="${child3.children}">
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td>
                                            <div class="card">
                                                <div class="card-header">
                                                    <c:if test="${child4.user.email.equals(principal.email)}">
                                                        ${child4.user.fullName}
                                                    </c:if>
                                                    <c:if test="${!child4.user.email.equals(principal.email)}">
                                                        <a href="${mainURL}message/add?id=${child4.user.id}" title="Wyślij wiadomość"><i class="far fa-envelope"></i> ${child4.user.fullName}</a>
                                                    </c:if>
                                                    ,Utworzono : ${child4.created},
                                                </div>
                                                <div class="card-body">
                                                    <p class="card-text">${child4.text}</p>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:forEach>
                        </c:forEach>

                    </c:forEach>

                </c:forEach>

            </tbody>
        </table>
        </p>



    </div>
</main>
<jsp:include page="footer.jsp"/>

