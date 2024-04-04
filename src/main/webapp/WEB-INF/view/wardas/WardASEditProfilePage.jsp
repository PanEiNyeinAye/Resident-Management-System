<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Ward Administrator Edit Profile</title>
    <meta name="description" content="Ela Admin - HTML5 Admin Template">
    <meta name="viewport" content="width=device-width, initial-scale=1">

     <link rel="apple-touch-icon" href="/ResidentManagementSystem/images/favicon.png">
    <link rel="shortcut icon" href="/ResidentManagementSystem/images/favicon.png">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/normalize.css@8.0.0/normalize.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/font-awesome@4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/lykmapipo/themify-icons@0.1.2/css/themify-icons.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/pixeden-stroke-7-icon@1.2.3/pe-icon-7-stroke/dist/pe-icon-7-stroke.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.2.0/css/flag-icon.min.css">
    <link rel="stylesheet" href="/ResidentManagementSystem/assets/css/cs-skin-elastic.css">
    <link rel="stylesheet" href="/ResidentManagementSystem/assets/css/style.css">
    <!-- <script type="text/javascript" src="https://cdn.jsdelivr.net/html5shiv/3.7.3/html5shiv.min.js"></script> -->
    <link href="https://cdn.jsdelivr.net/npm/chartist@0.11.0/dist/chartist.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/jqvmap@1.5.1/dist/jqvmap.min.css" rel="stylesheet">

    <link href="https://cdn.jsdelivr.net/npm/weathericons@2.1.0/css/weather-icons.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/fullcalendar@3.9.0/dist/fullcalendar.min.css" rel="stylesheet" />
 <style>

td {
  padding-left: 10px;
  padding-right: 10px;
}

  .user-area .user-menu {
  position:absolute;
   color: #06213F;
    background: #fff ;
   border: solid;
    border-color:#06213F
    top: 54px !important;
    margin: 0;
    padding: 5px 10px;
    z-index: 999;
    min-width: 580px;
    -webkit-box-shadow: 0 3px 12px rgba(0, 0, 0, 0.05);
            box-shadow: 0 3px 12px rgba(0, 0, 0, 0.05); }
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
  font: bold15px arial,sans-serif;
}

.color{
color:red;
}
</style>

</head>

<body>
    <!-- Left Panel -->
    <aside id="left-panel" class="left-panel">
        <nav class="navbar navbar-expand-sm navbar-default">
            <div id="main-menu" class="main-menu collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li>
                        <a href=""><i class="menu-icon fa fa-laptop"></i> </a>
                    </li>
                    <li>
                        <a href="/ResidentManagementSystem/wardas/home"><i class="menu-icon fa fa-laptop"></i>Dashboard </a>
                    </li>
                    <li class="menu-title">Administrative divisions<!-- /.menu-title -->
                    
                   <li class="menu-item-has-children">
                        <a href="/ResidentManagementSystem/wardas/viewStateOrRegion/${wardAS.wardASId}"> <i class="menu-icon fa fa-table"></i>State/Regions</a>
                    </li>
                    
                    <li class="menu-item-has-children">
                        <a href="/ResidentManagementSystem/wardas/viewTownship/${wardAS.wardASId}"> <i class="menu-icon fa fa-table"></i>Townships</a>
                    </li>
                    
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Ward/Villages</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/viewWard/${wardAS.wardASId}">Ward</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/viewVillage/${wardAS.wardASId}">Village</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/viewWardOrVillage/${wardAS.wardASId}">All</a></li>
                        </ul>
                    </li>
                    
                    <li class="menu-title">Residents</li><!-- /.menu-title -->
                    
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Residents</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/viewMaleResidentList/${wardAS.wardASId}">Male</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/viewFemaleResidentList/${wardAS.wardASId}">Female</a></li>
                             <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/manageResidentList/${wardAS.wardASId}">Total</a></li>
                        </ul>
                    </li>
                    
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Over 18</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/viewOver18MaleList/${wardAS.wardASId}">Male</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/viewOver18FemaleList/${wardAS.wardASId}">Female</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/viewOver18List/${wardAS.wardASId}">All</a></li>
                        </ul>
                    </li>
                    
                   	<li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Over 85</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/viewOver85MaleList/${wardAS.wardASId}">Male</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/viewOver85FemaleList/${wardAS.wardASId}">Female</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/viewOver85List/${wardAS.wardASId}">All</a></li>
                        </ul>
                    </li>
                    
                     <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Birth</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/viewMaleBirthList/${wardAS.wardASId}">Male</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/viewFemaleBirthList/${wardAS.wardASId}">Female</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/manageBirthList/${wardAS.wardASId}">All</a></li>
                        </ul>
                    </li>
                    
                    <li class="menu-item-has-children">
                        <a href="/ResidentManagementSystem/wardas/manageDeathList/${wardAS.wardASId}"> <i class="menu-icon fa fa-table"></i>Deceased</a>
                    </li>
                    
                     <li class="menu-title">NRC Registration Status</li><!-- /.menu-title -->
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Over 10 Non NRC</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/viewOver10MaleNonNRC/${wardAS.wardASId}">Male</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/viewOver10FemaleNonNRC/${wardAS.wardASId}">Female</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/viewOver10NonNRC/${wardAS.wardASId}">All</a></li>
                        </ul>
                    </li>
                    
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Below 10 Non NRC</a>
                        <ul class="sub-menu children dropdown-menu">
                             <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/viewBelow10MaleNonNRC/${wardAS.wardASId}">Male</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/viewBelow10FemaleNonNRC/${wardAS.wardASId}">Female</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/viewBelow10NonNRC/${wardAS.wardASId}">All</a></li>
                        </ul>
                    </li>
                    
              		<li class="menu-title">Vaccination Status</li><!-- /.menu-title -->
                     <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>BCG</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/viewBCGDone/${wardAS.wardASId}">Done</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/viewBCGNotYet/${wardAS.wardASId}">Not Yet</a></li>
                        </ul>
                    </li>
                      <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Five Vaccines</a>
                        <ul class="sub-menu children dropdown-menu">
                           <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/viewFiveVaccinesDone/${wardAS.wardASId}">Done</a></li>
                           <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/viewFiveVaccinesNotYet/${wardAS.wardASId}">Not Yet</a></li>
                        </ul>
                    </li>
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Vitamin A</a>
                        <ul class="sub-menu children dropdown-menu">
                           <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/viewVitaminADone/${wardAS.wardASId}">Done</a></li>
                           <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/viewVitaminANotYet/${wardAS.wardASId}">Not Yet</a></li>
                        </ul>
                    </li>
   
                    <li class="menu-item-has-children ">
                        <a href="/ResidentManagementSystem/wardas/view9MonthsList/${wardAS.wardASId}"> <i class="menu-icon fa fa-table"></i>9 months babies</a>
                    </li>
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>HPV Vaccine</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/viewHPVVaccineDone/${wardAS.wardASId}">Done</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/wardas/viewHPVVaccineNotYet/${wardAS.wardASId}">Not Yet</a></li>
                         </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </nav>
    </aside>
    <!-- /#left-panel -->
    <!-- Right Panel -->
    <div id="right-panel" class="right-panel">
        <!-- Header-->
        <header id="header" class="header">
            <div class="top-left">
                <div class="navbar-header">
                    <a class="navbar-brand" href="./">${wardAS.wardOrVillage.wardOrVillageName} Administrator</a>
                    <a class="navbar-brand" href="./">${wardAS.wardOrVillage.township.townshipName}</a>
                    <a id="menuToggle" class="menutoggle"><i class="fa fa-bars"></i></a>
                </div>
            </div>
            <div class="top-right">
                <div class="header-menu">
                    <div class="header-left">
                      <a href="/ResidentManagementSystem/wardas/getSend/${wardAS.wardASId}"><i class="fa fa-pencil" aria-hidden="true"></i></a>  
                      &nbsp;&nbsp;
                     
                       <a href="/ResidentManagementSystem/wardas/viewSentHistory/${wardAS.wardASId}"><i class="fa fa-paper-plane" aria-hidden="true"></i></a>  
                      &nbsp;&nbsp;
						<div class="dropdown for-message">
                             <a href="/ResidentManagementSystem/wardas/receive/${wardAS.wardASId}">
                               <i class="fa fa-envelope"></i>
                            </a> 
                        </div>
                    </div>

                     <div class="user-area dropdown float-right">
                        <a href="#" class="dropdown-toggle active" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <img class="user-avatar rounded-circle" src="/ResidentManagementSystem/images/user.png" alt="User Avatar">
                        </a>
						 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
						 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
                        <div class="user-menu">
                      	 <form:form method="post" action="/ResidentManagementSystem/wardas/editProfile">
           					
           					
           					<table>
    							<tr>
    							<td>Email </td>
    							<td><form:input cssClass="input" path="email" placeholder="Enter Email" required="true"/></td>
    							
    							</tr>
								<c:if test="${error eq true}"><tr><td></td><td><small class="color"> * Email must be in correct format.</small></td><tr></c:if>
		                 		<tr>
		                 		<td> New Password </td>
		                 		<td><form:password cssClass="input" path="password" required="true"/></td>
		                 		</tr>
		                		<c:if test="${error eq true}"><tr><td></td><td><small class="color"> * Password must have eight characters.</small></td><tr></c:if>
		                		<tr>
		                 		<td>Confirm New Password </td>
		                 		<td><form:password cssClass="input" path="newPassword" required="true"/></td>
		                 		</tr>
		                 		
		                 		<c:if test="${passwordNotMatch eq true}"><tr><td></td><td><small class="color">Password Not Match, Try Again!</small></td><tr></c:if>
		                	</table>
							<center><input type="Submit" value="Save"></center>
							<a href="/ResidentManagementSystem/wardas/home" style="display:inline-block;"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
				</form:form>
                       
                        
                          
                      
                        </div>
                    </div>

                </div>
            </div>
        </header>
        <!-- /#header -->
        <!-- Content -->
        
        <!-- /.content -->
       
        <!-- Footer -->
        
        <!-- /.site-footer -->
    
    <!-- /#right-panel -->
   

    <!-- Scripts -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.4/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery-match-height@0.7.2/dist/jquery.matchHeight.min.js"></script>
    <script src="/ResidentManagementSystem/assets/js/main.js"></script>

</body>
</html>
