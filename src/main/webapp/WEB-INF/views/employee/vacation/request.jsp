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
            <table  border="1">
                <tr>
                    <th>제목</th>
                    <td>
                        <input type="text" name="" id="" placeholder="제목">
                    </td>
                </tr>
                <tr>
                    <th>휴가 구분</th>
                    <select name="approvalType"></select>
                    <td>
                        <input type="radio" name="vacationCate" id="">
                        <label for="">연차</label>
                        <input type="radio" name="vacationCate" id="">
                        <label for="">공가 - 생일</label>
                        <input type="radio" name="vacationCate" id="">
                        <label for="">공가 - 결혼</label>
                        <input type="radio" name="vacationCate" id="">
                        <label for="">공가 - 경조사</label>
                        <input type="radio" name="vacationCate" id="">
                        <label for="">병가</label>
                    </td>
                </tr>
                <tr>
                    <th>종류</th>
                    <td>
                        <input type="radio" name="vacationType" id="">
                        <label for="">오전 반차</label>
                        <input type="radio" name="vacationType" id="">
                        <label for="">오후 반차</label>
                        <input type="radio" name="vacationType" id="">
                        <label for="">종일</label>
                    </td>
                </tr>
                <tr>
                    <th>기간</th>
                    <td>
                        <input type="text" name="" id="" placeholder="시작 날짜"> ~
                        <input type="text" name="" id="" placeholder="끝 날짜">
                    </td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td>
                        <textarea name="" id="" cols="30" rows="10" placeholder="내용"></textarea>
                    </td>
                </tr>
            </table>
            <button>저장하기</button>
        </form>
    </div>
</main>
<script>
    var inputData = {
        key1: $('#inputField1').val(),
        key2: $('#inputField2').val()
    };

    var jsonData = JSON.stringify(inputData);

    $.ajax({
        url: '/your-controller-mapping',
        type: 'POST',
        contentType: 'application/json', // 전송하는 데이터의 타입
        data: jsonData, // JSON 데이터를 전송
        success: function (data) {
            // 서버에서 반환한 응답 데이터 처리
        },
        error: function (error) {
            // AJAX 요청 실패 시 실행될 코드
        }
    });
</script>