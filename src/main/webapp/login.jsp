<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="error ${param.error == true ? '' : 'hide'}">
    登陆失败<br>
    ${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}
</div>
<form action="${pageContext.request.contextPath}/j_spring_security_check" method="post"
      style="width:260px;text-align:center;">
    <fieldset>
        <legend>登陆</legend>
        用户： <input type="text" name="username " style="width:150px;" value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}"/><br />
        密码： <input type="password" name="password " style="width:150px;" /><br />
        <input type="checkbox" name="remember_me " />两周之内不必登陆
        <br />
        <input type="submit" value="登陆"/>
        <input type="reset" value="重置"/>
    </fieldset>
</form>
</body>
</html>
