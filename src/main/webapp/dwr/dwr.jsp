<%--
 Created with IntelliJ IDEA.
 User: 徐立
 Date: 2017-11-27
 Time: 13:13
 To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/include.jsp" %>
<html>
<head>
    <title>chat room</title>
    <script type='text/javascript' src='${path}/dwr/engine.js'></script>
    <script type='text/javascript' src='${path}/dwr/interface/ChatService.js'></script>
    <script type='text/javascript' src='${path}/dwr/util.js'></script>
    <script type='text/javascript' src='${path}/js/dwr/dwr.js'></script>
</head>
<body onload="init();">
<ul id="chatlog"></ul>
<input id="text" onkeypress="dwr.util.onReturn(event, sendMessage)"/>
<input type="button" value="Send" onclick="sendMessage()"/>
<br/>
<a href="/index.jsp" target="_blank">index.jsp</a>
</body>
</html>
