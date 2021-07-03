<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<script type="text/javascript">
   function checkUser() {
      if (document.login.inputname.value == "") {
         alert("用户名不能为空.");
         return false;
      }
      if (document.login.inputpass.value == "") {
         alert("密码不能为空.");
         return false;
      }
      return true;
   }
</script>
<title>西普教育</title>
<link rel="stylesheet" href="assets/css/style.css">

<body style="background-color: #D3A4FF; background-position: center; background-repeat: repeat-y">
   <div class="login-container">
      <h1>网盘</h1>
      <div class="connect">
         <p>www.shiyanbar.com</p>
      </div>
      <form action="LoginServlet" method="post" id="loginForm" name="login" onsubmit="return checkUser()">
         <div>
            <input type="text" id="inputname" name="username" class="username" placeholder="用户名" autocomplete="off" />
         </div>
         <div>
            <input type="password" id="inputpass" name="password" class="password" placeholder="密码"
               oncontextmenu="return false" onpaste="return false" />
         </div>
         <button id="submit" type="submit">登录</button>
      </form>
      <a href="register.jsp">
         <button type="button" class="register-tis">还有没有账号?</button>
      </a>
   </div>
   <div style="text-align: center; margin: 50px; font: normal 14px/24px 'Microsoft YaHei';">
      <p>适用浏览器:360、FireFox、Chrome、 Safari、Opera、傲游、搜狗、世界之窗.不支持IE8及以下浏览器。</p>
      <p>
         来源:<a href="http://sc.chinaz.com/" target=" blank">西普教育</a>
      </P>
   </div>
</body>

</html>
