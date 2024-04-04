<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Ward/Village Administrators Manage Residents</title>
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
                    
                    <li class="menu-item-has-children active dropdown">
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
        <div class="content">
            <!-- Animated -->
            <div class="animated fadeIn">
                <!-- Widgets  -->
                <div class="row">
                        <div class="card" style="left:190px;">
                            <div class="card-header" style="color:#fff; text-align:center;background-color:#3A4D6F;">
                               <strong>	Edit Resident </strong>
                            </div>
                            <div class="card-body card-block">
           <form:form method="post" action="/ResidentManagementSystem/wardas/editResident/${wardAS.wardASId}">
           					
           					 <form:hidden path="residentId" />
           					 
           					<div class="row form-group">
		                    <div class="col col-md-3"><label for="text-input" class=" form-control-label">Name</label></div>
		                    <div class="col-12 col-md-9"><form:input path="residentName" id="text-input" name="text-input"  class="form-control" required="true"/><small class="form-text text-muted"></small></div>
		                	</div>
							
							<div class="row form-group">
                    		<div class="col col-md-3"><label class=" form-control-label">Gender</label></div>
                    		<div class="col col-md-9">
							<div class="form-check-inline form-check">
							<label for="inline-radio1" class="form-check-label">
							<form:radiobutton path="gender" value="Male" id="inline-radio1" name="inline-radios" class="form-check-input" required="true"/>Male
							 </label>&nbsp;
							 <label for="inline-radio2" class="form-check-label ">
							<form:radiobutton path="gender" value="Female" id="inline-radio2" name="inline-radios" class="form-check-input" required="true"/>Female<br>
							 </label>
							</div>
							</div>
                   			 </div>
						
							
							<div class="row form-group">
		                    <div class="col col-md-3"><label for="text-input" class=" form-control-label">Date of Birth</label></div>
		                    <div class="col-12 col-md-9"><form:input path="dob" id="text-input" name="text-input"  class="form-control" type="date" required="true"/><small class="form-text text-muted"></small></div>
		                	</div>
	
							<div class="row form-group">
		                    <div class="col col-md-3"><label for="text-input" class=" form-control-label">National Registration Card(NRC) No.</label></div>
		                    <div class="col-12 col-md-9"><form:input path="nrcNo" id="text-input" name="text-input"  class="form-control" placeholder="Eg; 8/MaLaNa(N)000000" required="true"/><small class="form-text text-muted"></small></div>
		                	 <div class="col-12 col-md-9 error"><c:if test="${error eq true}">* NRC number must be in correct format.</c:if></div>
		                	<div class="col-12 col-md-9 error"><c:if test="${nrcDuplicateError eq true}">* There is a resident with the same NRC number.</c:if></div>
		                	</div>
							
							<div class="row form-group">
		                    <div class="col col-md-3"><label for="text-input" class=" form-control-label">Father's Name</label></div>
		                    <div class="col-12 col-md-9"><form:input path="fatherName" id="text-input" name="text-input"  class="form-control" required="true"/><small class="form-text text-muted"></small></div>
		                	</div>
					
							<div class="row form-group">
		                    <div class="col col-md-3"><label for="text-input" class=" form-control-label">Mother's Name</label></div>
		                    <div class="col-12 col-md-9"><form:input path="motherName" id="text-input" name="text-input"  class="form-control" required="true"/><small class="form-text text-muted"></small></div>
		                	</div>
		
							<div class="row form-group">
		                    <div class="col col-md-3"><label for="text-input" class=" form-control-label">Ethnicity</label></div>
		                    <div class="col-12 col-md-9"><form:input path="ethnicity" id="text-input" name="text-input"  class="form-control" required="true"/><small class="form-text text-muted"></small></div>
		                	</div>
		              
							<div class="row form-group">
		                    <div class="col col-md-3"><label for="text-input" class=" form-control-label">Note</label></div>
		                    <div class="col-12 col-md-9"><form:input path="note" id="text-input" name="text-input"  class="form-control" /><small class="form-text text-muted"></small></div>
		                	</div>
							
							<div class="row form-group">
							<div class="col col-md-3"><label for="email-input" class=" form-control-label">Email</label></div>
							<div class="col-12 col-md-9"><form:input path="residentEmail" placeholder="Enter Email" id="email-input" name="email-input" class="form-control" required="true"/><small class="help-block form-text"></small></div><br>
							<div class="col-12 col-md-9 error"><c:if test="${error eq true}">* Email must be in correct format.</c:if></div>
							</div>
							
							<div class="row form-group">
		                    <div class="col col-md-3"><label for="text-input" class=" form-control-label">Password</label></div>
		                    <div class="col-12 col-md-9"><form:password path="residentPassword" id="text-input" name="text-input"  class="form-control" required="true"/><small class="form-text text-muted"></small></div>
		                	<div class="col-12 col-md-9 error"><c:if test="${error eq true}">* Password must have 8 characters.</c:if></div>
		                	</div>
		                	
							<div class="row form-group">
                    		<div class="col col-md-3"><label class=" form-control-label">BCG Vaccine</label></div>
                    		<div class="col col-md-9">
							<div class="form-check-inline form-check">
							<label for="inline-radio1" class="form-check-label">
							<form:radiobutton path="bcg" value="true" id="inline-radio1" name="inline-radios" class="form-check-input"/>Done
							 </label>&nbsp;
							 <label for="inline-radio2" class="form-check-label ">
							<form:radiobutton path="bcg" value="false" id="inline-radio2" name="inline-radios" class="form-check-input"/>Not Yet<br>
							 </label>
							</div>
							</div>
                   			 </div>
               				
							
							<div class="row form-group">
                    		<div class="col col-md-3"><label class=" form-control-label">Five Vaccines 1st Time</label></div>
                    		<div class="col col-md-9">
							<div class="form-check-inline form-check">
							<label for="inline-radio1" class="form-check-label">
							<form:radiobutton path="fiveVaccines1" value="true" id="inline-radio1" name="inline-radios" class="form-check-input"/>Done
							 </label>&nbsp;
							 <label for="inline-radio2" class="form-check-label ">
							<form:radiobutton path="fiveVaccines1" value="false" id="inline-radio2" name="inline-radios" class="form-check-input"/>Not Yet<br>
							 </label>
							</div>
							</div>
                   			 </div>
               				
               				
						<div class="row form-group">
                    		<div class="col col-md-3"><label class=" form-control-label">Five Vaccines 2nd Time</label></div>
                    		<div class="col col-md-9">
							<div class="form-check-inline form-check">
							<label for="inline-radio1" class="form-check-label">
							<form:radiobutton path="fiveVaccines2" value="true" id="inline-radio1" name="inline-radios" class="form-check-input"/>Done
							 </label>&nbsp;
							 <label for="inline-radio2" class="form-check-label ">
							<form:radiobutton path="fiveVaccines2" value="false" id="inline-radio2" name="inline-radios" class="form-check-input"/>Not Yet<br>
							 </label>
							</div>
							</div>
                   			 </div>
               				
               				
               				<div class="row form-group">
                    		<div class="col col-md-3"><label class=" form-control-label">Five Vaccines 3rd Time</label></div>
                    		<div class="col col-md-9">
							<div class="form-check-inline form-check">
							<label for="inline-radio1" class="form-check-label">
							<form:radiobutton path="fiveVaccines3" value="true" id="inline-radio1" name="inline-radios" class="form-check-input"/>Done
							 </label>&nbsp;
							 <label for="inline-radio2" class="form-check-label ">
							<form:radiobutton path="fiveVaccines3" value="false" id="inline-radio2" name="inline-radios" class="form-check-input"/>Not Yet<br>
							 </label>
							</div>
							</div>
                   			 </div>
               				
               				<div class="row form-group">
                    		<div class="col col-md-3"><label class=" form-control-label">Vitamin A</label></div>
                    		<div class="col col-md-9">
							<div class="form-check-inline form-check">
							<label for="inline-radio1" class="form-check-label">
							<form:radiobutton path="vitaminA" value="true" id="inline-radio1" name="inline-radios" class="form-check-input"/>Done
							 </label>&nbsp;
							 <label for="inline-radio2" class="form-check-label ">
							<form:radiobutton path="vitaminA" value="false" id="inline-radio2" name="inline-radios" class="form-check-input"/>Not Yet<br>
							 </label>
							</div>
							</div>
                   			 </div>
		
							<div class="row form-group">
                    		<div class="col col-md-3"><label class=" form-control-label">HPV Vaccine</label></div>
                    		<div class="col col-md-9">
							<div class="form-check-inline form-check">
							<label for="inline-radio1" class="form-check-label">
							<form:radiobutton path="hpvVaccine" value="true" id="inline-radio1" name="inline-radios" class="form-check-input"/>Done
							 </label>&nbsp;
							 <label for="inline-radio2" class="form-check-label ">
							<form:radiobutton path="hpvVaccine" value="false" id="inline-radio2" name="inline-radios" class="form-check-input"/>Not Yet<br>
							 </label>
							</div>
							</div>
                   			 </div>
					
							<form:hidden path="wardOrVillageId" value="${wardOrVillageId}"/><br>
		                	
							<div class="card-footer" style="background-color:#3A4D6F; color:black; text-align:center;">
							<a href="/ResidentManagementSystem/wardas/manageResidentList/${wardAS.wardASId}" style="display:inline-block;color:white;float:left;"><i class="fa fa-angle-double-left" style="font-size:28px;" aria-hidden="true"></i></a><input type="Submit">
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
        <footer class="site-footer">
            <div class="footer-inner bg-white">
                <div class="row">
                    <div class="col-sm-6">
                        Copyright &copy; 2018 Ela Admin
                    </div>
                    <div class="col-sm-6 text-right">
                        Designed by <a href="https://colorlib.com">Colorlib</a>
                    </div>
                </div>
            </div>
        </footer>
        <!-- /.site-footer -->
    </div>
    <!-- /#right-panel -->

    <!-- Scripts -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.4/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery-match-height@0.7.2/dist/jquery.matchHeight.min.js"></script>
    <script src="/ResidentManagementSystem/assets/js/main.js"></script>
    
     <script src="/ResidentManagementSystem/assets/js/lib/data-table/datatables.min.js"></script>
    <script src="/ResidentManagementSystem/assets/js/lib/data-table/dataTables.bootstrap.min.js"></script>
    <script src="/ResidentManagementSystem/assets/js/lib/data-table/dataTables.buttons.min.js"></script>
    <script src="/ResidentManagementSystem/assets/js/lib/data-table/buttons.bootstrap.min.js"></script>
    <script src="/ResidentManagementSystem/assets/js/lib/data-table/jszip.min.js"></script>
    <script src="/ResidentManagementSystem/asssets/js/lib/data-table/vfs_fonts.js"></script>
    <script src="/ResidentManagementSystem/assets/js/lib/data-table/buttons.html5.min.js"></script>
    <script src="/ResidentManagementSystem/assets/js/lib/data-table/buttons.print.min.js"></script>
    <script src="/ResidentManagementSystem/assets/js/lib/data-table/buttons.colVis.min.js"></script>
    <script src="/ResidentManagementSystem/assets/js/init/datatables-init.js"></script>


    <script type="text/javascript">
        $(document).ready(function() {
          $('#bootstrap-data-table-export').DataTable();
      } );
  </script>
</body>
</html>
