<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <ul>
        <li><a href="#">내 휴가</a></li>
        <li><a href="#">내 급여</a></li>
        <li><a href="#">휴가 기록</a></li>
    </ul>
</header>
<main>
    <h1>휴가 신청</h1>
    <div>
        <form action="#">
            <table border="1">
                <tr>
                    <th>제목</th>
                    <td>
                        <input type="text" name="title" id="title" placeholder="제목">
                    </td>
                </tr>
                <tr>
                    <th>휴가 구분</th>
                    <td>
                        <input type="radio" name="vacationCate" value="연차" id="vacation1">
                        <label for="vacation1">연차</label>

                        <input type="radio" name="vacationCate" value="공가 - 생일" id="vacation2">
                        <label for="vacation2">공가 - 생일</label>

                        <input type="radio" name="vacationCate" value="공가 - 결혼" id="vacation3">
                        <label for="vacation3">공가 - 결혼</label>

                        <input type="radio" name="vacationCate" value="공가 - 경조사" id="vacation4">
                        <label for="vacation4">공가 - 경조사</label>

                        <input type="radio" name="vacationCate" value="병가" id="vacation5">
                        <label for="vacation5">병가</label>
                    </td>
                </tr>
                <tr>
                    <th>종류</th>
                    <td>
                        <input type="radio" name="vacationType" id="morning">
                        <label for="morning">오전 반차</label>
                        <input type="radio" name="vacationType" id="afternoon">
                        <label for="afternoon">오후 반차</label>
                        <input type="radio" name="vacationType" id="allDay">
                        <label for="allDay">종일</label>
                    </td>
                </tr>
                <tr>
                    <th>기간</th>
                    <td>
                        <input type="text" name="startDay" id="startDay" placeholder="시작 날짜"> ~
                        <input type="text" name="endDay" id="endDay" placeholder="끝 날짜">
                    </td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td>
                        <textarea name="content" id="content" cols="30" rows="10" placeholder="내용"></textarea>
                    </td>
                </tr>
            </table>
            <button type="button" id="save">저장하기</button>
            <button type="button" id="insertSanction" hidden="hidden">결재하기</button>
        </form>
    </div>
</main>
<script>
    $("#save").on("click", function () {
        $("#insertSanction").prop("hidden", false)
    })

    $("#insertSanction").on("click", function () {
        let formData = {
            title: $("#title").val(),
            vacationCategory: $("input[name='vacationCate']:checked").val(),
            vacationType: $("input[name='vacationType']:checked").val(),
            startDay: $("#startDay").val(),
            endDay: $("#endDay").val(),
            content: $("#content").val()
        };
        let childWindow = window.open("/sanction/write/DEPT010?format=SANCTN_FORMAT011", "결재", "width = 1200, height = 1200")
        childWindow.dataFromParent = formData;

        /*
         $.ajax({
             url: '/sanction/approve',
             type: 'POST',
             data: JSON.stringify({
                 approvalType: 'kr.co.groovy.sanction.AnnualLeaveService',
                 methodName: 'viewSanction',
                 parameters: formData
             }),
             contentType: 'application/json',
             success: function (data) {
                 console.log("결재 insert 성공");
                 console.log(data)
                 let childWindow = ;
                 childWindow.dataFromParent = data;


             },
             error: function (error) {
                 console.log("결재 insert 실패");
             }
         });*/
    });
</script>