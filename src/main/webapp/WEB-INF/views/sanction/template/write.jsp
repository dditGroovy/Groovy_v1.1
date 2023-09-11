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
                <div id="approvalLine">
                    <%@include file="../line/line.jsp" %>
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
            </tr>
        </div>
        <br/><br/>
        <button type="button" id="sanctionSubmit">결재 제출</button>
    </div>

    <script>
        let before = new Date();
        let year = before.getFullYear();
        let month = String(before.getMonth() + 1).padStart(2, '0');
        let day = String(before.getDate()).padStart(2, '0');


        let approver;
        let receiver;
        let referrer;

        <%--const etprCode = "${etprCode}";--%>
        const formatCode = "${format.commonCodeSanctnFormat}";
        const writer = "${CustomUser.employeeVO.emplNm}"
        const today = year + '-' + month + '-' + day;
        const title = "${format.formatSj}";
        let content;
        let file = $('#sanctionFile')[0].files[0];

        let formData = {
            title: opener.$("#title").val(),
            vacationKind: opener.$("input[name='commonCodeYrycUseKind']:checked + label").text(),
            vacationSe: opener.$("input[name='commonCodeYrycUseSe']:checked + label").text(),
            startDay: opener.$("#startDay").val(),
            endDay: opener.$("#endDay").val(),
            content: opener.$("#content").val()


        };
        // vacationCategory: opener.$("input[name='vacationCate']:checked").val(),
        // vacationType: opener.$("input[name='vacationType']:checked").val(),
        $(document).ready(function () {
            // $("#sanctionNo").html(etprCode);
            $("#writeDate").html(today);
            $("#writer").html(writer)
            $("#vacationKind").text(formData.vacationKind)
            $("#vacationSe").text(formData.vacationSe)
            $("#startDay").text(formData.startDay)
            $("#endDate").text(formData.endDay)
            $("#sanctionContent").text(content)

        });
        $(".submitLine").on("click", function () {
            approver = $("#sanctionLine input[type=hidden]").map(function () {
                return $(this).val();
            }).get();
            receiver = $("#receiveLine input[type=hidden]").map(function () {
                return $(this).val();
            }).get();
            referrer = $("#refrnLine input[type=hidden]").map(function () {
                return $(this).val();
            }).get();
            console.log(file);
        })
        $("#sanctionSubmit").on("click", function () {
            $("#sanctionContent").text("윤하늘 바보")
            content = $(".formContent").html();
            const jsonData = {
                approver: approver,
                receiver: receiver,
                referrer: referrer,
                etprCode: etprCode,
                formatCode: formatCode,
                writer: writer,
                today: today,
                title: title,
                content: content,
            };

            $.ajax({
                url: "/sanction/inputSanction",
                type: "POST",
                data: JSON.stringify(jsonData),
                contentType: "application/json",
                success: function (data) {
                    console.log("결재 제출 성공");
                    if (file != null) {
                        uploadFile();

                    }
                },
                error: function (xhr) {
                    console.log("결재 제출 실패");
                }
            });
        });

        function uploadFile() {
            let form = $('#sanctionFile')[0].files[0];
            let formData = new FormData();

            formData.append('file', form);

            $.ajax({
                url: `/file/upload/sanction/\${etprCode}`,
                type: "POST",
                data: formData,
                contentType: false, // 필수
                processData: false, // 필수
                success: function (data) {
                    console.log("결재 파일 업로드 성공");
                },
                error: function (xhr) {
                    console.log("결재 파일 업로드 실패");
                }
            });
        }
    </script>
</sec:authorize>