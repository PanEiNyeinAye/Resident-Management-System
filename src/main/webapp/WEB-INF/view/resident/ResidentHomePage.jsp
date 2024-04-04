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
        <li class="active"><a href="/ResidentManagementSystem/resident/home">Home</a></li>
         <li><a href="/ResidentManagementSystem/resident/myProfile">My Profile</a></li>
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
    <div id="slide-wrapper" class="rounded clear" style="background-color:#3A4D6F;" > 
      <!-- ################################################################################################ -->
      <figure id="slide-1"><a class="view" href="#"><img src="/ResidentManagementSystem/images/1.jpg" alt=""></a>
        <c:if test="${criminalRecord eq false}">
        <figcaption style="width: 450px; left:250px;">
         <h1 style="color:white; text-align:center;"><b>Please choose the required letter!</b></h1>
       <div style="display:flex; justify-content: center;">
        <form:form action="/ResidentManagementSystem/resident/residencyLetter/${resident.residentId}">
        	 
        	 <input type="Submit" value="Ward/Village Administrator's proof of residency letter" style="color:black;">
        	
        </form:form></div><br>
        <div style="display:flex; justify-content: center;">
        <form:form action="/ResidentManagementSystem/resident/goodCharacterLetter/${resident.residentId}">
        	<input type="Submit" value="Ward/Village Administrator's proof of good character letter" style="color:black;">
        </form:form></div><br>
        <div style="display:flex; justify-content: center;">
        <form:form action="/ResidentManagementSystem/resident/clearanceLetter/${resident.residentId}">
        	<input type="Submit" value="certificate of clearance from criminal records" style="color:black;">
        	
        </form:form></div>
        
        </figcaption>
       </c:if>
       <c:if test="${criminalRecord eq true}">
       <figcaption style="width: 450px; left:250px; height:120px; top:100px; font-size:15px; text-align:center;">
         <b>Please click the button to get the letter!</b> 
          <form:form action="/ResidentManagementSystem/resident/residencyLetter/${resident.residentId}">
        	 <br>
        	 <input type="Submit" value="Ward/Village Administrator's proof of residency letter" style="color:black;">
        	
        </form:form>
      
         </figcaption>
       </c:if>
      </figure>
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