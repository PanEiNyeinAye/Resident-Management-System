<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Sub Admin</title>
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
                        <a href="/ResidentManagementSystem/sadmin/home"><i class="menu-icon fa fa-laptop"></i>Dashboard </a>
                    </li>
                    <li class="menu-title">Administrative divisions<!-- /.menu-title -->
                    
                     <li class="menu-item-has-children">
                        <a href="/ResidentManagementSystem/sadmin/viewStateOrRegion/${sadmin.sadminId}"> <i class="menu-icon fa fa-table"></i>State/Regions</a>
                    </li>
                    
                    <li class="menu-item-has-children">
                        <a href="/ResidentManagementSystem/sadmin/viewTownship/${sadmin.sadminId}"> <i class="menu-icon fa fa-table"></i>Townships</a>
                    </li>
                    
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Ward/Villages</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewWard/${sadmin.sadminId}">Ward</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewVillage/${sadmin.sadminId}">Village</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewWardOrVillage/${sadmin.sadminId}">All</a></li>
                        </ul>
                    </li>
                    
                    <li class="menu-title">Staffs</li><!-- /.menu-title -->
                    
                     <li class="menu-item-has-children">
                        <a href="/ResidentManagementSystem/sadmin/managePoliceOfficerList/${sadmin.sadminId}" > <i class="menu-icon fa fa-table"></i>Police Officers</a>
                    </li>
                    
                     <li class="menu-item-has-children ">
                        <a href="/ResidentManagementSystem/sadmin/manageGASList/${sadmin.sadminId}"> <i class="menu-icon fa fa-table"></i>GAS</a>
                    </li>
                    
                     <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Administrators</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/manageWardASList/${sadmin.sadminId}">Ward</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/manageVillageASList/${sadmin.sadminId}">Village</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewWardOrVillageASList/${sadmin.sadminId}">All</a></li>
                        </ul>
                    </li>
                    
                    
                    <li class="menu-title">Residents</li><!-- /.menu-title -->
                    <li class="menu-item-has-children active dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Residents</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewMaleResidentList/${sadmin.sadminId}">Male</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewFemaleResidentList/${sadmin.sadminId}">Female</a></li>
                             <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/manageResidentList/${sadmin.sadminId}">All</a></li>
                        </ul>
                    </li>
                    
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Over 18</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewOver18MaleList/${sadmin.sadminId}">Male</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewOver18FemaleList/${sadmin.sadminId}">Female</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewOver18List/${sadmin.sadminId}">All</a></li>
                        </ul>
                    </li>
                    
                   	<li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Over 85</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewOver85MaleList/${sadmin.sadminId}">Male</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewOver85FemaleList/${sadmin.sadminId}">Female</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewOver85List/${sadmin.sadminId}">All</a></li>
                        </ul>
                    </li>
                    
                     <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Birth</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewMaleBirthList/${sadmin.sadminId}">Male</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewFemaleBirthList/${sadmin.sadminId}">Female</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/manageBirthList/${sadmin.sadminId}">All</a></li>
                        </ul>
                    </li>
                    
                    <li class="menu-item-has-children">
                        <a href="/ResidentManagementSystem/sadmin/manageDeathList/${sadmin.sadminId}"><i class="menu-icon fa fa-table"></i>Deceased</a>
                    </li>
                    
                     <li class="menu-title">NRC Registration Status</li><!-- /.menu-title -->
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Over 10 Non NRC</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewOver10MaleNonNRC/${sadmin.sadminId}">Male</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewOver10FemaleNonNRC/${sadmin.sadminId}">Female</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewOver10NonNRC/${sadmin.sadminId}">All</a></li>
                        </ul>
                    </li>
                    
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Below 10 Non NRC</a>
                        <ul class="sub-menu children dropdown-menu">
                             <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewBelow10MaleNonNRC/${sadmin.sadminId}">Male</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewBelow10FemaleNonNRC/${sadmin.sadminId}">Female</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewBelow10NonNRC/${sadmin.sadminId}">All</a></li>
                        </ul>
                    </li>
                    
              		<li class="menu-title">Vaccination Status</li><!-- /.menu-title -->
                     <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>BCG</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewBCGDone/${sadmin.sadminId}">Done</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewBCGNotYet/${sadmin.sadminId}">Not Yet</a></li>
                        </ul>
                    </li>
                      <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Five Vaccines</a>
                        <ul class="sub-menu children dropdown-menu">
                           <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewFiveVaccinesDone/${sadmin.sadminId}">Done</a></li>
                           <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewFiveVaccinesNotYet/${sadmin.sadminId}">Not Yet</a></li>
                        </ul>
                    </li>
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Vitamin A</a>
                        <ul class="sub-menu children dropdown-menu">
                           <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewVitaminADone/${sadmin.sadminId}">Done</a></li>
                           <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewVitaminANotYet/${sadmin.sadminId}">Not Yet</a></li>
                        </ul>
                    </li>
   
                    <li class="menu-item-has-children ">
                        <a href="/ResidentManagementSystem/sadmin/view9MonthsList/${sadmin.sadminId}"> <i class="menu-icon fa fa-table"></i>9 months babies</a>
                    </li>
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>HPV Vaccine</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewHPVVaccineDone/${sadmin.sadminId}">Done</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewHPVVaccineNotYet/${sadmin.sadminId}">Not Yet</a></li>
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
                    <a class="navbar-brand" href="./">Sub Admin (${sadmin.stateOrRegion.stateOrRegionName})</a>
                    <a class="navbar-brand hidden" href="./"><img src="images/logo2.png" alt="Logo"></a>
                    <a id="menuToggle" class="menutoggle"><i class="fa fa-bars"></i></a>
                </div>
            </div>
            <div class="top-right">
                <div class="header-menu">
                    <div class="header-left">
                        
                    </div>

                    <div class="user-area dropdown float-right">
                        <a href="#" class="dropdown-toggle active" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <img class="user-avatar rounded-circle" src="/ResidentManagementSystem/images/user.png" alt="User Avatar">
                        </a>
						 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
                        <div class="user-menu dropdown-menu">
                      
                        <%@ include file="myProfile.jsp" %>
                        
                            <div style="text-align:center;">
                             <a href="/ResidentManagementSystem/sadmin/getEditProfile"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>Edit</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/ResidentManagementSystem/sadmin/logout"><i class="fa fa-sign-out" aria-hidden="true"></i>Logout</a></div>
                      
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
           <form:form method="post" action="/ResidentManagementSystem/sadmin/editResident/${sadmin.sadminId}">
           					
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
					
							<div class="row form-group">
		                    <div class="col col-md-3"><label for="text-input" class=" form-control-label">Ward/Village Name</label></div>
		                    <div class="col-12 col-md-9">
			                    <form:select path="wardOrVillageId"  name="select" id="select" class="form-control">
			                    	<form:options items="${wardOrVillageList}" itemValue="wardOrVillageId" itemLabel="wardOrVillageName"/>    	
			                    </form:select>
			                    </div>
		                	</div>
		                	
							<div class="card-footer" style="background-color:#3A4D6F; color:black; text-align:center;">
							<a href="/ResidentManagementSystem/sadmin/manageResidentList/${sadmin.sadminId}" style="display:inline-block;color:white;float:left;"><i class="fa fa-angle-double-left" style="font-size:28px;" aria-hidden="true"></i></a><input type="Submit">
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
