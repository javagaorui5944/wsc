<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生管理系统</title>
<style type="text/css">

.icon .r3 {
    background: url("user.png") no-repeat scroll 0 0 rgba(0, 0, 0, 0);
    /* display: block;
    height: 14px;
    left: 16px;
    position: absolute;  */
    top: 11px;
/*     width: 15px; */
}

.text {
	border: 1px solid #ccc;
    border: 1px solid #ccc;
    color: #999;
    height: 16px;
    line-height: 16px;
    padding: 2px 0 5px 19px;
    width: 180px;
    border-radius: 3px;
}

.lg_login  .sub{
    background: url("images/login.png") no-repeat scroll 0 0 rgba(0, 0, 0, 0);
    border: medium none;
    cursor: pointer;
    display: inline;
/*     float: left; */
    height: 32px;
    width: 142px; 

}

.lg_login  .sub:hover{
	background-position: -1px 0px;
}
.nav1{
  height: 32px;
    width: 40px;
}
.mogujie{
  height: 300px;
    width: 400px;
}

</style>
<script type="text/javascript">
 function cz() {
	 document.getElementById("Username").value="";
	 document.getElementById("Passwd").value="";
	
}
</script>
</head>
<body background="images/bg.jpg">
<div align="center" style="padding-top:100px;">
<form action="LoginServlet" method="post" >
	<table id="n" width="680" height="340"  background="images/bg2.jpg" style=" border-radius:8px;">
	<tr>
		<td colspan="4"></td>
	</tr>
		<tr height="10" class="icon" >
		<td><input class="text r3" type="text" name="Username" style="border-color:#CFCFCF; color:#999;"  value="手机/邮箱/昵称" name="uname" maxlength="32"></td>
		<td width="5%" id="nav1"></td>
			<!-- <td><input type="text" value="Username" name="Username" id="Username" maxlength="20" size=15></td> -->
			<td width="10%"></td>
			<td width="20%"></td>
		</tr>
		<tr height="10" class="icon">
			<td><input class="text r3" type="password" name="Passwd" style="border-color:#CFCFCF; color:#999;" value=""  name="uname" maxlength="32"></td>
			<td width="5%" id="nav1"></td>
			<!-- <td><input type="password" value="" name="Passwd" id="Passwd" maxlength="20" size=15></td> -->
			
			<td width="30%"></td>	
			<td width="20%"></td>
		</tr>
		<tr height="10" class="lg_login clearfix" >
			<td width="10%" id="nav1" class="nav1" ><input class="sub" type="submit" value="" ></td>
			<td><!-- <input type="button" value="重置" onclick="cz()"> --></td> 
			<td width="30%"></td>	
			<td width="20%"></td>
		</tr>
		<tr height="10">
			<td colspan="3">${ error}</td>
		</tr>
		<tr	>
			<td></td>
		</tr>
	</table>
	</form>
</div>
 <!-- <div align="left" style="padding-top:0px;">
<table  width="400px" height="160px"  background="images/mogujie.jpg" class="mogujie">
<tr height="180" >
		<td colspan="4"></td>
	</tr>
	</table>
</div>  -->
</body>
</html>