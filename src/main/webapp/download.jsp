<%@ page language="java" import="com.tz.util.DownUtil,java.util.HashMap" pageEncoding="utf-8" %>
<%@ page import="java.util.List" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Java开发大数据批量抓取系统 - Arry老师</title>
    <meta name="keywords" content="关键词,关键词">
    <meta name="description" content="">
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
        }

        body {
            background: url("images/bg.jpg");
            background-size: cover;
            font-size: 12px;
            font-family: "微软雅黑";
            color: #666;
        }

        /*down start*/
        .down {
            width: 600px;
            margin: 60px auto;
        }

        .down .d-title {
            font-size: 22px;
            font-weight: 300;
            text-align: center;
            color: #fff;
            padding-bottom: 30px;
        }

        .down .d-txt {
            width: 470px;
            height: 36px;
            border: none;
            outline: none;
            padding-left: 10px;
            font-size: 14px;
            border-radius: 3px 0 0 3px;
        }

        .down .d-btn {
            width: 110px;
            height: 36px;
            background: #3c0;
            border: none;
            color: #fff;
            outline: none;
            cursor: pointer;
            border-radius: 0 3px 3px 0;
        }

        .down .d-btn:hover {
            background: #269900;
        }

        /*end down*/

        /*files start*/
        .files {
            width: 1000px;
            margin: 0 auto;
        }

        .files ul li {
            list-style: none;
            width: 132px;
            background: #fff;
            padding: 5px;
            float: left;
            margin: 12px;
        }

        .files ul li:hover {
            background: #e2e2e2;
        }

        .files ul li p {
            text-align: center;
            line-height: 24px;
        }

        /*end files*/
    </style>
</head>
<body>
<%
    // 获取文本框的值
    String url = request.getParameter("url");
    String encoding = "gbk";
    if (null != url) {
        // 解析图像方法
        List<HashMap<String, String>> list = DownUtil.findImages(url, encoding);
        // 存储到作用域中
        pageContext.setAttribute("img", list);
    }
%>
<!--down start-->
<form action="down.jsp" method="post">
    <div class="down">
        <h1 class="d-title">Java开发大数据搜索引擎完整版</h1>
        <div class="d-box">
            <input type="text" name="url" class="d-txt" placeholder="请输入网址 ..." value="http://tech.qq.com/"/><input type="submit" class="d-btn" value="开始下载"/>
        </div>
    </div>
</form>
<!--end down-->
<!--files start-->
<div class="files">
    <ul>
        <c:forEach items="${img}" var="image">
            <li>
                <a href="#">
                    <img src="${image.src}" alt="缩略图" width="132" height="132"/>
                </a>
                <p class="f-title">arry老师</p>
                <p class="f-name">缩略图</p>
            </li>
        </c:forEach>
    </ul>
</div>
<!--end files-->
</body>
</html>