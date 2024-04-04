<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Police Officer</title>
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
    <link href="https://fonts.googleapis.com/css?family=Poppins:400,500,700" rel="stylesheet" />
    <link rel="stylesheet" href="/ResidentManagementSystem/assets/css/search.css">
</head>

<body>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <!-- Left Panel -->
    <aside id="left-panel" class="left-panel">
        <nav class="navbar navbar-expand-sm navbar-default">
            <div id="main-menu" class="main-menu collapse navbar-collapse">
                <ul class="nav navbar-nav">
                  <li>
                        <a href=""><i class="menu-icon fa fa-laptop"></i></a>
                    </li>
                    <li>
                        <a href="/ResidentManagementSystem/policeOfficer/home"><i class="menu-icon fa fa-laptop"></i>Dashboard </a>
                    </li>
                    <li class="menu-title">Administrative divisions<!-- /.menu-title -->
                    
                     <li class="menu-item-has-children">
                        <a href="/ResidentManagementSystem/policeOfficer/viewStateOrRegion/${policeOfficer.policeOfficerId}"> <i class="menu-icon fa fa-table"></i>State/Regions</a>
                    </li>
                    
                    <li class="menu-item-has-children">
                        <a href="/ResidentManagementSystem/policeOfficer/viewTownship/${policeOfficer.policeOfficerId}"> <i class="menu-icon fa fa-table"></i>Townships</a>
                    </li>
                    
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Ward/Villages</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/policeOfficer/viewWard/${policeOfficer.policeOfficerId}">Ward</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/policeOfficer/viewVillage/${policeOfficer.policeOfficerId}">Village</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/policeOfficer/viewWardOrVillage/${policeOfficer.policeOfficerId}">All</a></li>
                        </ul>
                    </li>
                    
                    <li class="menu-title">Residents</li><!-- /.menu-title -->
                    
                    <li class="menu-item-has-children">
                        <a href="/ResidentManagementSystem/policeOfficer/home"> <i class="menu-icon fa fa-search"></i>Search</a>
                      
                    </li>
                    
                    <li class="menu-item-has-children active">
                        <a href="/ResidentManagementSystem/policeOfficer/manageCriminalRecordList/${policeOfficer.policeOfficerId}"> <i class="menu-icon fa fa-table"></i>Criminal Records</a>
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
                    <a class="navbar-brand" href="./">Police Officer<br>(${policeOfficer.township.townshipName})</a>
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
                             <a href="/ResidentManagementSystem/policeOfficer/getEditProfile"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>Edit</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/ResidentManagementSystem/policeOfficer/logout"><i class="fa fa-sign-out" aria-hidden="true"></i>Logout</a></div>
                      
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
                               <strong>	Edit Criminal Record </strong>
                            </div>
                            <div class="card-body card-block">
           <form:form method="post" action="/ResidentManagementSystem/policeOfficer/editCriminalRecord/${policeOfficer.policeOfficerId}">
           					
           					<form:hidden path="crimeId"/>
           					
           					<div class="row form-group">
		                    <div class="col col-md-3"><label for="text-input" class=" form-control-label">Criminal Section</label></div>
		                    <div class="col-12 col-md-9"><form:input path="crimeName" id="text-input" name="text-input"  class="form-control" required="true"/><small class="form-text text-muted"></small></div>
		                	</div>
	
							<div class="row form-group">
		                    <div class="col col-md-3"><label for="text-input" class=" form-control-label">Date of Crime</label></div>
		                    <div class="col-12 col-md-9"><form:input path="criminalRecordDate" id="text-input" name="text-input"  class="form-control" placeholder="" type="date" required="true"/><small class="form-text text-muted"></small></div>
		                	</div>
								
							<div class="row form-group">
							<div class="col col-md-3"><label for="email-input" class=" form-control-label">Police Officer's Sign</label></div>
							<div class="col-12 col-md-9"><form:input path="policeOfficerSign" id="text-input" name="text-input" class="form-control" required="true"/><small class="help-block form-text"></small></div><br>
							</div>
							
							<div class="row form-group">
		                    <div class="col col-md-3"><label for="text-input" class=" form-control-label">Place of Crime Scene</label></div>
		                    <div class="col-12 col-md-9"><form:input path="crimeScenePlace" id="text-input" name="text-input"  class="form-control" required="true"/><small class="form-text text-muted"></small></div>
		                	</div>
							
							<div class="row form-group">
		                    <div class="col col-md-3"><label for="text-input" class=" form-control-label">The offender's NRC No</label></div>
		                    <div class="col-12 col-md-9">
			                    <form:select path="residentId"  name="select" id="select" class="form-control">
			                    	<form:options items="${residentList}" itemValue="residentId" itemLabel="nrcNo"/>    	
			                    </form:select>
			                    </div>
		                	</div>
		                	
		                	<div class="row form-group">
                    		<div class="col col-md-3"><label class=" form-control-label">Status of The Trial</label></div>
                    		<div class="col col-md-9">
							<div class="form-check-inline form-check">
							<label for="inline-radio1" class="form-check-label">
							<form:radiobutton path="resultOfTheTrial" value="1" id="inline-radio1" name="inline-radios" class="form-check-input" required="true"/>Still Facing The Trial
							 </label>&nbsp;
							 <label for="inline-radio2" class="form-check-label ">
							<form:radiobutton path="resultOfTheTrial" value="2" id="inline-radio2" name="inline-radios" class="form-check-input" required="true"/>Losed The Trial<br>
							 </label>&nbsp;
							 <label for="inline-radio2" class="form-check-label ">
							<form:radiobutton path="resultOfTheTrial" value="3" id="inline-radio2" name="inline-radios" class="form-check-input" required="true"/>Won The Trial<br>
							 </label>
							</div>
							</div>
                   			 </div>
                   			 
		                	<form:hidden path="townshipId" value="${policeOfficer.townshipId}"/>
							
							<div class="card-footer" style="background-color:#3A4D6F; color:black; text-align:center;">
							<a href="/ResidentManagementSystem/policeOfficer/manageCriminalRecordList/${policeOfficer.policeOfficerId}" style="display:inline-block;color:white;float:left;"><i class="fa fa-angle-double-left" style="font-size:28px;" aria-hidden="true"></i></a><input type="Submit">
							</div>
				</form:form>
           </div>
            </div>
            </div>
					
                    
                </div>
                <!-- /Widgets -->
               
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
</body>
</html>