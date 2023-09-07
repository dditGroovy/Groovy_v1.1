<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="/job/insertJob" method="post" enctype="multipart/form-data">
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
  <textarea name="jobDiaryCn" id="editor"></textarea>
  <button type="button" id="goDiary">목록으로</button>
  <button type="submit">등록하기</button>
</form>

<script src="https://cdn.ckeditor.com/ckeditor5/39.0.1/classic/ckeditor.js"></script>
<script>
  ClassicEditor.create(document.querySelector("#editor"));
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
</script>
