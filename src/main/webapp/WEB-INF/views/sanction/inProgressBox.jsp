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
        <li>
            <button id="loadAwaiting">결재 대기 문서</button>
        </li>
        결재할 문서
        <li>
            <button id="receive">결재 수신 문서</button>
        </li>
        <li>
            <button id="reference">결재 참조/열람 문서</button>
        </li>
        <li>
            <button id="upcoming">결재 예정 문서</button>
        </li>
        앞으로 결재할 문서
    </ul>
    <div class="sanctionList">
    </div>
    <script>
        $(function () {

        })

        // 결재 대기 문서
        $("#loadAwaiting").on("click", function () {
            $.ajax({
                url: '/sanction/loadAwaiting?progrsCode=SANCTN015&emplId=' + '${CustomUser.employeeVO.emplId}',
                type: 'GET',
                success: function (res) {
                    let code = "<table border=1>";
                    code += `<thead><tr><th>문서번호</th>><th>결재양식</th><th>제목</th><th>기안일시</th><th>상태</th></thead><tbody>`;
                    if (res.length === 0) {
                        code += "<td colspan='8'>진행 대기 문서가 없습니다</td>";
                    } else {
                        for (let i = 0; i < res.length; i++) {
                            code += `<td>\${res[i].elctrnSanctnEtprCode}</td>`;
                            code += `<td>\${res[i].elctrnSanctnSj}</td>`;
                            code += `<td>\${res[i].emplNm}</td>`;
                            code += `<td>\${res[i].elctrnSanctnRecomDate}</td>`;
                            code += `<td>\${res[i].commonCodeSanctProgrs}</td>`;
                            code += "</tr>";
                        }
                    }
                    code += "</tbody></table>";
                    $(".sanctionList").html(code);
                }
            })
        })

        // 결재 예정 문서
        $("#upcoming").on("click", function () {
            $.ajax({
                url: '/sanction/loadAwaiting?progrsCode=SANCTN016&emplId=' + '${CustomUser.employeeVO.emplId}',
                type: 'GET',
                success: function (res) {
                    let code = "<table border=1>";
                    code += `<thead><tr><th>문서번호</th>><th>결재양식</th><th>제목</th><th>기안일시</th><th>상태</th></thead><tbody>`;
                    if (res.length === 0) {
                        code += "<td colspan='8'>진행 대기 문서가 없습니다</td>";
                    } else {
                        for (let i = 0; i < res.length; i++) {
                            code += `<td>\${res[i].elctrnSanctnEtprCode}</td>`;
                            code += `<td>\${res[i].elctrnSanctnSj}</td>`;
                            code += `<td>\${res[i].emplNm}</td>`;
                            code += `<td>\${res[i].elctrnSanctnRecomDate}</td>`;
                            code += `<td>\${res[i].commonCodeSanctProgrs}</td>`;
                            code += "</tr>";
                        }
                    }
                    code += "</tbody></table>";
                    $(".sanctionList").html(code);
                }
            })
        })
    </script>

</sec:authorize>