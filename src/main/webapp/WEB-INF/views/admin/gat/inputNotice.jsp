<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="#" method="post"
      enctype="multipart/form-data" id="uploadForm">
    <label for="noti-title">공지 제목</label>
    <input type="text" name="notiTitle" id="noti-title" required><br/>
    <label for="noti-content">공지 내용</label>
    <textarea cols="50" rows="10" name="notiContent" id="noti-content" required></textarea><br>
    <label for="noti-category">카테고리</label>
    <select name="notiCtgryIconFileStreNm" id="noti-category">
        <option value="important.png">중요</option>
        <option value="notice.png">공지</option>
        <option value="event.png">행사</option>
        <option value="obituary.png">부고</option>
    </select>
    <br>
    <label for="noti-file">파일 첨부</label>
    <input type="file" name="notiFiles" id="noti-file" multiple><br/>
    <button type="button" id="submitBtn">등록</button>
</form>
<script>
    let content = $("#noti-content");

    //보내는 사원 아이디, 알림내용, 알림 날짜, 공통코드알림종류 코드
    <sec:authorize access="isAuthenticated()">
        <sec:authentication property="principal" var="CustomUser"/>
        let emplId = `${CustomUser.employeeVO.emplId}`;
        let emplNm = `${CustomUser.employeeVO.emplNm}`;
    </sec:authorize>

    $("#submitBtn").on("click", function () {
        var form = $('#uploadForm')[0];
        var formData = new FormData(form);

        $.ajax({
            url: "/generalAffairs/inputNotice",
            type: 'POST',
            data: formData,
            dataType: 'text',
            contentType: false,
            processData: false,
            success: function (notiEtprCode) {
                let url = '/common/noticeDetail?notiEtprCode=' + notiEtprCode;
                let alarmVO = {
                    "ntcnCn": content.val(),
                    "commonCodeNtcnKind": 'NTCN013'
                }
                $.ajax({
                    type: 'post',
                    url: '/common/insertAlarm',
                    data: alarmVO,
                    success: function(rslt) {
                        if (socket) {
                            let msg = "noti,"+url;
                            socket.send(msg);
                        }
                        location.href = "/generalAffairs/manageNotice";
                    },
                    error: function (xhr) {
                        console.log(xhr.status);
                    }
                })
            },
            error: function (xhr) {
                console.log(xhr.status)
            }
        })
    });
</script>