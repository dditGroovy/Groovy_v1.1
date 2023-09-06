<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    ul {list-style: none; padding-left: 0; }
    .wrap ul {display: flex; gap:10px}
    .header, .titleWrap {display: flex; align-items: center; gap: 10px;}
    table tr {display: flex;}
    table tr td,table tr th {flex: 1;}
    .carInfo ul {border: 1px solid black}
    .carInfo ul, .carInfo ul li {display: flex; align-items: center; gap: 20px;}
    .content > ul {display: flex; flex-direction: column;}
</style>
<div class="wrap">
    <ul>
        <li><a href="#" class="tab">차량 관리</a></li>
        <li><a href="#" class="tab">예약 현황</a></li>
    </ul>
</div>
<div class="cardWrap">
    <div class="card">
        <div class="header">
            <h3>오늘 예약 현황</h3>
            <p><a href="#" class="totalResve">3</a>건</p>
            <a href="#">더보기</a>
        </div>
        <div class="content">
            <table border=1 style="width: 100%">
                <thead>
                <tr>
                    <th>순번</th>
                    <th>차량 번호</th>
                    <th>시작 시간</th>
                    <th>끝 시간</th>
                    <th>예약 사원(사번)</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>5</td>
                    <td>14허8538</td>
                    <td>09:00</td>
                    <td>12:00</td>
                    <td>강서주(202308001)</td>
                    <td><button>반납 확인</button></td>
                </tr>
                <tr>
                    <td>4</td>
                    <td>14허8538</td>
                    <td>15:00</td>
                    <td>17:00</td>
                    <td>강서주(202308001)</td>
                    <td><button>반납 확인</button></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="card">
        <div class="header">
            <div class="titleWrap">
                <h3>등록된 차량</h3>
                <p><span>2</span>대</p>
            </div>
            <div class="btnWrap">
                <button><a href="#">차량 등록 +</a></button>
            </div>
        </div>
        <div class="content">
            <ul>
                <li>
                    <div class="carInfo">
                        <ul>
                            <li class="carInfoList">
                                <h4>레이</h4>
                                <span>14허8538</span>
                            </li>
                            <li class="carInfoList">
                                <h5>정원</h5>
                                <span>4명</span>
                            </li>
                            <li class="carInfoList">
                                <h5>하이패스</h5>
                                <span>부착</span>
                            </li>
                        </ul>
                    </div>
                </li>
                <li>
                    <div class="carInfo">
                        <ul>
                            <li class="carInfoList">
                                <h4>레이</h4>
                                <span>14허8538</span>
                            </li>
                            <li class="carInfoList">
                                <h5>정원</h5>
                                <span>4명</span>
                            </li>
                            <li class="carInfoList">
                                <h5>하이패스</h5>
                                <span>부착</span>
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>