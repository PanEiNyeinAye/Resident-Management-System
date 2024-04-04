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
<title>Resident Login</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="apple-touch-icon" href="/ResidentManagementSystem/images/favicon.png">
    <link rel="shortcut icon" href="/ResidentManagementSystem/images/favicon.png">
<link href="/ResidentManagementSystem/layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">

<link rel="stylesheet" type="text/css" href="/ResidentManagementSystem/layout/styles/fontawesome-4.2.0.min.css">
<style>
 .loginform {
 			
            position: absolute;
            background: #06213F;
            width: 400px;
            height: 210px;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            border-radius: 5px;
            border: 1px solid #6e6cfc;
            padding: 10px;
            box-shadow: 0 12px 20px -10px #6e6cfc;
            text-align: center;
            border-radius: 10px;
           
        }
   
       
        input {
        	
            margin-top: 2px;
            text-align: center;
           color: black;
            width: 60%;
            height: 30px;
            border-radius: 10px;
            border: none;
            outline: none;
             
        }

       
         input[type=submit]{
            margin-top: 2px;
            width: 20%;
            height: 30px;
            border-radius: 10px;
            background: #fff;
            border: 1px solid #06213F;
            outline: none;
            color: black;
            font-size: 15px;
         }
</style>
</head>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
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
        <li><a href="/ResidentManagementSystem/index.html">Home</a></li>
        <li><a href="/ResidentManagementSystem/AboutUs.html">About Us</a>
        </li>
        <li><a href="/ResidentManagementSystem/News.html">News & Notices</a>
            </li>
        <li><a href="/ResidentManagementSystem/userManual.html">User Manual</a></li>
        <li><a href="/ResidentManagementSystem/FAQPage.html">FAQ</a></li>
        <li><a href="#footer">Contact Us</a></li>
         <li class="active"><a href="/ResidentManagementSystem/resident/loginForm">Resident Login</a></li>
        <li><a href="/ResidentManagementSystem/staffLogin">Staff Login</a></li>
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
    <div id="slide-wrapper" class="rounded clear"> 
      <!-- ################################################################################################ -->
      <figure id="slide-1"><a class="view" href="#"><img src="/ResidentManagementSystem/images/1.jpg" alt=""></a>
       <div class="loginform">
      <div align="center" style="color:black;"><h1 style="color:white;  margin-top: 15px;"><b>Enter your email and password to login!</b></h1>
	      <form:form action="/ResidentManagementSystem/resident/validate" modelAttribute="resident">
	      		<form:input path="residentEmail" placeholder="Enter your Email" required="true"/><br>
				<form:password path="residentPassword" placeholder="Enter your password" required="true"/><br>
						<input type="Submit" value="Login">
	      </form:form></div>	
      </div>
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
    <p class="fl_left">Copyright &copy; 2014 - All Rights Reserved - <a href="#">Domain Name</a></p>
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