<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    #dclzNav > ul {display: flex;gap: 48px;}
    #myGrid {width: 100%; height: calc((360 / 1080) * 100vh);}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js"></script>
<script defer src="https://unpkg.com/ag-grid-community/dist/ag-grid-community.min.js"></script>
<body>
<header>
    <h1><a href="#">근태 관리</a></h1>
    <nav id="dclzNav">
        <ul>
            <li><a href="${pageContext.request.contextPath}/employee/manageDclzAll">전체</a></li>
            <li><a href="#">인사팀</a></li>
            <li><a href="#">총무팀</a></li>
            <li><a href="#">회계팅</a></li>
            <li><a href="#">영업팀</a></li>
            <li><a href="#">홍보팀</a></li>
        </ul>
    </nav>
</header><br /><br /><br />
<main>
    <div id="chartsJsWrap">
        <div class="allDepartment"></div>
        <div class="avgDepartment"></div>
    </div>
    <input type="text" oninput="onQuickFilterChanged()" id="quickFilter" placeholder="검색어를 입력하세요"/>
    <div id="myGrid" class="ag-theme-alpine"></div>
</main>

<script>
    /*예시*/
    const getMedalString = function (param) {
        const str = `${param} `;
        return str;
    };
    const MedalRenderer = function (params) {
        return getMedalString(params.value);
    };
    function onQuickFilterChanged() {
        gridOptions.api.setQuickFilter(document.getElementById('quickFilter').value);
    }
    const columnDefs = [
        { field: "emplId",  headerName:"사원번호",getQuickFilterText: (params) => {
                return getMedalString(params.value);
            }},
        { field: "emplNm",  headerName:"이름"},
        { field: "commonCodeDept", headerName:"부서", filter: "agMultiColumnFilter"},
        { field: "commonCodeCrsf", headerName:"직급"},
        { field: "defaulWorkDate", headerName:"소정근무일수"},
        { field: "realWorkDate", headerName:"실제근무일수"},
        { field: "defaulWorkTime", headerName:"소정근무시간"},
        { field: "realWorkTime", headerName:"실제근무시간"},
        { field: "overWorkTime", headerName:"총 연장 근무시간"},
        { field: "totalWorkTime", headerName:"총 근무시간"},
        { field: "avgWorkTime", headerName:"평균 근무시간"},
    ];
    const rowData = [
        { emplId: "202308001",emplNm: "최서연", commonCodeDept: "인사팀", commonCodeCrsf: "대리", defaulWorkDate: 5, realWorkDate : 5, defaulWorkTime : "40:00", realWorkTime: "24:00",  overWorkTime: 0, totalWorkTime : "24:00",avgWorkTime: "03:26" },
        { emplId: "202308001",emplNm: "최서연", commonCodeDept: "총무팀", commonCodeCrsf: "대리", defaulWorkDate: 5, realWorkDate : 5, defaulWorkTime : "40:00", realWorkTime: "24:00",  overWorkTime: 0, totalWorkTime : "24:00",avgWorkTime: "03:26" },
        { emplId: "202308001",emplNm: "최서연", commonCodeDept: "회계팀", commonCodeCrsf: "대리", defaulWorkDate: 5, realWorkDate : 5, defaulWorkTime : "40:00", realWorkTime: "24:00",  overWorkTime: 0, totalWorkTime : "24:00",avgWorkTime: "03:26" },
        { emplId: "202308001",emplNm: "최서연", commonCodeDept: "홍보팀", commonCodeCrsf: "대리", defaulWorkDate: 5, realWorkDate : 5, defaulWorkTime : "40:00", realWorkTime: "24:00",  overWorkTime: 0, totalWorkTime : "24:00",avgWorkTime: "03:26" },
        { emplId: "202308001",emplNm: "최서연", commonCodeDept: "영업팀", commonCodeCrsf: "대리", defaulWorkDate: 5, realWorkDate : 5, defaulWorkTime : "40:00", realWorkTime: "24:00",  overWorkTime: 0, totalWorkTime : "24:00",avgWorkTime: "03:26" },
        { emplId: "202308001",emplNm: "최서연", commonCodeDept: "인사팀", commonCodeCrsf: "대리", defaulWorkDate: 5, realWorkDate : 5, defaulWorkTime : "40:00", realWorkTime: "24:00",  overWorkTime: 0, totalWorkTime : "24:00",avgWorkTime: "03:26" },
        { emplId: "202308001",emplNm: "최서연", commonCodeDept: "총무팀", commonCodeCrsf: "대리", defaulWorkDate: 5, realWorkDate : 5, defaulWorkTime : "40:00", realWorkTime: "24:00",  overWorkTime: 0, totalWorkTime : "24:00",avgWorkTime: "03:26" },
        { emplId: "202308001",emplNm: "최서연", commonCodeDept: "회계팀", commonCodeCrsf: "대리", defaulWorkDate: 5, realWorkDate : 5, defaulWorkTime : "40:00", realWorkTime: "24:00",  overWorkTime: 0, totalWorkTime : "24:00",avgWorkTime: "03:26" },
        { emplId: "202308001",emplNm: "최서연", commonCodeDept: "인사팀", commonCodeCrsf: "대리", defaulWorkDate: 5, realWorkDate : 5, defaulWorkTime : "40:00", realWorkTime: "24:00",  overWorkTime: 0, totalWorkTime : "24:00",avgWorkTime: "03:26" }
    ];
    const gridOptions = {
        columnDefs: columnDefs,
        rowData: rowData
    };
    document.addEventListener('DOMContentLoaded', () => {
        const gridDiv = document.querySelector('#myGrid');
        new agGrid.Grid(gridDiv, gridOptions);
    });


    /* 실제 ajax grid 위 코드 참고 */
    const MainGrid = function(){
        let $this = this;

        /* grid 영역 정의 */
        this.gridDiv = "myGrid";

        /* grid 칼럼 정의 */
        this.getColumnDefs = function(){
            var columnDefs = [
                {field: "emplNm",  headerName:"이름", width: 150, minWidth:120, pinned:'left', suppressSizeToFit: true, suppressMenu: true, sort: 'asc'},
                {field: "commonCodeDept", headerName:"부서", width: 90, minWidth: 50, maxWidth: 100, pinned:'left', suppressMenu: true, suppressSorting : true},
                {field: "commonCodeCrsf", headerName:"직급", width: 120},
                {field: "defaulWorkDate", headerName:"소정근무일수"},
                {field: "realWorkDate", headerName:"실제근무일수"},
                {field: "defaulWorkTime", headerName:"소정근무시간"},
                {field: "overWorkTime", headerName:"총 연장 근무시간"},
                {field: "totalWorkTime", headerName:"총 근무시간"},
                {field: "avgWorkTime", headerName:"평균 근무시간"}
            ];
            let gridOpt = CommonGrid.getDefaultGridOpt(columnDefs);
            return gridOpt;
        };
        let mainGrid = new MainGrid();

        document.addEventListener('DOMContentLoaded', function() {
            var httpRequest = new XMLHttpRequest();
            httpRequest.open('GET', 'url');
            httpRequest.send();
            httpRequest.onreadystatechange = function() {
                if (httpRequest.readyState === 4 && httpRequest.status === 200) {
                    var httpResult = JSON.parse(httpRequest.responseText);
                    mainGrid.makeGrid(httpResult);
                }
            };
        });
    }
</script>