<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>

<h2>
    <a href="#">결재 요청</a>
    <a href="#">결재 진행함</a>
    <a href="#">개인 문서함</a>
</h2> <br/><br/>
<div id="formCard">
    <div class="formHeader">
        <div class="btnWrap">
            <button id="getLine">결재선</button>
            <div id="sanctionLine">
                <%@include file="line/line.jsp" %>
            </div>
        </div>
        <br/>
        <div class="formTitle">
            연차 신청   <!--이것도 각자 끌어오면 좋게씀-->
        </div>
    </div>
    <div class="approvalWrap">
        <div class="approval">

        </div>
    </div>
    <div class="formContent">
        <!--결재양식 들어오는 영역-->

        <form action="#">
            <table style="width: 100%;" border="1">
                <colgroup>
                    <col style="width: 15%;">
                    <col style="width: 35%;">
                    <col style="width: 15%;">
                    <col style="width: 35%;">
                </colgroup>
                <tr>
                    <td class="form-title">문서 종류</td>
                    <td><span id="sanctnCate">연차</span></td>
                    <td class="form-title">문서 번호</td>
                    <td><span id="elctrnSanctnEtprCode">문서번호 자리</span></td>
                </tr>
                <tr>
                    <td class="form-title">기안 부서</td>
                    <td><span>회계팀</span></td>
                    <td class="form-title">기안자</td>
                    <td><span>윤하늘</span></td>
                </tr>
                <tr>
                    <td class="form-title">기안 일자</td>
                    <td><span>2023-09-02</span></td>
                    <td class="form-title">완료 일자</td>
                    <td><span>-</span></td>
                </tr>
                <tr>
                    <td class="form-title">제목</td>
                    <td colspan="3"><input type="text" name="elctrnSanctnSj" id="elctrnSanctnSj" style="width: 99%">
                    </td>
                </tr>
                <tr>
                    <td class="form-title">종류</td>
                    <td colspan="3">
                        <input type="radio" name="halfOfYearlyVacation" id="halfOfYearlyVacation1">
                        <label for="halfOfYearlyVacation1">오전 반차</label>
                        <input type="radio" name="halfOfYearlyVacation" id="halfOfYearlyVacation2">
                        <label for="halfOfYearlyVacation2">오후 반차</label>
                        <input type="radio" name="yearlyVacation" id="yearlyVacation">
                        <label for="yearlyVacation">종일</label>
                    </td>
                </tr>
                <tr>
                    <td class="form-title">시작 날짜</td>
                    <td>
                        <input type="date" name="startDate" id="startDate">
                    </td>
                    <td class="form-title">종료 일자</td>
                    <td>
                        <input type="date" name="endDate" id="endDate">
                    </td>
                </tr>
                <tr>
                    <td class="form-title">내용</td>
                    <td colspan="3">
                        <textarea name="elctrnSanctnCn" id="elctrnSanctnCn" style="width: 99%"></textarea>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <br/><br/>
    <button type="button">결재 제출</button>
</div>
<script>
    $("#getLine").on("click", function () {
        // $("#sanctionLine").prop("hidden", false);
        // 인사,HRT
        // 회계,AT
        // 영업,ST
        // 홍보,PRT
        // 총무,GAT
        // 경영자,CEO

        $.ajax({
            url: '/sanction/loadOrgChart',
            method: 'GET',
            contentType: "application/json;charset=utf-8",
            dataType: 'json',
            success: function (data) {
                data.forEach(function (employee) {
                    var employeeDiv = $('<div>');
                    employeeDiv.html(
                        `<input type="text" value= "\${employee.emplId}"/>` +
                        '이름' + employee.emplNm + '<br>' +
                        '부서: ' + employee.commonCodeDept + '<br>' +
                        '직급: ' + employee.commonCodeClsf);

                    if (employee.commonCodeDept === '인사') {
                        $('#hrt').append(employeeDiv);
                    } else if (employee.commonCodeDept === '홍보') {
                        $('#').append(employeeDiv);
                    }
                });
            },
            error: function (xhr, textStatus, error) {
                console.log("AJAX 오류:", error);
            }
        });
    })
</script>
