<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js"></script>

<div id="floating">
        <ul>
            <li><a href="${pageContext.request.contextPath}/humanResources/loadLog"><i class="icon i-job"></i></a></li>
            <li><a href="${pageContext.request.contextPath}/chat"><i class="icon i-chat"></i></a></li>
        </ul>
    </div>

<div id="AlarmDiv" style="border: 1px solid black">
    <h1>Alarm Test</h1>
    <div id="alarm" role="alarm" style="border: 1px solid black"></div>
</div>

<script>
    var socket = null;
    $(document).ready(function() {
        connectWs();
    });

    function connectWs() {
        //웹소켓 연결
        sock = new SockJS("<c:url value="/echo-ws"/>");
        socket = sock;

        sock.onopen = function () {
            console.log("info: connection opend");
        };

        sock.onmessage = function(event) {
            console.log(event.data);
            let $socketAlarm = $("#alarm");

            //handler에서 설정한 메시지 넣어준다.
            $socketAlarm.html(event.data);
        }

        sock.onclose = function () {
            console.log("close");
        }

        sock.onerror = function (err) {
            console.log("ERROR: ", err);
        }
    }
</script>