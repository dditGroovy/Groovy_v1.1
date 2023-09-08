<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>

<h2>
    <a href="#">결재 요청</a>
    <a href="#">결재 진행함</a>
    <a href="#">개인 문서함</a>
</h2> <br/><br/>

<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="CustomUser"/>
    <p>${CustomUser.employeeVO.notificationVO.dutyRequest}</p>
    <div id="formCard">
        <div class="formHeader">
            <div class="btnWrap">
                <button id="getLine">결재선 지정</button>
                <div id="sanctionLine">
                    <%@include file="line/line.jsp" %>
                </div>
            </div>
            <br/>
            <div class="formTitle">
                    ${format.formatSj}
            </div>
        </div>
        <div class="approvalWrap">
            <div class="approval">
                <!--결재선 들어오는 영역-->

            </div>
            <div>

            </div>
        </div>
        <div class="formContent">
            <!--결재양식 들어오는 영역-->
                ${format.formatCn}
        </div>
        <br/><br/>
        <button type="button">결재 제출</button>
    </div>

    <script>
        let before = new Date();

        let year = before.getFullYear();
        let month = String(before.getMonth() + 1).padStart(2, '0');
        let day = String(before.getDate()).padStart(2, '0');

        const today = year + '-' + month + '-' + day;
        console.log("TODAY ", today)
        $(function () {
            $("#sanctionNo").html("${etprCode}");
            $("#writeDate").html(today);
            $("#writer").html("${CustomUser.employeeVO.emplNm}")
        });
    </script>
</sec:authorize>