<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="/resources/ckeditor/ckeditor.js"></script>
<button type="button" id="goWrite">업무 일지 작성</button>
${list}
<c:choose>
    <c:when test="${empty list}">
        <p>데이터가 존재하지 않습니다.</p>
    </c:when>
    <c:otherwise>
        <table border="1">
            <tr>
                <td>번호</td>
                <td>제목</td>
                <td>등록자</td>
                <td>등록일</td>
            </tr>
            <c:forEach var="list" items="${list}" varStatus="stat">
                <tr>
                    <td>${stat.index + 1}</td>
                    <td><p onclick="goRead(this)">${list.jobDiarySbj}</p></td>
                    <td>${list.jobDiaryWrtingEmplNm}</td>
                    <td><c:out value="${list.jobDiaryReportDate.substring(0, 10)}" /></td>
                    <td style="display: none">${list.jobDiaryWrtingEmplId}</td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>

<script>
    let goWrite = document.querySelector("#goWrite");
    goWrite.addEventListener("click", function () {
        window.location.href = "/job/write";
    });

    //업무 등록 실패했을시
    let error = `${error}`;
    if (error != "") {
        alert(error);
    }

    function goRead(readBtn) {
        let date = readBtn.closest("td").nextElementSibling.nextElementSibling.textContent;
        let id = readBtn.closest("td").nextElementSibling.nextElementSibling.nextElementSibling.textContent;
        window.location.href = "/job/read?date=" + date + "&id=" + id;
    }

</script>