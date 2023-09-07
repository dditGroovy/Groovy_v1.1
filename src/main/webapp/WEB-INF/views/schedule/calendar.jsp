<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<link href="/resources/css/schedule/calendar.css" rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script src="/resources/fullcalendar/main.js"></script>
<script src="/resources/fullcalendar/ko.js"></script>
<head>
<title>Full Calendar</title>
<script>
$(document).ready(function(){		
	$(function(){
		var request = $.ajax({
			url : "/full-calendar/calendar-admin-update", // 값 불러오기
			method : "GET",
			dataType : "json"
		});
		
		request.done(function(data){
			console.log(data); // log로 데이터 찍어주기
			let calendarEl = document.getElementById('calendar');
			calendar = new FullCalendar.Calendar(calendarEl,{
				height : '700px',
				slotMinTime : '08:00', // Day 캘린더에서 시작 시간
				slotMaxTime : '20:00',  // Day 캘린더에서 종료 시간
				// 헤더에 표시할 툴바
				headerToolbar :{
					left : 'prev, next today',
					center : 'title',
					right : 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
				},
				initialView : 'dayGridMonth', // 초기 로드 될 때 보이는 캘린더 화면 (기본 설정 : 달)
				navLinks : true, // 날짜를 선택하면 Day 캘린더나 Week 캘린더로 링크
				editable : true, // 수정 가능?
				selectable : true, // 달력 일자 드래그 설정 가능
				droppable : true, // 드래그 앤 드롭 
				events : data,
				locale : 'ko', // 한국어 설정
				select: function(arg){ // 캘린더 이벤트를 생성할 수 있다

					let title = prompt('일정을 입력해주세요.');
				    if(title){
				    	calendar.addEvent({
				    		title : title,
				    		start : arg.start,
				    		end : arg.end,
				    		allDay : arg.allDay
				    	})
				    }else{
				    	 location.reload(); // 새로고침  
				    	 return;
				    }
				    
				    let events = new Array(); // Json 데이터를 받기 위한 배열 선언
				    let obj = new Object(); // Json을 담기 위해 Object 선언
				    
				    obj.title = title;
				    obj.start = arg.start; // 시작
				    obj.end = arg.end; // 끝
				    events.push(obj);
				    
				    let jsondata = JSON.stringify(events);
				    console.log(jsondata);
				    
				    $(function saveData(jsonData){
				    	$.ajax({
				    		url : "/full-calendar/calendar-admin-update",
				    		method : "POST",
				    		dataType : "json",
				    		data : JSON.stringify(events),
				    		contentType : 'application/json'
				    	});
				    	calendar.unselect()
				    });
				},
				
				//클릭해서 값 불러와서 수정하는 이벤트... 하나만 가져와야 돼 수인아 ^^ 가서 또 만들어와
				eventClick : function(info){

					$("#eventModal").modal("show");
						
					let scheduleId = info.event.id;
					
					$.ajax({
						url: "/full-calendar/calendar-admin-update/" + scheduleId,
				        method: "GET",
				        dataType: "json",
				        success: function (response) {
				            // 서버로부터 받은 스케줄 정보를 활용하여 모달 내용 업데이트
				            $("#eventTitle").val(response.schdulNm);
				            $("#eventStart").val(response.schdulBeginDate);
				            $("#eventEnd").val(response.schdulClosDate);
				        },
				        error: function (error) {
				            console.error("Error:", error);
				            // 오류 처리 로직을 추가하세요.
				        }
					});
					
					
					/*
					let events = new Array(); // JSON 데이터를 받기 위한 배열 선언
					let obj = new Object();
					    obj.title = info.event._def.title;
					    obj.start = info.event._instance.range.start;
					    events.push(obj);
					    
				    console.log(events);
				    $(function deleteData(){
				    	$.ajax({
				    		url : "/full-calendar/calendar-admin-update",
				    		method : "DELETE",
				    		dataType : "json",
				    		data : JSON.stringify(events),
				    		contentType : 'application/json;charset=utf-8',
				    	})
				    });
				    */
				} //eventClick 끝 
			});
			calendar.render();
		});
	});
});
</script>
</head>
<body>

	<!-- 모달 창 -->
	<div class="modal fade" id="eventModal" tabindex="-1" role="dialog"
		aria-labelledby="eventModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="eventModalLabel">이벤트 정보</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="eventForm">
						<div class="form-group">
							<label for="eventTitle"><strong>제목:</strong></label> <input
								type="text" class="form-control" id="eventTitle" name="title">
						</div>
						<div class="form-group">
							<label for="eventStart"><strong>시작 날짜:</strong></label> 
							<input type="text" class="form-control" id="eventStart" name="start">
						</div>
						<div class="form-group">
							<label for="eventEnd"><strong>종료 날짜:</strong></label> 
							<input type="text" class="form-control" id="eventEnd" name="end">
						</div>
						<!-- 기타 데이터를 표시할 수 있는 추가 인풋 필드를 여기에 추가할 수 있습니다. -->
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">닫기</button>
					<button type="button" class="btn btn-primary" id="saveEvent">저장</button>
				</div>
			</div>
		</div>
	</div>

	<div id="calendar"></div>


</body>
</html>