<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

// 인사,HRT
// 회계,AT
// 영업,ST
// 홍보,PRT
// 총무,GAT
// 경영자,CEO
<div id="">
        <div id="hrt">
            <ul class="depth1 active">
                <li class="department nav-list"><a href="#" class="active">인사팀  <i class="icon i-arr-bt"></i></a></li>
                <ul>
                    <li class="nav-list"><a href="#"><i class="icon i-sanction"></i >결재 관리</a></li>
                </ul>
            </ul>
        </div>
        <div class="gat">
            <ul class="depth1">
                <li class="department nav-list"><a href="#">총무팀 <i class="icon i-arr-bt"></i></a></li>
                <ul>
                    <li class="nav-list"><a href="#" ><i class="icon i-sanction"></i>결재 관리</a></li>
                </ul>
            </ul>
        </div>
        <div class="at">
            <ul class="depth1">
                <li class="department nav-list"><a href="#">회계팀  <i class="icon i-arr-bt"></i></a></li>
                <ul>
                    <li class="nav-list"><a href="#"><i class="icon i-sanction"></i>결재 관리</a></li>

                </ul>
            </ul>
        </div>
    검색<input type="text" id="searchLine"/>
    <button type="button">검색</button>
    <p>검색 결과</p>
    <div name="result">

    </div>
    <hr>
    <button type="button">추가</button>
    <button type="button">제거</button>
    <hr>
    <p>결재 순서</p>
    <div id="sanctionLine">

    </div>
</div>
</body>
</html>
