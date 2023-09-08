<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="CustomUser"/>
<h2>
    <a href="#">결재 요청</a>
    <a href="${pageContext.request.contextPath}/sanction/inProgress">결재 진행함</a>
    <a href="${pageContext.request.contextPath}/sanction/mySanction">개인 문서함</a>
</h2> <br/><br/>

<ul id="sanctionStatus">
    <li><button id="inProgress">결재 대기 문서</button></li> 결재할 문서
    <li><button id="receive">결재 수신 문서</button></li>
    <li><button id="reference">결재 참조/열람 문서</button></li>
    <li><button id="upcoming">결재 예정 문서</button></li> 앞으로 결재할 문서
</ul>
<div class="sanctionList">
</div>

</sec:authorize>