
@Code
    Layout = Nothing
End Code

<!DOCTYPE html>

<html>
<head>
    <meta name="viewport" content="width=device-width" />
    <title>登録</title>
    <script>
        function clearClick() {
            alert("11");
        }
    </script>



</head>
<body>
    <a id="url" href=" http://www.baidu.com" />通过超链接跳转到百度</a>
    <div><label>ユーザー：</label> <input id="name" /></div>
    <div style="padding-top:1em"><label>パスワード：</label><input id="psw" /> </div>
    <div style="padding-top:1em"> <button id="clear" onclick="clearClick()">クリア</button> <button id="Login">登録</button></div>

</body>

</html>
