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
    $("#submitBtn").on("click", function () {
        var form = $('#uploadForm')[0];
        var formData = new FormData(form);

        $.ajax({
            url: "/notice/inputNotice",
            type: 'POST',
            data: formData,
            dataType: 'text',
            contentType: false,
            processData: false,
            success: function (notiEtprCode) {
                console.log(notiEtprCode)
                let url = '/notice/noticeDetail?notiEtprCode=' + notiEtprCode;
                let content = `<div class="alarmBox">
                                  <a href="\${url}" class="aTag">
                                    <h1>[전체공지]</h1>
                                    <p>관리자로부터 전체 공지사항이 등록되었습니다.</p>
                                  </a>
                                  <button type="button" class="readBtn">읽음</button>
                                </div>`;
                let alarmVO = {
                    "ntcnUrl": url,
                    "ntcnCn": content,
                    "commonCodeNtcnKind": 'NTCN013'
                }
                $.ajax({
                    type: 'post',
                    url: '/alarm/insertAlarm',
                    data: alarmVO,
                    success: function(rslt) {
                        if (socket) {
                            let msg = "noti,"+url;
                            socket.send(msg);
                        }
                        location.href = "/notice/manageNotice";
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