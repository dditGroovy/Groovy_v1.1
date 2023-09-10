<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication property="principal" var="CustomUser"/>

<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

<style>
    div {
        border : 1px solid black;
    }

    .rooms {
        cursor: pointer;
    }

</style>
<hr>
<h1>채팅방 개설</h1>
<div>
    <ul id="employeeList">
        <%--        <c:forEach items="${empListForChat}" var="employee">--%>
        <%--            <li>--%>
        <%--                <label>--%>
        <%--                    <input type="checkbox" name="selectedEmpls" value="${employee.emplId}/${employee.emplNm}"--%>
        <%--                           data-emplNm="${employee.emplNm}">--%>
        <%--                        ${employee.deptNm}부 ${employee.emplNm} ${employee.clsfNm}--%>
        <%--                </label>--%>
        <%--            </li>--%>
        <%--        </c:forEach>--%>
    </ul>
    <button type="button" id="createRoomBtn">채팅방 생성</button>
</div>

<hr>

<h1>채팅방 목록</h1>
<div id="chatRoomList">
    <%--    <c:forEach items="${chatRoomList}" var="chatRoom">--%>
    <%--        <div>--%>
    <%--            <img src="/uploads/profile/${chatRoom.chatRoomThumbnail}" alt="${chatRoom.chatRoomThumbnail}"/>--%>
    <%--            <p>${chatRoom.chttRoomNm}</p>--%>
    <%--            <p>${chatRoom.latestChat}</p>--%>
    <%--            <input type="hidden" value="${chatRoom.chttRoomNo}">--%>
    <%--        </div>--%>
    <%--    </c:forEach>--%>
</div>

<hr>

<h1>채팅창</h1>
<div id="chatRoom">
    <div id="msgArea">

    </div>
    <input type="text" id="msg" class="form-control">
    <button type="button" id="button-send">전송</button>
</div>

<script>

    const emplId = ${CustomUser.employeeVO.emplId};
    const emplNm = "${CustomUser.employeeVO.emplNm}";
    const msg = $("#msg");
    const chatRoomMessages = {};

    let sockJS = new SockJS("/chat");
    let client = Stomp.over(sockJS);

    let currentSubRoom; // 현재 구독 중인 채팅방
    let currentRoomNo; // 현재 들어가 있는 채팅방 번호

    let isScrolled = false;
    let isEnd = false;

    function connectToStomp() {
        return new Promise(function(res, rej) {
            client.connect({}, function() {
                console.log("stomp 연결 확인");
                res();
            });
        });
    }

    connectToStomp().then(function() {
        $("#button-send").on("click", function(){
            let message = msg.val();
            let date = new Date();
            console.log(emplId + ":" + message);
            let chatVO = {
                chttNo : 0,
                chttRoomNo : chttRoomNo,
                chttMbrEmplId : emplId,
                chttMbrEmplNm : emplNm,
                chttCn : message,
                chttInputDate : date
            }
            client.send('/public/chat/message', {}, JSON.stringify(chatVO));

            console.log("msgData : ", chatVO);
            $.ajax({
                url: "/chat/inputMessage",
                type: "post",
                data: JSON.stringify(chatVO),
                contentType: "application/json;charset:utf-8",
                success: function () {

                },
                error: function (request, status, error) {
                    alert("채팅 전송 실패")
                    console.log("code: " + request.status)
                    console.log("message: " + request.responseText)
                    console.log("error: " + error);
                }
            })
            msg.val('');
        });

        function enterRoom(currentRoomNo) {
            console.log("currentRoomNo : ", currentRoomNo);
            $("#msgArea").html('');
            $("#msgArea").html(`<div class="myroom" id="room\${currentRoomNo}" style="border: 2px solid green"></div>`)

            $.ajax({
                url: `/chat/loadRoomMessages/\${currentRoomNo}`,
                type: "get",
                dataType: "json",
                success: function (messages) {
                    console.log(messages);
                    alert("채팅 왔음")

                    code = "";
                    $.each(messages, function (idx, obj) {
                        if(obj.chttMbrEmplId == emplId) {
                            code = "<div style='border: 1px solid blue' id='\${obj.chttNo}'>";
                            code += "<div>";
                            code += `<p>\${obj.chttMbrEmplNm} : \${obj.chttCn}</p>`;
                            code += "</div></div>";
                            $(`#room\${currentRoomNo}`).append(code);
                        } else {
                            code = "<div style='border: 1px solid red' id='\${obj.chttNo}'>";
                            code += "<div>";
                            code += `<p>\${obj.chttMbrEmplNm} : \${obj.chttCn}</p>`;
                            code += "</div></div>";
                            $(`#room\${currentRoomNo}`).append(code);
                        }
                    });
                },
                error: function (request, status, error) {
                    alert("채팅 로드 실패")
                    console.log("code: " + request.status)
                    console.log("message: " + request.responseText)
                    console.log("error: " + error);
                }
            })
            msg.val('');
        }


        $("#chatRoomList").on("click", ".rooms", function() {
            let selectedRoom = $(this);
            let chttRoomNo = selectedRoom.find("input").val();

            currentRoomNo = chttRoomNo;

            enterRoom(currentRoomNo);
        });

        function subscribeToChatRoom(chttRoomNo) {
            client.subscribe("/subscribe/chat/room/" + chttRoomNo, function (chat) {
                console.log("message : ", chat);
                let content = JSON.parse(chat.body);

                let chttRoomNo = content.chttRoomNo;
                console.log("subscribeToChatRoom chttRoomNo : ", chttRoomNo);
                let chttMbrEmplId = content.chttMbrEmplId;
                let chttMbrEmplNm = content.chttMbrEmplNm;
                let chttCn = content.chttCn;
                let chttInputDate = content.chttInputDate;
                console.log("chttMbrEmplId : ", chttMbrEmplId);
                console.log("chttMbrEmplNm : ", chttMbrEmplNm);
                console.log("latestInputDate : ", latestInputDate);

                let code = "";
                if (chttMbrEmplId == emplId) {
                    code += "<div style='border: 1px solid #0000cc'>";
                    code += "<div>";
                    code += "<b>" + chttMbrEmplNm + " : " + chttCn + "</b>";
                    code += "</div></div>";
                    $(`#room\${chttRoomNo}`).append(code);
                } else {
                    code += "<div style='border: 1px solid red'>";
                    code += "<div>";
                    code += "<b>" + chttMbrEmplNm + " : " + chttCn + "</b>";
                    code += "</div></div>";
                    $(`#room\${chttRoomNo}`).append(code);
                }

                updateLatestChttCn(chttRoomNo, chttCn, chttInputDate);
                updateChatRoomList(chttRoomNo, chttCn, chttInputDate);
            });
        }

        function updateLatestChttCn(chttRoomNo, chttCn, chttInputDate) {
            for (let i = 0; i < chatRoomList.length; i++) {
                if (chatRoomList[i].chttRoomNo === chttRoomNo) {
                    chatRoomList[i].latestChttCn = chttCn;
                    chatRoomList[i].latestInputDate = chttInputDate;
                    break;
                }
            }
            renderChatRoomList();
        }

        function updateChatRoomList(chttRoomNo, latestChttCn, chttInputDate) {
            let chatRoom = $("#chatRoomList" + chttRoomNo);
            console.log("updateChatRoomList chatRoom : ", chatRoom);
            chatRoom.find("#latestChttCn").text(latestChttCn);
            chatRoom.find("#latestInputDate").text(chttInputDate);
        }

        loadRoomList();

        function loadRoomList() {
            $.ajax({
                url: "/chat/loadRooms",
                type: "get",
                dataType: "json",
                success: function (result) {
                    console.log("result : ", result)
                    result.sort(function (a, b) {
                        return new Date(b.latestInputDate) - new Date(a.latestInputDate);
                    });

                    chatRoomList = result;

                    if (currentSubRoom) {
                        currentSubRoom.unsubscribe();
                    }

                    for (let i = 0; i < chatRoomList.length; i++) {
                        const chttRoomNo = chatRoomList[i].chttRoomNo;
                        subscribeToChatRoom(chttRoomNo);
                    }

                    renderChatRoomList();
                },
                error: function (request, status, error) {
                    // 오류 처리 코드를 추가할 수 있습니다.
                }
            })
        }


        let chatRoomList = [];

        function renderChatRoomList() {
            $("#chatRoomList").html(''); // 초기화

            chatRoomList.forEach(room => room.latestInputDate = new Date(room.latestInputDate));
            chatRoomList.sort((a, b) => b.latestInputDate - a.latestInputDate);

            code = "";
            $.each(chatRoomList, function (idx, obj) {
                code += `<button class="rooms" id="chatRoom\${obj.chttRoomNo}">
            <img src="/uploads/profile/\${obj.chttRoomThumbnail}" alt="\${obj.chttRoomThumbnail}"/>
            <p>\${obj.chttRoomNm}</p>
            <p id="latestChttCn">\${obj.latestChttCn}</p>
            <p id="latestInputDate">\${obj.latestInputDate}</p>
            <input type="hidden" value="\${obj.chttRoomNo}">
            </button>`;
            });

            $("#chatRoomList").html(code);
        }

        var groupedEmployees = {};

        <c:forEach items="${empListForChat}" var="employee">
        var deptNm = "${employee.deptNm}";
        if (!groupedEmployees[deptNm]) {
            groupedEmployees[deptNm] = [];
        }
        groupedEmployees[deptNm].push({
            emplId: "${employee.emplId}",
            emplNm: "${employee.emplNm}",
            clsfNm: "${employee.clsfNm}"
        });
        </c:forEach>

        var ul = $("#employeeList");
        for (var deptNm in groupedEmployees) {
            var li = $("<li>").text(deptNm);
            ul.append(li);

            var ulSub = $("<ul>");
            groupedEmployees[deptNm].forEach(function(employee) {
                var liSub = $("<li>");
                var label = $("<label>");
                var input = $("<input>").attr({
                    type: "checkbox",
                    name: "selectedEmpls",
                    value: employee.emplId + "/" + employee.emplNm
                }).data("emplNm", employee.emplNm);
                label.append(input);
                label.append(document.createTextNode(employee.emplNm + " " + employee.clsfNm));
                liSub.append(label);
                ulSub.append(liSub);
            });
            li.append(ulSub);
        }

        $("#createRoomBtn").click(function () {
            let roomMemList = [];

            $("input[name='selectedEmpls']:checked").each(function () {
                let selectedEmpls = $(this).val()
                let splitResult = selectedEmpls.split("/");

                if (splitResult.length === 2) {
                    let emplId = splitResult[0];
                    let emplNm = splitResult[1];

                    let EmployeeVO = {
                        emplId: emplId,
                        emplNm: emplNm
                    };

                    roomMemList.push(EmployeeVO);
                }
            });

            $.ajax({
                url: "/chat/createRoom",
                type: "post",
                data: JSON.stringify(roomMemList),
                contentType: "application/json;charset:utf-8",
                success: function () {
                    loadRoomList();
                    alert("채팅방 개설 성공");
                },
                error: function (request, status, error) {
                    alert("채팅방 개설 실패")
                    console.log("code: " + request.status)
                    console.log("message: " + request.responseText)
                    console.log("error: " + error);
                }
            });
        });

    });
</script>