<!DOCTYPE html>
<html lang="en">
<#assign ctx=request.getContextPath()>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="${ctx}/static/js/jquery-3.3.1.js"></script>
    <script>
        $(function () {
            $("#login").click(function(){
                $.ajax({
                    "url":"${ctx}/user/login",
                    "type":"POST",
                    "data":$("#form").serialize(),
                    success:function (data) {
                        if(data.code==0){
                            localStorage.setItem("User-Token",data.data.token);
                            location.href="${ctx}/";
                        }else{
                            alert(data.msg);
                        }
                    }
                });
            });
        });
    </script>
</head>
<body>
<form id="form">
    <input name="username" id="username">
    <input name="password" id="password">
    <button type="button" id="login">登录</button>
</form>
</body>
</html>