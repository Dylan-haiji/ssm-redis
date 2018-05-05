<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="/spring-redis/js/jquery-1.8.3.min.js" type="text/javascript"> </script>
<script type="text/javascript">
	function getOrder(){
		$.ajax({
			type : 'post',
			data:{},
			url:'/spring-redis/order/selectOrder.action',
			datatype:'json',
			success:function(data){
				console.log(data);

				   var jsonData = eval('(' + data + ')');
				   var headTr ="<tr><td width=100>id</td><td width=100>昵称</td><td width=100>密码</td></tr>";
				   for(var i=0;i<jsonData.length;i++){
					   var id = jsonData[i].id;
					   var name = jsonData[i].name;
					   var pws = jsonData[i].pws;
					   var tr = "<tr>";
					   tr+="<td width=100>";
					   tr+=id;
					   tr+="</td>";
					   tr+="<td width=100>";
					   tr+=name;
					   tr+="</td>";
					   tr+="<td width=100>";
					   tr+=pws;
					   tr+="</td>";
					   
					   headTr+=tr;
					   
				   }
				   $("#orderTable").html(headTr); 
			},
		});
	}
</script>
<title>信息</title>
</head>
<body>
	<input type="button" id="btnton" value="查询" onclick="getOrder()" />
	<table id="orderTable">
		<tr>
			<td width=100>id</td>
			<td width=100>昵称</td>
			<td width=100>密码</td>
		</tr>
	</table>
</body>
</html>