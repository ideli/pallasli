<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Performance</title>
    <script type="text/javascript" src="../pallasli-web-js-css/scripts/echarts-2.0.0/asset/js/esl/esl.js"></script>
</head>
<body>
    <div id="main" style="width:100%;height:500px;border:1px solid green"></div>
    <div>
        <input id="round" value="5"/>次
        <input id="count" value="2000"/>个数据
        <select id='chart'>
            <option value='line'>line</option>
            <option value='bar'>bar</option>
            <option value='scatter'>scatter</option>
            <option value='k'>k</option>
            <option value='pie'>pie</option>
            <option value='radar'>radar</option>
            <option value='chord'>chord</option>
            <option value='map'>map</option>
        </select>
        <button id='run'>start</button>
        <button id='auto-run'>auto start</button>
    </div>
    <div id="res">loading</div>
    <script src="echarts2.0/option.js" type="text/javascript"></script>
    <script src="echarts2.0/p.js" type="text/javascript"></script>
</body>
</html>