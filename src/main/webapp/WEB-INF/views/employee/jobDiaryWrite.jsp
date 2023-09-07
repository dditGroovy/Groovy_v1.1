<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="">
  <table>
    <tr>
      <td>제목</td>
      <td><input type="text" name="jobDiarySbj" placeholder="제목을 입력해주세요." /></td>
    </tr>
    <tr>
      <td>등록일</td>
      <td>2023-09-07</td>
    </tr>
  </table>
  <textarea name="jobDiaryCn" id="editor"></textarea>
  <button type="button">목록으로</button>
  <button type="submit">등록하기</button>
</form>

<script>
  ClassicEditor.create(document.querySelector("#editor"));
</script>
