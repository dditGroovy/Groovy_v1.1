<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>

<h2>
    <a href="#">결재 요청</a>
    <a href="#">결재 진행함</a>
    <a href="#">개인 문서함</a>
</h2> <br/><br/>
<p>
    private String commonCodeSanctnFormat;
    private String formatSanctnKnd;
    private String formatSj;
    private String formatCn; // CLOB 타입
    private String formatUseAt;
</p>
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
            ${format.formatSj}   <!--이것도 각자 끌어오면 좋게씀-->
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
    $(function () {
        $("#elctrnSanctnEtprCode").html("윤하늘 바보")
    });
</script>
