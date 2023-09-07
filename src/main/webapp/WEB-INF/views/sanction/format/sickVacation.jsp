<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>병가신청</h3>
    <form action="#">
        <table border="1" style="width: 100%">
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
                <td colspan="3"><input type="text" name="elctrnSanctnSj" id="elctrnSanctnSj" style="width: 99%"></td>
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
</body>
</html>

