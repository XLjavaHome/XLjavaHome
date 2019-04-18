<%--
  Created by IntelliJ IDEA.
  User: 徐立
  Date: 2017/10/23
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.xl.com/common/tag" prefix="xl" %>
<link href="css/xl.css" type=text/css rel=stylesheet>
<script type="text/javascript" src="${contextPath}/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/juicer.js"></script>
<%
    request.setAttribute("path", request.getContextPath());
    request.setAttribute("contextPath", request.getContextPath());
%>
