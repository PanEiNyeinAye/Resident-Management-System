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
  <style>
	.card-header{
	background-color:#3A4D6F;
	}
	</style>
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
                    
                    <li class="menu-item-has-children active">
                        <a href="/ResidentManagementSystem/policeOfficer/home"> <i class="menu-icon fa fa-search"></i>Search</a>
                      
                    </li>
                    
                     <li class="menu-item-has-children">
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
               
                <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
                   
                          <div class="row">
                          <c:if test="${searchedResident.isCriminalResident eq true}">
                           <div class="card" style="left:190px;">
                          <div class="card-header" style="color:#fff; text-align:center;background-color:#C41E3A;">
                               <a href="/ResidentManagementSystem/policeOfficer/home" style="display:inline-block;color:#fff;float:left;"><i class="fa fa-angle-double-left" style="font-size:28px;" aria-hidden="true"></i></a>		
                               <strong>About ${searchedResident.residentName} </strong>
                            </div>
                            <div class="card-body card-block">
                            
		                    Name : ${searchedResident.residentName}<br>
		                    <hr>
		                    Gender : ${searchedResident.gender}<br>
		                    <hr>
		                	Date of Birth : ${searchedResident.dob}<br>
		                	<hr>
							National Registration Card(NRC) No. : ${searchedResident.nrcNo}<br>
							<hr>
							Father's Name : ${searchedResident.fatherName}<br>
							<hr>
							Mother's Name : ${searchedResident.motherName}<br>
							<hr>
							Ethnicity : ${searchedResident.ethnicity}<br>
							<hr>
							Ward/Village Name : ${searchedResident.wardOrVillageName}<br>
							<hr>
							<div style="color:#C41E3A; font-weight: bold;">Criminal Record : ${searchedResident.crimeName}</div><br>
							
							
							
           </div>
               </div>
                          </c:if>
                          <c:if test="${searchedResident.isCriminalResident eq false}">
                           <div class="card" style="left:190px;">
                            <div class="card-header" style="color:#fff; text-align:center; background-color:#3A4D6F;">
                               <a href="/ResidentManagementSystem/policeOfficer/home" style="display:inline-block;color:#fff;float:left;"><i class="fa fa-angle-double-left" style="font-size:28px;" aria-hidden="true"></i></a>		
                               <strong>About ${searchedResident.residentName} </strong>
                            </div>
                            <div class="card-body card-block">
                            
		                    Name : ${searchedResident.residentName}<br>
		                    <hr>
		                    Gender : ${searchedResident.gender}<br>
		                    <hr>
		                	Date of Birth : ${searchedResident.dob}<br>
		                	<hr>
							National Registration Card(NRC) No. : ${searchedResident.nrcNo}<br>
							<hr>
							Father's Name : ${searchedResident.fatherName}<br>
							<hr>
							Mother's Name : ${searchedResident.motherName}<br>
							<hr>
							Ethnicity : ${searchedResident.ethnicity}<br>
							<hr>
							Ward/Village Name : ${searchedResident.wardOrVillageName}<br>
							
           </div>
               </div>
                            </c:if>
                            
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
