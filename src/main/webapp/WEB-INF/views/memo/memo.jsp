<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .memo {
        border: 1px solid red;
        width: calc((315 / 1920) * 100vw);
        height: calc((320 / 1080) * 100vh);
    }
    #inputMemo {
        display: flex;
        align-items: center;
        justify-content: center;
    }
    #memoLists {
        display: flex;
    }
</style>
</head>
<body>
<h1>메모장</h1>
<h4>나만의 메모 공간 &#x1F4AD;</h4>
<div class="memoWrap">
    <div id="inputMemo" class="memo">
        <button id="inputMemoBtn">
            메모 추가
        </button>
    </div>
    <div id="memoLists">
        <div id="appendMemo"></div>
        <div id="memoList">
        <c:forEach items="${memoList}" var="list">
            <div class="memo">
               <p>${list.memoSj}</p>
               <p>${list.memoCn}</p>
               <p>${list.memoWrtngDate}</p>
            </div>
         </c:forEach>
        </div>
    </div>
</div>

<script>
    const inputMemoBtn = document.querySelector("#inputMemoBtn");
    const memoLists = document.querySelector("#memoLists");
    let flug = true;
    inputMemoBtn.addEventListener("click",()=>{
        if(flug){
            const memoElem = document.createElement("div");
            memoElem.className = "memo";

            const memoTitle = document.createElement("input");
            memoTitle.type = "text";
            memoTitle.name = "memoSj";
            memoTitle.placeholder = "제목을 입력해주세요.";
            memoElem.appendChild(memoTitle);

            const memoCnt = document.createElement("textarea");

            memoCnt.name = "memoCn"
            memoCnt.placeholder = "내용을 입력해주세요.";

            memoElem.appendChild(memoCnt);
            const saveBtn = document.createElement("button");

            saveBtn.className = "savebtn";
            saveBtn.innerText = "저장";
            memoElem.appendChild(saveBtn);
            console.log(memoElem);

            document.querySelector("#appendMemo").append(memoElem);
            flug = false;
        }

    })
    memoLists.addEventListener("click",(e)=>{
        if(e.target.classList.contains("savebtn")){
            const target = e.target;
            
            const memoElem = target.parentElement;
            const memoTitleInput = memoElem.querySelector('input[name="memoSj"]');
            const memoContentTextarea = memoElem.querySelector('textarea[name="memoCn"]');
            
            const memoSj = memoTitleInput.value;
            const memoCn = memoContentTextarea.value;
            
            const memoData = {
            		memoSj: memoSj,
            		memoCn: memoCn
            	};
            
            $.ajax({
                    url: "/memo/memoMain",
                    type: "POST",
                    data: JSON.stringify(memoData),
                    contentType: "application/json;charset=UTF-8",
                    success:function(data){
	                    		if(data=="success") {
	                               location.href=location.href;                    			
	                    		}
	                    		else {
	                    			alert("메모 추가를 실패했습니다");
	                    		}
                            },
                    error: function (request, status, error) {
                          console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
                        }            

        })
            flug = true;
        
        }
    })
   

</script>
