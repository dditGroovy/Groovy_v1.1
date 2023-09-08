<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="/resources/ckeditor/ckeditor.js"></script>
<button type="button" id="goWrite">업무 일지 작성</button>

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
                    <td onclick="diaryDetail(this)">${list.jobDiarySbj}</td>
                    <td>${list.jobDiaryWrtingEmplId}</td>
                    <td><fmt:formatDate value="${list.jobDiaryReportDate}" pattern="yyyy-MM-dd" /></td>
                    <td style="display: none">${list.jobDiaryCn}</td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>

<div id="detail" style="display: none;">
    <h2>업무 일지 (상세)</h2>
    <table border="1">
        <tr>
            <td>제목</td>
            <td id="subject"></td>
        </tr>
        <tr>
            <td>작성자</td>
            <td id="writer"></td>
        </tr>
        <tr>
            <td>작성 날짜</td>
            <td id="date"></td>
        </tr>
        <tr>
            <td>내용</td>
            <td><textarea name="editor" id="editor"></textarea></td>
        </tr>
    </table>
</div>



<script>
    CKEDITOR.replace('editor');
    $("#editor").attr("readOnly",true);
    window.onload = function () {
        document.querySelector("#cke_1_top").style.display = "none";
        document.querySelector("#cke_1_bottom").style.display = "none";
    }

    let goWrite = document.querySelector("#goWrite");
    goWrite.addEventListener("click", function () {
        window.location.href = "/job/write";
    });

    //업무 등록 실패했을시
    let error = `${error}`;
    if (error != "") {
        alert(error);
    }

    function diaryDetail(listItem) {
        let subject = listItem.textContent;
        let writer = listItem.nextElementSibling.textContent;
        let date = listItem.nextElementSibling.nextElementSibling.textContent;
        let content = listItem.nextElementSibling.nextElementSibling.nextElementSibling.innerHTML;

        document.querySelector("#subject").textContent = subject;
        document.querySelector("#writer").textContent = writer;
        document.querySelector("#date").textContent = date;
        CKEDITOR.instances.editor.setData(content);

        document.querySelector("#detail").style.display = "block";
    }
</script>