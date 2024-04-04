<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Ward Administrator</title>
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
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
       <link rel="stylesheet" href="/ResidentManagementSystem/assets/css/lib/datatable/dataTables.bootstrap.min.css">
   <style> 
.error{color:red} 
</style>       
</head>

<body>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
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
                        <div class="user-menu dropdown-menu">
                      
                        <%@ include file="myProfile.jsp" %>
                        
                            <div style="text-align:center;">
                             <a href="/ResidentManagementSystem/wardas/getEditProfile"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>Edit</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/ResidentManagementSystem/wardas/logout"><i class="fa fa-sign-out" aria-hidden="true"></i>Logout</a></div>
                      
                        </div>
                    </div>
				
                </div>
            </div>
        </header>
          
        <!-- /#header -->
        <!-- Content -->
        <div class="content" style="background-color:#3A4D6F;">
            <!-- Animated -->
            <div class="animated fadeIn">
                <!-- Widgets  -->
                       <div class="row">
                 <div class="card" style="left:190px;">
                  <div class="card-header" style="color:#06213F; text-align:center;background-color:#f9dec0;">
                               <strong>	Send to the resident's Previous Ward/Village Administrator to confirm </strong>
                            </div>
                            <div class="card-body card-block">
           <form:form method="post" action="/ResidentManagementSystem/wardas/send/${wardAS.wardASId}"  modelAttribute="communication">
               				
               				<div class="row form-group">
		                    <div class="col col-md-3"><label for="text-input" class=" form-control-label">Resident's National Registration Card(NRC) No.</label></div>
		                    <div class="col-12 col-md-9"><form:input path="residentNrcNo" id="text-input" name="text-input"  class="form-control" placeholder="Eg; 8/MaLaNa(N)000000" /><small class="form-text text-muted">Please write in the correct order!</small></div>
		                	<div class="col-12 col-md-9 error"><c:if test="${nrcError eq true}">* NRC number must be in correct format.</c:if></div>
		                	</div>
		                	
               				<div style="text-align:center;font-weight:bold;">Please Correctly Choose The Administrative Division To Send The Message!</div>
           					<br>
           					<div class="row form-group">
		                    <div class="col col-md-3"><label for="text-input" class=" form-control-label">State/Region</label></div>
		                    <div class="col-12 col-md-9">
			                    <form:select path="stateOrRegionName"  name="select" id="select" class="form-control">
			                    	<form:options items="${stateOrRegions}" itemValue="stateOrRegionName" itemLabel="stateOrRegionName"/>    	
			                    </form:select>
			                    </div>
		                	</div>
		                	
		                	<div class="row form-group">
		                    <div class="col col-md-3"><label for="text-input" class=" form-control-label">Township</label></div>
		                    <div class="col-12 col-md-9">
			                    <form:select path="townshipName"  name="select" id="select" class="form-control">
			                    	<form:options items="${townships}" itemValue="townshipName" itemLabel="townshipName"/>    	
			                    </form:select>
			                    </div>
		                	</div>
		                	
		                	<div class="row form-group">
		                    <div class="col col-md-3"><label for="text-input" class=" form-control-label">Ward/Village</label></div>
		                    <div class="col-12 col-md-9">
			                    <form:select path="wardOrVillageName"  name="select" id="select" class="form-control">
			                    	<form:options items="${wardOrVillages}" itemValue="wardOrVillageName" itemLabel="wardOrVillageName"/>    	
			                    </form:select>
			                    </div>
		                	</div>
		                	
							<div class="card-footer" style="background-color:#f9dec0; color:black; text-align:center;">
							<a href="/ResidentManagementSystem/wardas/home" style="display:inline-block;color:#3A4D6F;float:left;"><i class="fa fa-angle-double-left" style="font-size:28px;" aria-hidden="true"></i></a><input type="Submit">
							</div>
				</form:form>
           </div>
            </div>
            </div>
            <!-- .animated -->
        </div>
        <!-- /.content -->
        <div class="clearfix"></div>
        <!-- Footer -->
       <footer class="site-footer" style="background-color:#3A4D6F;">
            <div class="footer-inner" style="background-color:#3A4D6F;">
               
            </div>
             <div class="footer-inner" style="background-color:#3A4D6F;">
               
            </div>
            <div class="footer-inner" style="background-color:#3A4D6F;">
               
            </div>
             <div class="footer-inner" style="background-color:#3A4D6F;">
               
            </div>
            <div class="footer-inner" style="background-color:#3A4D6F;">
               
            </div>
            <div class="footer-inner" style="background-color:#3A4D6F;">
               
            </div>
        </footer>
        <!-- /.site-footer -->
    </div>
    
    </div>
    <!-- /#right-panel -->

    <!-- Scripts -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.4/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery-match-height@0.7.2/dist/jquery.matchHeight.min.js"></script>
    <script src="/ResidentManagementSystem/assets/js/main.js"></script>

</body>
</html>
