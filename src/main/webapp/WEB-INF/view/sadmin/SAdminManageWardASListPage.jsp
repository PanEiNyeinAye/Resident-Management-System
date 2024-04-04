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
                    
                     <li class="menu-item-has-children active dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Administrators</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/manageWardASList/${sadmin.sadminId}">Ward</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/manageVillageASList/${sadmin.sadminId}">Village</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/sadmin/viewWardOrVillageASList/${sadmin.sadminId}">All</a></li>
                        </ul>
                    </li>
                    
                    
                    <li class="menu-title">Residents</li><!-- /.menu-title -->
                    <li class="menu-item-has-children dropdown">
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
                               <div class="card" >
                             <div class="card-header" style="text-align:right; background-color:#3A4D6F; color:#fff;">
                            	<div style="text-align:center;position:relative;"><Strong>Ward Administrators of ${sadmin.stateOrRegion.stateOrRegionName}</Strong></div>
         						<a href="/ResidentManagementSystem/sadmin/getAddWardAS/${sadmin.sadminId}" style="color:#fff"><i class="material-icons" style="color:#fff">&#xE147;</i> Add New Ward Administrator</a>
                            </div>
                            <div class="card-body">
                             <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
                                <table id="bootstrap-data-table" class="table table-striped table-bordered" style="text-align:center;">
                                    <thead>
                    <tr>
						<th>No.</th>
                        <th>Name</th>
						<th>Employee No</th>
						<th>Position</th>
						<th>Email</th>
						<th>Password</th>
						<th>Phone No.</th>
						<th>Ward/Village Name</th>
						<th>Edit</th>
                    </tr>
                </thead>
                <tbody>
                 <c:forEach var="wardas" items="${wardASListBySAdmin}" varStatus="wardasCount">
                    <tr>
                    	<td><c:out value ="${wardasCount.count}"/></td>
                    	<td><c:out value ="${wardas.wardASName}"/></td>
						<td><c:out value ="${wardas.wardASEmployeeNo}"/></td>
						<td><c:out value ="${wardas.wardASPosition}"/></td>
						<td><c:out value ="${wardas.wardASEmail}"/></td>
						<td><c:out value ="${wardas.wardASPassword}"/></td>
						<td><c:out value ="${wardas.wardASPhoneNo}"/></td>
						<td><c:out value ="${wardas.wardOrVillage.wardOrVillageName}"/></td>
						<td>
                            <a href="/ResidentManagementSystem/sadmin/getEditWardAS/${wardas.wardASId}/${sadmin.sadminId}" class="fa fa-pencil-square-o" aria-hidden="true"></a>
                        </td>				
					</tr>	
					 </c:forEach>				
                </tbody>
                                </table>
                                <a href="/ResidentManagementSystem/sadmin/home" style="display:inline-block;color:#3A4D6F;float:left;"><i class="fa fa-angle-double-left" style="font-size:28px;" aria-hidden="true"></i></a>
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
