<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<style>
    .file_box {
        border: 2px solid rgb(13 110 253 / 25%);
        border-radius: 10px;
        margin-top: 20px;
        padding: 40px;
        text-align: center;
    }
</style>
<h2>
    <a href="#">결재 요청</a>
    <a href="#">결재 진행함</a>
    <a href="#">개인 문서함</a>
</h2> <br/><br/>

<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="CustomUser"/>
    <div id="formCard">
        <div class="formHeader">
            <div class="btnWrap">
                <button id="getLine">결재선 지정</button>
                <div id="sanctionLine">
                    <%@include file="line/line.jsp" %>
                </div>
            </div>
            <br/>
            <div class="formTitle">
                    ${format.formatSj}
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
        let before = new Date();
        let year = before.getFullYear();
        let month = String(before.getMonth() + 1).padStart(2, '0');
        let day = String(before.getDate()).padStart(2, '0');
        const today = year + '-' + month + '-' + day;

        $(function () {
            $("#sanctionNo").html("${etprCode}");
            $("#writeDate").html(today);
            $("#writer").html("${CustomUser.employeeVO.emplNm}")
        });
        $(".submitLine").on("click", function () {
            console.log("윤하늘 바보")

        })


        // 파일 드래그 앤 드롭 (쓰려면 파일 처리 기능 추가해야 됨)
        const dropbox = document.querySelector('.file_box');
        const input_filename = document.querySelector('.file_name');

        //박스 안에 drag 하고 있을 때
        dropbox.addEventListener('dragover', function (e) {
            e.preventDefault();
            this.style.backgroundColor = 'rgb(13 110 253 / 25%)';
        });

        //박스 밖으로 drag가 나갈 때
        dropbox.addEventListener('dragleave', function (e) {
            this.style.backgroundColor = 'white';
        });

        //박스 안에 drop 했을 때
        dropbox.addEventListener('drop', function (e) {
            e.preventDefault();
            this.style.backgroundColor = 'white';

            //파일 이름을 text로 표시
            input_filename.innerHTML = e.dataTransfer.files[0].name;
        });
    </script>
</sec:authorize>