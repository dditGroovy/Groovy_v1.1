<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="CustomUser"/>
<h1>팀 커뮤니티</h1>
<h4>인사팀만을 위한 공간입니다.</h4>

<h2>포스트 등록</h2>
<form action="${pageContext.request.contextPath}/teamCommunity/inputPost" method="post" enctype="multipart/form-data">
    <table border="1">
        <tr>
            <th>글 내용</th>
            <td><textarea name="sntncCn" id="sntncCn" cols="50" rows="10"></textarea></td>
        </tr>
        <tr>
            <th>글 파일첨부</th>
            <td> <input type="file" name="postFile" id="postFile"><br /></td>
        </tr>
    </table>
    <button id="insertPostBtn">등록</button>
</form>
<hr /><br />
<h2>포스트 불러오기</h2>
<form>
    <table border="1" style="width: 90%;">
        <tr>
            <th>글번호</th>
            <th>사원 이름</th>
            <th>등록일</th>
            <th>포스트 내용</th>
            <th>좋아요</th>
            <th>수정/삭제</th>
        </tr>
        <!--<tr>
            <td><input type="text" name="getSntncEtprCode" id="getSntncEtprCode" value="1"></td>
            <td><input type="text" name="getSntncWritingEmplId" id="getWritingEmplId" value="강서주"></td>
            <td><input type="text" name="getSntncWritingDate" id="getWritingDate" value="2023/08/08 21:42:00"></td>
            <td><textarea name="getSntncCn" id="getSntncCn" cols="50" rows="10">Lorem ipsum dolor sit amet consectetur. In malesuada sed vitae pharetra id. Cras cong</textarea></td>
            <td>0</td>
            <td>0</td>
            <td>
                <button type="button" id="modifyBtn">수정</button>
                <button type="button" id="deleteBtn">삭제</button>
            </td>
        </tr>-->
        <c:forEach var="sntncVO" items="${sntncList}">
        <tr>
            <td>${sntncVO.sntncEtprCode}</td>
            <td>${sntncVO.emplNm}</td>
            <td>${sntncVO.sntncWritingDate}</td>
            <td>${sntncVO.sntncCn}</td>
            <td>${sntncVO.recomendCnt}</td>
            <td>${sntncVO.sntncWritingEmplId}</td>
            <c:if test="${sntncVO.sntncWritingEmplId} == ${CustomUser.employeeVO.emplNm}" var="emplId" scope="session">
            <td>
                <button type="button" id="modifyBtn">수정</button>
                <button type="button" id="deleteBtn">삭제</button>
            </td>
            </c:if>
        </tr>
        </c:forEach>
    </table>
    <hr /><br />
    <h2>포스트에 등록한 댓글 불러오기</h2>
    <table border="1" style="width: 80%;">
        <thead>
        <tr>
            <td>글 번호</td>
            <td>댓글 번호</td>
            <td>댓글 내용</td>
            <td>댓글남긴 날짜</td>
            <td>댓글남긴 사원</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>1</td>
            <td>1</td>
            <td>Lorem ipsum dolor sit amet consectetur. In malesuada sed vitae pharetra id. Cras cong</td>
            <td>2023/08/27</td>
            <td>이혜진</td>
        </tr>
        <tr>
            <td>1</td>
            <td>2</td>
            <td>Lorem ipsum dolor sit amet consectetur. In malesuada sed vitae pharetra id. Cras cong</td>
            <td>2023/08/27</td>
            <td>이혜진</td>
        </tr>
        </tbody>
    </table>
</form>
<hr /><br />
<h2>댓글 등록하기</h2>
<form action="#" method="post">
    <table>
        <tr>
            <th>글 전사적코드</th>
            <td><input type="text" name="answerSntncEtprCode" id="answerSntncEtprCode"></td>
        </tr>
        <tr>
            <th>댓글 작성 사원 아이디</th>
            <td><input type="text" name="answerWrtingEmplId" id="answerWrtingEmplId"></td>
        </tr>
        <tr>
            <th>댓글 내용</th>
            <td><textarea name="answerCn" id="answerCn" cols="50" rows="10"></textarea></td>
        </tr>
        <tr>
            <th>댓글 날짜</th>
            <td><input type="text" name="answerDate" id="answerDate"></td>
        </tr>
    </table>
    <button>댓글 등록하기</button>
</form>
<br /><hr />
<h2>좋아요 누르깅</h2>
<form action="#" method="get">
    <table>
        <tr>
            <th>글 전사적 코드</th>
            <td><input type="text" name="recomendSntncEtprCode" id="recomendSntncEtprCode"></td>
        </tr>
        <tr>
            <th>추천 사원 아이디</th>
            <td><input type="text" name="recomendEmplId" id="recomendEmplId"></td>
        </tr>
        <tr>
            <th>추천 사원 여부 구분</th>
            <td><input type="text" name="commonCodeRecomendAt" id="commonCodeRecomendAt" value="RECOMEND011"></td>
        </tr>
    </table>
    <button type="button">좋아요 누르기</button>
</form>
<br /><hr />

<h2>투표 추가하기 <= 누르면 추가하기 모달 밑에 추가</h2>
<button type="button" id="addVote">투표 추가하기</button>
<div id="modal-insert-vote" style="display: none;">
    <form action="#">
        <label for="voteRegistTitle">투표 제목</label> <br />
        <input type="text" name="voteRegistTitle" id="voteRegistTitle"> <br />
        <label for="insertOption">옵션 추가</label>
        <button type="button" id="insertOption">옵션 추가 +</button>
        <div class="options">
            <div class="option">
                <input type="text" name="voteOption1" id="voteOption1">
                <label for="voteOption1"></label>
                <button type="button" class="deleteOption">옵션 삭제</button>
            </div>
            <div class="option">
                <input type="text" name="voteOption2" id="voteOption2">
                <label for="voteOption2"></label>
                <button type="button" class="deleteOption">옵션 삭제</button>
            </div>
        </div>
        <label for="voteRegistStartDate">시작날짜</label>
        <input type="date" name="voteRegistStartDate" id="voteRegistStartDate">
        <label for="voteRegistEndDate">마감날짜</label>
        <input type="date" name="voteRegistEndDate" id="voteRegistEndDate"> <br />
        <button type="button" id="insertVote">투표 등록</button>
    </form>
</div>
<br /><hr />
<h2>진행중인 투표</h2>
<table border="1" style="width: 50%;" id="voteRegistId" class="voteList">
    <thead>
    <tr>
        <th>투표등록 번호 </th>
        <td>
            <input type="text" name="vote1"  value="VOTE_REGIST_SEQ 값" style="width: 90%;" readonly> </td> <!-- name -> VOTE_REGIST_SEQ -->
    </tr>
    <tr>
        <th>투표 제목</th>
        <td> <input type="text" name="vote1RegistTitle" value="회식 뭐먹을까요?"  style="width: 90%;" readonly> </td>
    </tr>
    <tr>
        <th>투표 시작일 </th>
        <td>
            <input type="text" name="vote1RegistStartDate"  value="2023-08-23"  style="width: 90%;" readonly>
        </td>
    </tr>
    <tr>
        <th>투표 종료일 </th>
        <td>
            <input type="text" name="vote1RegistEndtDate" id="vote1RegistEndtDate" value="2023-08-28"  style="width: 90%;" readonly>
        </td>
    </tr>
    <tr>
        <th>투표 등록 사원 아이디 </th>
        <td>
            <input type="text" name="vote1RegistEmpId" id="vote1RegistEmpId" value="20230827"  style="width: 90%;" readonly>
        </td>
    </tr>
    <!-- 만약에 등록 사원 아이디와 로그인한 사원 아이디가 일치할 때 노출-->
    <tr>
        <th>등록 사원 아이디와 로그인 아이디가 일치할 때 노출 =></th>
        <td>
            <button type="button" class="modifyVote">수정</button>
            <button type="button" class="updateVote" style="display: none">저장</button>
            <button type="button" class="deleteVote">삭제</button>
        </td>
    </tr>
    </thead>
    <tbody>
    <tr>
        <!-- input name에 투표 항목 번호가 들어오게  -->
        <td>등록된 옵션</td>
        <td><div class="optionWrap">
            <input type="radio" name="voteRegist1Option1" id="voteRegist1Option1" disabled checked>
            <label for="voteRegist1Option1">삼겹살</label>
            <input type="radio" name="voteRegist1Option2" id="voteRegist1Option2" disabled>
            <label for="voteRegist1Option1">마라탕</label>
            <input type="radio" name="voteRegist1Option3" id="voteRegist1Option3" disabled>
            <label for="voteRegist1Option3">찐옥수수</label>
            <input type="radio" name="voteRegist1Option4" id="voteRegist1Option4" disabled>
            <label for="voteRegist1Option4">곱창</label>
        </div></td>
    </tr>
    <tr>
        <td>투표 인원 수</td>
        <td>
            <ul>
                <li>삼겹살 : 1 (명)</li>
                <li>마라탕 : 1 (명)</li>
                <li>찐옥수수 : 2 (명)</li>
                <li>곱창 : 4 (명)</li>
            </ul>
        </td>
    </tr>

    </tbody>
</table>
<br /><hr />
<h2>투표하기</h2>
<form action="#" method="post">
    <table>
        <tr>
            <th>투표참여사원아이디</th>
            <td><input type="text" name="voteParcpinEmpId" id="voteParcpinEmpId"></td>
        </tr>
        <tr>
            <th>투표등록번호</th>
            <td><input type="text" name="parcpinVoteRegistSeq" id="parcpinVoteRegistSeq"></td>
        </tr>
        <tr>
            <th>투표항목번호</th>
            <td><input type="text" name="parcpinVoteOptionSeq" id="parcpinVoteOptionSeq"></td>
        </tr>
    </table>
    <button type="button">투표하기</button>
</form>
<!--
<div id="modifyVoteModal" class="modal" style="display: none">
<div class="modal-content">
<h3>수정 모달창</h3>
<form action="#">
<label for="modifyVoteTitle">투표 제목</label> <br />
<input type="text" name="modifyVoteTitle" id="modifyVoteTitle"> <br />
<label for="modifyOption">옵션 추가</label>
<button type="button" id="modifyOption">옵션 추가 +</button>
<div class="modifyOptions"></div>
<label for="modifyStartVodeDate">시작날짜</label>
<input type="date" name="modifyStartVodeDate" id="modifyStartVodeDate">
<label for="modifyEndVodeDate">마감날짜</label>
<input type="date" name="modifyEndVodeDate" id="modifyEndVodeDate"> <br />
<button type="button" id="updateVote">투표 수정</button>
</form>
</div>
</div>-->
<br /><hr />
<h2>팀 공지</h2>
<button type="button" id="addTeamNotice">팀 공지 추가하기</button>
<div id="modal-insert-notice">
    <form action="#" enctype="application/x-www-form-urlencoded">
        <label for="notiSntncEtprCode">글 전사적 코드</label> <br />
        <input type="text" name="notisntncEtprCode" id="notisntncEtprCode"> <br />
        <label for="notisntncWritingEmplId">글작성 사원아이디</label> <br />
        <input type="text" name="notisntncWritingEmplId" id="notisntncWritingEmplId"> <br />
        <label for="notisntncSj">공지 제목</label> <br />
        <input type="text" name="notisntncSj" id="notisntncSj"> <br />
        <label for="notisntncCn">공지 내용</label><br />
        <textarea name="notisntncCn" id="notisntncCn" cols="30" rows="10"></textarea><br />
        <input type="file" name="noticeFile" id="noticeFile"><br />
        <label for="notisntncSj">글종류구분</label> <br />
        <select name="noticommonCodeSntncCtgry" id="noticommonCodeSntncCtgry">
            <option value="SNTNC010">공지</option>
        </select>
    </form>
    <button type="button" id="insertNotice">등록</button>
</div>
</sec:authorize>
<br /><hr />
<script>
    document.addEventListener("DOMContentLoaded", () => {
        const addVoteBtn = document.querySelector("#addVote");
        const postInput = document.querySelector("#postContent");
        const fileInput = document.querySelector("#postFile");
        const voteTitle = document.querySelector("#voteRegistTitle");
        const insertPostBtn = document.querySelector("#insertPost");
        const insertOptionBtn = document.querySelector("#insertOption");
        const insertVoteBtn = document.querySelector("#insertPostBtn");
        const voteRegistStartDate = document.querySelector("#voteRegistStartDate");
        const sntncSj = document.querySelector("#sntncSj");
        const sntncCn = document.querySelector("#sntncCn");
        const insertNotice = document.querySelector("#insertNotice");
        const noticeFile = document.querySelector("#noticeFile");
        const modifyVoteBtn = document.querySelector(".modifyVote");
        const deleteVoteBtn = document.querySelector("#deleteVoteBtn");
        let deleteOptionBtn = document.querySelectorAll(".deleteOption");
        let optionContainers = document.querySelectorAll(".option");
        const options = document.querySelector(".options");
        let voteList = document.querySelectorAll(".voteList");

        let formData = undefined;
        let selectedFile = undefined;
        let num = 2;

        console.log(num);

        addVoteBtn.addEventListener("click", () => {
            document.querySelector("#modal-insert-vote").style.display = "block";
        })
        insertVoteBtn.addEventListener("click", () => {
            const voteVO = {
                "voteRegistTitle" : voteTitle.value,
                "voteRegistStartDate" : voteRegistStartDate.value,
                "voteRegistEndDate" : voteRegistEndDate.value
            }
            // 알아서 쓰시오
            /* $.ajax({

            }) */
            document.querySelector("#modal-insert-vote").style.display = "none";
        })

        insertNotice.addEventListener("click",()=>{
            const noticeTitle = sntncSj.value;
            const noticeContent = sntncCn.value;
            formData = new FormData();
            formData.append("noticeTitle", noticeTitle);
            formData.append("noticeContent", noticeContent);

            selectedFile = noticeFile.files[0];
            formData.append("file", selectedFile);

            if(noticeTitle == "" ||noticeContent == ""){
                alert("모든 항목을 입력해주세요.")
                return;
            }

            /* $.ajax({
                // 입력하세요.
            }) */
            document.querySelector("#modal-insert-notice").style.display = "none";
        })

        /*  const getVoteList = () => {
            $.ajax({
                type: "get",
                // url: "",
                dataType: "json",
                success: function (res) {
                    console.log("체크 : ",res);
                    //form 에 class voteModifyForm 넣어주세용
                },
                error: function (xhr, status, error) {
                    console.log("code: " + xhr.status);
                    console.log("message: " + xhr.responseText);
                    console.log("error: " + error);
                }
            });
        }
        getVoteList(); */

        const forms = document.querySelectorAll('.voteRegistId');
        forms.forEach(form => {
            console.log(form);
            const modifyVoteBtn = document.querySelector(".modifyVote");

            form.addEventListener("click", function(event) {
                console.log(event.target)
                if (event.target === modifyVoteBtn) {
                    if (form.style.display != "none") {
                        form.style.display = "none";
                        document.querySelector(".modifyRegist").style.display = "block";
                    }
                }
            });
        })

        /* updateVoteBtn.addEventListener("click",() => {

        }) */

        /*deleteOptionBtn.forEach(btn => {
            const thisParent = btn.parentNode;
            thisParent.addEventListener("click", function(event){
                event.target.remove();
                num = optionContainers.length;
                console.log(event.target, num);
            })

        })*/
        options.addEventListener("click",(event) => {
            console.log(event.target);
            if(event.target.classList.contains("deleteOption")){
                const thisParnt = event.target.closest(".option");
                if(thisParnt){
                    options.removeChild(thisParnt);
                    num = options.children.length;
                    console.log("num : " + num , "options.children : " + options.children);
                }
            }
        });
        insertOptionBtn.addEventListener("click",() => {

            if(num >= 5) {
                alert("옵션은 5개까지 가능합니다.")
                return;
            }
            num++;
            console.log(num);


            let newDiv = document.createElement("div");
            newDiv.className = "option";

            let newInput = document.createElement("input");
            newInput.type = "text";
            newInput.name = num;
            newInput.id = num;

            let newLabel = document.createElement("label");
            newLabel.htmlFor = newInput.id;
            newLabel.textContent = "";

            let newBtn = document.createElement("button");
            newBtn.textContent = "옵션 삭제";
            newBtn.classList = "deleteOption";

            newDiv.appendChild(newInput);
            newDiv.appendChild(newLabel);
            newDiv.appendChild(newBtn);

            options.appendChild(newDiv);
        })

        /* 등록된 투표 수정*/
        document.addEventListener("click",function(event){
            if(event.target.classList.contains("modifyVote")){
                const table = event.target.closest("table");
                const inputText = table.querySelectorAll("input[type=text]");
                const inputRadio = table.querySelectorAll("input[type=radio]");

                event.target.style.display = "none";
                event.target.nextElementSibling.style.display = "block";

                inputText.forEach(item => {
                    item.readOnly = false;
                })
                inputRadio.forEach(item => {
                    item.disabled = false;
                })

            };
            if(event.target.classList.contains("updateVote")){
                event.target.style.display = "none";
                event.target.previousElementSibling.style.display = "block";

                const table = event.target.closest("table");
                const inputText = table.querySelectorAll("input[type=text]");
                const inputRadio = table.querySelectorAll("input[type=radio]");

                inputText.forEach(item => {
                    item.readOnly = true;
                })
                inputRadio.forEach(item => {
                    item.disabled = true;
                })
            }
        })
        /*voteList.forEach(vote => {
            vote.addEventListener("click",event => {
                const title = document.querySelector("input[name=vote1]");
                const startDate = document.querySelector("input[name=vote1RegistStartDate]");
                const endDate = document.querySelector("input[name=vote1RegistEndDate]");

                for (let i = 1; i < ; i++) {

                }
                console.log(event.target);
                if(event.target.classList.contains("modifyVote")){
                    document.querySelector("#modifyVoteModal").style.display = "block";
                };
            })

        })*/
        updateVote.addEventListener("click",()=>{
            document.querySelector("#modifyVoteModal").style.display = "none";
        })
        document.querySelector("#addTeamNotice").addEventListener("click",() => {
            document.querySelector("#modal-insert-notice").style.display = "block";
        })
    })
</script>