<!DOCTYPE html>
<html lang="en">
<#assign ctx=request.getContextPath()>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="${ctx}/static/js/jquery-3.3.1.js"></script>
    <script>
        $(function(){
            $("#test").click(function () {
                $.ajax({
                    "headers":{"User-Token":localStorage.getItem("User-Token")},
                    "url":"${ctx}/user/test",
                    "type":"GET",
                    success:function (data) {
                        alert(data.data);
                    }
                });
            });
        })
    </script>
</head>
<body>
<button type="button" id="test">test</button>
</body>
</html>