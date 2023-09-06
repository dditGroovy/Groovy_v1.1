<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1><a href="/facility/meeting">회의실 예약</a></h1>
<h1><a href="/facility/rest">자리 예약</a></h1>
<h1><a href="/facility/vehicle">차량 예약</a></h1>

<!-- 반복 -->
<c:forEach var="meetingRoom" items="${meetingRoomList}" varStatus="">
  <button type="button" onclick="setRoomNumber(this)">
    <i></i> <!-- 아이콘 -->
    <h3 class="no">${meetingRoom.commonCodeFcltyKind}</h3> <!-- 회의실 번호 -->
    <h4>인원</h4>
    <p><span>${meetingRoom.fcltyPsncpa}</span>명</p> <!-- 인원 -->
    <h4>비품</h4>
    <!-- 비품 반복 -->
    <p>
      <span>프로젝터</span>
    </p>
  </button>
</c:forEach>
<hr />

<h2>내 예약 현황</h2>
<table border="1">
  <tr>
    <td>번호</td>
    <td>회의실</td>
    <td>예약 시간</td>
    <td>요청 사항</td>
  </tr>
  <tr>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
</table>

<hr />
<form action="">
  <!-- 회의실 클릭시 회의실 번호가 name값으로 들어옴 -->
  <input type="hidden" name="facltyNo" id="facltyNo"/>
  <p id="today"></p>
  <p id="time"></p>
  <span>오전</span>
  <button type="button">9:00</button>
  <button type="button">11:00</button>

  <span>오후</span>
  <button type="button">01:00</button>
  <button type="button">03:00</button>
  <button type="button">05:00</button>
  <button type="button">07:00</button>

  <h3>요청사항</h3>
  <input type="text" name="" value="" placeholder="비품 등 요청 사항을 적어주세요 :)"/>

  <div>
    <p><i></i>가능</p>
    <p><i></i>불가능</p>
  </div>

  <button type="button">예약하기</button>
</form>

<script>
  //날씨
  let today = document.querySelector("#today");

  const currentDate = new Date();
  const month = currentDate.getMonth() + 1;
  const day = currentDate.getDate();
  const daysOfWeek = ["일", "월", "화", "수", "목", "금", "토"];
  const dayOfWeek = daysOfWeek[currentDate.getDay()];

  let todayCode = `${month}.${day}(${dayOfWeek})`;
  today.innerText = todayCode;

  //회의실 번호
  function setRoomNumber(room) {
    roomNo = $(room).find(".no").html();
    $("#facltyNo").attr("value", roomNo);
  }
</script>