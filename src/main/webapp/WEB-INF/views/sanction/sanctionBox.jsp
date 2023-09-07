<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <h2>
        <a href="#">결재 요청함</a>
        <a href="#">결재 진행함</a>
        <a href="#">개인 문서함</a>
    </h2> <br /><br />

    <ul>
        <li><span>진행중인 결재</span> <a href="#">05</a>건</li>
        <li><span>완료된 결재</span> <a href="#">03</a>건</li>
        <li><span>반려된 결재</span> <a href="#">01</a>건</li>

    </ul>

    <hr /><br />
    <h3>결재 목록</h3>
    <ul>
        <li><a href="${pageContext.request.contextPath}/sanction/write/DEPT010?format=SANCTN_FORMAT011">연차</a></li>

        <li><a href="${pageContext.request.contextPath}/sanction/write/DEPT010?format=SANCTN_FORMAT013">여름 휴가</a></li>
        <li><a href="${pageContext.request.contextPath}/sanction/write/DEPT010?format=SANCTN_FORMAT013">생일</a></li>
        <li><a href="${pageContext.request.contextPath}/sanction/write/DEPT010?format=SANCTN_FORMAT013">결혼 - 신혼여행</a></li>
        <li><a href="${pageContext.request.contextPath}/sanction/write/DEPT010?format=SANCTN_FORMAT013">경조사</a></li>
        <li><a href="${pageContext.request.contextPath}/sanction/write/DEPT010?format=SANCTN_FORMAT013">병가</a></li>

        <li><a href="${pageContext.request.contextPath}/sanction/write/DEPT011?format=SANCTN_FORMAT010">법인카드 신청</a></li>
    </ul>