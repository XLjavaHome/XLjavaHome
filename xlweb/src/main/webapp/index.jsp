<%--
  Created by IntelliJ IDEA.
  User: 立
  Date: 4/30 0030
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="include/include.jsp" %>
<xl:page clazz="com.xl.web.action.IndexAction"/>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function gToPath(path) {
//            location.href = PATH;
            window.open(path);
        }
    </script>
</head>
<body>
<h1>hello World</h1>
<c:forEach items="${filePath}" var="file">
    <%--onclick="gToPath('${file.value}')"--%>
    <a href="${file.value}" target="_blank">${file.key}</a><br/>
</c:forEach>
</body>
</html>
