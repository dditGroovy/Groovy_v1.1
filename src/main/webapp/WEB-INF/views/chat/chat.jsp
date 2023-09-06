<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js" integrity="sha512-1QvjE7BtotQjkq8PxLeF6P46gEpBRXuskzIVgjFpekzFVF4yjRgrQvTG1MTOJ3yQgvTteKAcO7DSZI92+u/yZw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js" integrity="sha512-iKDtgDyTHjAitUDdLljGhenhPwrbBfqTKWO1mkhSFH3A7blITC9MhYon6SjnMhp4o0rADGw9yAC6EW4t5a4K3g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

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

</div>

<script>
    // empListForChat 목록을 dept_name 값으로 그룹화
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

    // 그룹화된 결과를 화면에 렌더링
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
                    emplId : emplId,
                    emplNm : emplNm
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

    loadRoomList();

    function loadRoomList() {
        $.ajax({
            url: "/chat/loadRooms",
            type: "get",
            dataType: "json",
            success: function (result) {
                console.log("result : ", result)
                result.toString();
                code = "";
                $.each(result, function (idx, obj) {
                    code += `<button class="rooms">
                    <img src="/uploads/profile/\${obj.chttRoomThumbnail}" alt="\${obj.chttRoomThumbnail}"/>
                    <p>\${obj.chttRoomNm}</p>
                    <p>\${obj.latestChttCn}</p>
                    <input type="hidden" value="\${obj.chttRoomNo}">
                    </button>`;
                })
                $("#chatRoomList").html(code);
            },
            error: function (request, status, error) {

            }

        })
    }

    $("#chatRoomList").on("click", ".rooms", function() {
        let selectedRoom = $(this);
        let chttRoomNo = selectedRoom.find("input").val();
        console.log("선택한 채팅방 번호: ", chttRoomNo);
    });



</script>