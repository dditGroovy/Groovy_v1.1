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
    <li><button id="Request">기안 문서함(사용자가 기안한 모든 문서)</button></li>
    <li><button id="pending">임시 저장함(작성이 완료되지 않은 문서)</button></li>
    <li><button id="r">결재 문서함(결재자로 지정되어 결재가 완료되거나 예정인 문서)</button></li>
    <hr>
    고민중
    <li><button id="we">수신 문서함(수신자/수신부서로 지정된 문서)</button></li>
    <li><button id="rq">발송 문서함(수신처를 지정하여 발송한 문서)</button></li>
    <hr>
</ul>
<div class="sanctionList">
</div>
    <script>
        $(function (){
            $.ajax({
                type: "GET",
                url: "/sanction/loadRequest?emplId=${CustomUser.employeeVO.emplId}",
                success: function (res) {
                    let code = "<table border=1>";
                    code += `<thead><tr><th>문서번호</th>><th>결재양식</th><th>제목</th><th>기안일시</th><th>상태</th></thead><tbody>`;
                    if (res.length === 0) {
                        code += "<td colspan='8'>진행 대기 문서가 없습니다</td>";
                    } else {
                        for (let i = 0; i < res.length; i++) {
                            code += `<td>\${res[i].elctrnSanctnEtprCode}</td>`;
                            code += `<td>\${res[i].elctrnSanctnFormatCode}</td>`;
                            code += `<td>\${res[i].elctrnSanctnSj}</td>`;
                            code += `<td>\${res[i].elctrnSanctnRecomDate}</td>`;
                            code += `<td>\${res[i].commonCodeSanctProgrs}</td>`;
                            code += "</tr>";
                        }
                    }
                    code += "</tbody></table>";
                    $(".sanctionList").html(code);
                }
            });
        });
    </script>
</sec:authorize>