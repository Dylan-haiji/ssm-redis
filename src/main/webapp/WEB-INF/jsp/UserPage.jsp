<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="/spring-redis/js/jquery-1.8.3.min.js" type="text/javascript"> </script>
<script type="text/javascript">
$(function(){
		$("#btn").click(function(){
			$.ajax({
				type : 'post',
				data:{name:$('#name').val(),pws:$('#pws').val()},
				url:'/spring-redis/user/toLongin.action',
				datatype:'json',
				success:function(data){
					window.location.href="/spring-redis/order/orderPage.action";
				}
			});
		});
	});
</script>
<title>用户登录</title>
</head>
<body>
	<tr>昵称
		<td><input type="text" id="name"></td><br>
		密码
		<td><input type="password" id="pws"></td><br>
		
		<td><input type="button" id="btn" value="登录"></td>
	</tr>
</body>
</html>