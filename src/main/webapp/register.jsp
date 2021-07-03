<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<script type="text/javascript">
   function checkRegister() {
      if (document.register.inputname.value == "") {
         alert("用户名不能为空.");
         return false;
      } else {
         if (document.register.inputpass.value == "") {
            alert("密码不能为空.");
            return false;
         } else {
            if (document.register.surepass.value == "") {
               alert("确认密码不能为空·");
               return false;
            } else {
               if (document.register.inputpass.value != document.register.surepass.value) {
                  alert("两次密码不一致。");
                  return false;
               }
            }
         }
      }
      return true;
   }
</script>
<title>注册</title>
<link rel="stylesheet" href="assets/css/site.css">

<body>
   <div>
      <h1>注册</h1>
      <div class="connect">
         <p>信息注册</p>
      </div>
      <form action="RegisterServlet" method="post" id="registerForm" onsubmit="return checkRegister()" name="register">
         <div>
            <input type="text" name="username" id="inputname" class="username" placeholder="您的用户名" autocomplete="off" />
         </div>
         <div>
            <input type="password" name="password" id="inputpass" class="password" placeholder="输入密码" />
         </div>
         <div>
            <input type="password" name="surepass" id="surepass" class="surepass" placeholder="再次输入密码" />
         </div>
         <button id="submit" type="submit">注册</button>
      </form>
      <a href="login.jsp">
         <button type="button" class="register-tis">已经有账号?</button>
      </a>
   </div>
</body>

</html>