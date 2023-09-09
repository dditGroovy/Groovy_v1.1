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
