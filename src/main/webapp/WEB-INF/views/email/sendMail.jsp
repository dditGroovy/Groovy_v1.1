<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/common.css">
    <style>
        input[type=text] {width: 100%; height: 30px}
        textarea {width: 100%; height: 200px;}
        .writeWrap {margin-top: 20px;}
    </style>
</head>
<body>
    <div class="contentWrap">
        <form action="#" method="post">
            <table border="1" style="width: 50%;>
                <tr>
                    <th>보내는 사람</th>
                    <td>
                        <input type="text" name="emplId" id="emplId">
                    </td>
                </tr>
                <tr>
                    <th>받는 사람</th>
                    <td>
                        <input type="text" name="emailRecpinEmplId" id="emailRecpinEmplId">
                    </td>
                </tr>
                <tr>
                    <th>참조</th>
                    <td>
                        <input type="text" name="emplId" id="emplId">
                    </td>
                </tr>
                <tr>
                    <th>제목</th>
                    <td>
                        <input type="text" name="emailSj" id="emailSj">
                    </td>
                </tr>
                <tr>
                    <th>파일첨부</th>
                    <td>
                        <input type="file" name="file" id="file">
                    </td>
                </tr>
            <tr>
                <th>내용</th>
                <td>
                    <textarea name="emailCn" id="emailCn"></textarea>
                </td>
            </tr>
            </table>
            <div class="serviceWrap">
                <div class="serviceWrap">
                    <div class="writeWrap">
                        <button type="button" id="sendBtn">보내기</button>
                        <button type="button" id="saveBtn">임시저장</button>
                        <button type="button" id="resveBtn">예약</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</body>
</html>