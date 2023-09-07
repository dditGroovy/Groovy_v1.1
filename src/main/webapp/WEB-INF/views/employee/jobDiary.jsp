<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<button type="button" id="goWrite">업무 일지 작성</button>

<table border="1">
    <tr>
        <td>번호</td>
        <td>제목</td>
        <td>등록자</td>
        <td>등록일</td>
    </tr>
<%--    <c:forEach var="" items="" varStatus="">--%>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
<%--    </c:forEach>--%>
</table>

<script>
    let goWrite = document.querySelector("#goWrite");
    goWrite.addEventListener("click", function () {
        window.location.href = "/job/write";
    });

</script>