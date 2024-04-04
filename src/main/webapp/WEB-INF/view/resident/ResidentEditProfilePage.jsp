<!DOCTYPE html>
<!--
Template Name: Academic Education V2
Author: <a href="http://www.os-templates.com/">OS Templates</a>
Author URI: http://www.os-templates.com/
Licence: Free to use under our free template licence terms
Licence URI: http://www.os-templates.com/template-terms
-->
<html>
<head>
<title>Resident</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link href="/ResidentManagementSystem/layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">
    <link rel="apple-touch-icon" href="/ResidentManagementSystem/images/favicon.png">
    <link rel="shortcut icon" href="/ResidentManagementSystem/images/favicon.png">
<style>
pre span{
text-decoration: underline;
 text-decoration-color: #C41E3A;
}
</style>
   <style>

td {
  padding-left: 10px;
  padding-right: 10px;
  color: black;
}

.input{
height: 30px;
width: 250px;
}

input[type=submit] {
  background-color: #06213F;
  border: none;
  color: white;
height: 50px;
width: 80px;
  margin: 4px 2px;
  cursor: pointer;
  margin: 0 auto;
  font: bold15px arial,sans-serif;

}

.color{
color:red;
}

</style>
</head>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body id="top">
<!-- ################################################################################################ --> 
<!-- ################################################################################################ --> 
<!-- ################################################################################################ -->
<div class="wrapper row0">
  <div id="topbar" class="clear"> 
    <!-- ################################################################################################ -->
    
    <!-- ################################################################################################ --> 
  </div>
</div>
<!-- ################################################################################################ --> 
<!-- ################################################################################################ --> 
<!-- ################################################################################################ -->
<div class="wrapper row1">
  <header id="header" class="clear"> 
    <!-- ################################################################################################ -->
    <div id="logo" class="fl_left">
      <h1><a href="index.html">Resident Registration, Management and Identification</a></h1>
    </div>
    
    <!-- ################################################################################################ --> 
  </header>
</div>
<!-- ################################################################################################ --> 
<!-- ################################################################################################ --> 
<!-- ################################################################################################ -->
<div class="wrapper row2">
  <div class="rounded">
    <nav id="mainav" class="clear"> 
      <!-- ################################################################################################ -->
     <ul class="clear">
        <li><a href="/ResidentManagementSystem/resident/home">Home</a></li>
         <li class="active"><a href="/ResidentManagementSystem/resident/myProfile">My Profile</a></li>
        <li><a href="/ResidentManagementSystem/resident/logout">Logout</a></li>
      </ul>
      <!-- ################################################################################################ --> 
    </nav>
  </div>
</div>
<!-- ################################################################################################ --> 
<!-- ################################################################################################ --> 
<!-- ################################################################################################ -->
<div class="wrapper">
  <div id="slider">

    <div id="slide-wrapper" class="rounded clear" style="color:black; border-style: solid; margin: auto;
  width: 50%;
  padding: 10px;border: 5px solid;background-color:#06213F;"> 
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
						 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
      <!-- ################################################################################################ -->
     
     <div style="background-color:#3A4D6F;color:#fff; font-size:20px; font-family:FontAwesome; font-weight:bold; text-align:center; padding:10px;">"My Profile"</div>
  
     <div style="background-color:#3A4D6F; color:#fff; font-size:18px; font-family:FontAwesome; font-weight:bold; padding: 10px;">
  <form:form method="post" action="/ResidentManagementSystem/resident/editProfile" style="display:inline-block;">
           					
           					
           					<table class="center">
    							<tr>
    							<td>Email </td>
    							<td><form:input cssClass="input" path="email" placeholder="Enter Email" required="true"/></td>
    							
    							</tr>
								<c:if test="${error eq true}"><tr><td></td><td><small class="color"> * Email must be in correct format.</small></td><tr></c:if>
		                 		<tr>
		                 		<td>New Password </td>
		                 		<td><form:password cssClass="input" path="password" required="true"/></td>
		                 		</tr>
		                		<c:if test="${error eq true}"><tr><td></td><td><small class="color"> * Password must have eight characters.</small></td><tr></c:if>
		                		<tr>
		                 		<td>Confirm New Password </td>
		                 		<td><form:password cssClass="input" path="newPassword" required="true"/></td>
		                 		</tr>
		                 		
		         
		                 		<c:if test="${passwordNotMatch eq true}"><tr><td></td><td><small class="color">Password Not Match, Try Again!</small></td><tr></c:if>
		                	</table>
							<input type="submit" value="Save"></form:form>
							<a href="/ResidentManagementSystem/resident/myProfile" style="display:inline-block;"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
							
				
      
     </div>
     
      
      <!-- ################################################################################################ --> 
  
    </div>
  
  </div>
</div>
<!-- ################################################################################################ --> 
<!-- ################################################################################################ --> 
<!-- ################################################################################################ -->

<!-- ################################################################################################ --> 
<!-- ################################################################################################ --> 
<!-- ################################################################################################ -->
<div class="wrapper row4">
  <div class="rounded">
    <footer id="footer" class="clear"  style="background-color:#3A4D6F;"> 
      <!-- ################################################################################################ -->

      
      <div class="one_third first">
       09-112345678<br>
        Building Number (10)<br>
         Nay Pyi Taw<br>
          Myanmar<br>
 		<a href="https://moha.gov.mm/index.php?d=1&lang=en">www.moha.gov.mm</a>
       </div>
       
      <div class="one_third second">
       &nbsp;&nbsp;
       </div>
       
      <div class="one_third third">
        <p class="nospace btmspace-10">Stay Up to Date With What's Happening</p>
        <ul class="faico clear">
          <li><a class="faicon-facebook" href="#"><i class="fa fa-facebook"></i></a></li>
        </ul>
        <form class="clear" method="post" action="#">
          <fieldset>
            <legend>Follow our Facebook Page</legend>
          </fieldset>
        </form>
      </div>
      <!-- ################################################################################################ --> 
    </footer>
  </div>
</div>
<!-- ################################################################################################ --> 
<!-- ################################################################################################ --> 
<!-- ################################################################################################ -->
<div class="wrapper row5">
  <div id="copyright" class="clear"> 
    <!-- ################################################################################################ -->
    <p class="fl_left">Copyright &copy; 2023 - All Rights Reserved - <a href="#">Resident Management System</a></p>
    <p class="fl_right">Template by <a target="_blank" href="http://www.os-templates.com/" title="Free Website Templates">OS Templates</a></p>
    <!-- ################################################################################################ --> 
  </div>
</div>
<!-- JAVASCRIPTS --> 
<script src="/ResidentManagementSystem/layout/scripts/jquery.min.js"></script> 
<script src="/ResidentManagementSystem/layout/scripts/jquery.fitvids.min.js"></script> 
<script src="/ResidentManagementSystem/layout/scripts/jquery.mobilemenu.js"></script> 
<script src="/ResidentManagementSystem/layout/scripts/tabslet/jquery.tabslet.min.js"></script>
</body>
</html>