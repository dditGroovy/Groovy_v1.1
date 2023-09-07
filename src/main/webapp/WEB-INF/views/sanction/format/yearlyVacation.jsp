<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Title</title>
</head>
<body>
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
                <td><span id="sanctnCate">연차사용신청서</span></td>
                <td class="form-title">문서 번호</td>
                <td><span id="sanctionNo"></span></td>
            </tr>
            <tr>
                <td class="form-title">기안 부서</td>
                <td><span>인사팀</span></td>
                <td class="form-title">기안자</td>
                <td><span id="writer"></span></td>
            </tr>
            <tr>
                <td class="form-title">기안 일자</td>
                <td><span id="writeDate"></span></td>
                <td class="form-title">완료 일자</td>
                <td><span id="finishDate">-</span></td>
            </tr>
            <tr>
                <td class="form-title">제목</td>
                <td colspan="3"><input type="text" class="sanctionTitle" style="width: 99%"></td>
                <td colspan="3" class="sanctionTitle"></td>
            </tr>
            <tr>
                <td class="form-title">종류</td>
                <td class="vacationType" style="display: none;"></td>
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
                <td class="startDay" style="display: none;"></td>
                <td>
                    <input type="date" name="startDate" id="startDate">
                </td>
                <td class="form-title">종료 일자</td>
                <td class="endDay" style="display: none;"></td>
                <td>
                    <input type="date" name="endDate" id="endDate">
                </td>
            </tr>
            <tr>
                <td class="form-title">내용</td>
                <td class="vacationContent" style="display: none;"></td>
                <td colspan="3">
                    <textarea name="elctrnSanctnCn" id="elctrnSanctnCn" style="width: 99%"></textarea>
                </td>
            </tr>
        </table>



    </form>
</body>
</html>