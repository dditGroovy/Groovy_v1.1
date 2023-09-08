<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/resources/ckeditor/ckeditor.js"></script>
<form action="/job/insertDiary" method="post" enctype="multipart/form-data">
  <table>
    <tr>
      <td>제목</td>
      <td><input type="text" name="jobDiarySbj" placeholder="제목을 입력해주세요." /></td>
    </tr>
    <tr>
      <td>등록일</td>
      <td id="today"></td>
      </td>
    </tr>
  </table>
  <textarea id="editor" name="jobDiaryCn"></textarea>
  <button type="button" id="goDiary">목록으로</button>
  <button type="submit">등록하기</button>
</form>
<script>
  CKEDITOR.replace("editor");

  let date = new Date();
  let year = date.getFullYear();
  let month = date.getMonth() + 1;
  month = month<10?month=`0\${month}`: month;
  let day = date.getDate();
  day = day<10?day=`0\${day}`: day;
  let todayString = `\${year}-\${month}-\${day}`;
  let today = document.querySelector("#today");
  today.innerHTML = todayString;
  let goDiary = document.querySelector("#goDiary");
  goDiary.addEventListener("click", function () {
    window.location.href = "/job/jobDiary";
  });

  //유효성 검사
  document.querySelector("form").addEventListener("submit", function (e) {
    let titleInput = document.querySelector("input[name='jobDiarySbj']");
    let contentTextarea = CKEDITOR.instances.text; // CKEditor의 내용 가져오기
    if (titleInput.value.trim() === "" || !contentTextarea.getData().trim()) {
      e.preventDefault();
      alert("제목과 내용을 모두 입력해주세요.");
    }
  });
</script>
