<%@ page import="java.util.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>GAS View Residents</title>
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
    <link rel="stylesheet" href="/ResidentManagementSystem/assets/css/lib/datatable/dataTables.bootstrap.min.css">
    <link rel="stylesheet" href="/ResidentManagementSystem/assets/css/style.css">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>

    <!-- <script type="text/javascript" src="https://cdn.jsdelivr.net/html5shiv/3.7.3/html5shiv.min.js"></script> -->

</head>
<body>
   <!-- Left Panel -->
     <aside id="left-panel" class="left-panel">
        <nav class="navbar navbar-expand-sm navbar-default">
            <div id="main-menu" class="main-menu collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="/ResidentManagementSystem/gas/home"><i class="menu-icon fa fa-laptop"></i>Dashboard </a>
                    </li>
                    
                    <li class="menu-title">Administrative divisions<!-- /.menu-title -->
                    
                   <li class="menu-item-has-children">
                        <a href="/ResidentManagementSystem/gas/viewStateOrRegion/${gas.gasId}"> <i class="menu-icon fa fa-table"></i>State/Regions</a>
                    </li>
                    
                    <li class="menu-item-has-children">
                        <a href="/ResidentManagementSystem/gas/viewTownship/${gas.gasId}"> <i class="menu-icon fa fa-table"></i>Townships</a>
                    </li>
                    
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Ward/Villages</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewWard/${gas.gasId}">Ward</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewVillage/${gas.gasId}">Village</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewWardOrVillage/${gas.gasId}">All</a></li>
                        </ul>
                    </li>
                    
                    <li class="menu-title">Staffs</li><!-- /.menu-title -->
                    
                     <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Administrators</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/manageWardASList/${gas.gasId}">Ward</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/manageVillageASList/${gas.gasId}">Village</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewWardOrVillageASList/${gas.gasId}">All</a></li>
                        </ul>
                    </li>
                    
                     <li class="menu-title">Residents</li><!-- /.menu-title -->  
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Residents</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewMaleResidentList/${gas.gasId}">Male</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewFemaleResidentList/${gas.gasId}">Female</a></li>
                             <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/manageResidentList/${gas.gasId}">Total</a></li>
                        </ul>
                    </li>
                    
                   <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Over 18</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewOver18MaleList/${gas.gasId}">Male</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewOver18FemaleList/${gas.gasId}">Female</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewOver18List/${gas.gasId}">All</a></li>
                        </ul>
                    </li>
                    
                   	<li class="menu-item-has-children active dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Over 85</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewOver85MaleList/${gas.gasId}">Male</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewOver85FemaleList/${gas.gasId}">Female</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewOver85List/${gas.gasId}">All</a></li>
                        </ul>
                    </li>
                    
                     <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Birth</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewMaleBirthList/${gas.gasId}">Male</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewFemaleBirthList/${gas.gasId}">Female</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/manageBirthList/${gas.gasId}">All</a></li>
                        </ul>
                    </li>
                    
                     <li class="menu-item-has-children">
                        <a href="/ResidentManagementSystem/gas/manageDeathList/${gas.gasId}"> <i class="menu-icon fa fa-table"></i>Deceased</a>
                    </li>
                    
                     <li class="menu-title">NRC Registration Status</li><!-- /.menu-title -->
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Over 10 Non NRC</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewOver10MaleNonNRC/${gas.gasId}">Male</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewOver10FemaleNonNRC/${gas.gasId}">Female</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewOver10NonNRC/${gas.gasId}">All</a></li>
                        </ul>
                    </li>
                    
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Below 10 Non NRC</a>
                        <ul class="sub-menu children dropdown-menu">
                             <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewBelow10MaleNonNRC/${gas.gasId}">Male</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewBelow10FemaleNonNRC/${gas.gasId}">Female</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewBelow10NonNRC/${gas.gasId}">All</a></li>
                        </ul>
                    </li>
                    
              		<li class="menu-title">Vaccination Status</li><!-- /.menu-title -->
                     <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>BCG</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewBCGDone/${gas.gasId}">Done</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewBCGNotYet/${gas.gasId}">Not Yet</a></li>
                        </ul>
                    </li>
                      <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Five Vaccines</a>
                        <ul class="sub-menu children dropdown-menu">
                           <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewFiveVaccinesDone/${gas.gasId}">Done</a></li>
                           <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewFiveVaccinesNotYet/${gas.gasId}">Not Yet</a></li>
                        </ul>
                    </li>
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Vitamin A</a>
                        <ul class="sub-menu children dropdown-menu">
                           <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewVitaminADone/${gas.gasId}">Done</a></li>
                           <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewVitaminANotYet/${gas.gasId}">Not Yet</a></li>
                        </ul>
                    </li>
   
                    <li class="menu-item-has-children ">
                        <a href="/ResidentManagementSystem/gas/view9MonthsList/${gas.gasId}"> <i class="menu-icon fa fa-table"></i>9 months babies</a>
                    </li>
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>HPV Vaccine</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewHPVVaccineDone/${gas.gasId}">Done</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/gas/viewHPVVaccineNotYet/${gas.gasId}">Not Yet</a></li>
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
                    <a class="navbar-brand" href="./">GAS (${gas.township.townshipName})</a>
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
                             <a href="/ResidentManagementSystem/gas/getEditProfile"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>Edit</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/ResidentManagementSystem/gas/logout"><i class="fa fa-sign-out" aria-hidden="true"></i>Logout</a></div>
                      
                        </div>
                    </div>

                </div>
            </div>
        </header>
        <!-- /#header -->
        <!-- Header-->


        <div class="content">
            <div class="animated fadeIn">
                <div class="row">

                                     <div class="col-md-12">
                        <div class="card" >
                            <div class="card-header" style="text-align:center; background-color:#3A4D6F; color:#fff;">
                                <strong class="card-title">85 years old and over 85 residents of ${gas.township.townshipName}</strong>
                            </div>
                            <div class="card-body">
                             <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
                                <table id="bootstrap-data-table" class="table table-striped table-bordered" style="text-align:center;">
               <thead>
                    <tr>
						<th>No.</th>
                        <th>Name</th>
						<th>Gender</th>
						<th>DOB</th>
						<th>NRC No.</th>
						<th>Father's Name</th>
						<th>Mother's Name</th>
						<th>Ethnicity</th>
						<th>Note</th>
						<th>Email</th>
						<th>Password</th>
						<th>Ward/Village Name</th>
                    </tr>
                </thead>
                <tbody>
                 <c:forEach var="resident" items="${over85List}" varStatus="residentCount">
                    <tr>
                    	<td><c:out value ="${residentCount.count}"/></td>
                    	<td><c:out value ="${resident.residentName}"/></td>
						<td><c:out value ="${resident.gender}"/></td>
						<td><c:out value ="${resident.dob}"/></td>
						<td><c:out value ="${resident.nrcNo}"/></td>
						<td><c:out value ="${resident.fatherName}"/></td>
						<td><c:out value ="${resident.motherName}"/></td>
						<td><c:out value ="${resident.ethnicity}"/></td>
						<td><c:out value ="${resident.note}"/></td>	
						<td><c:out value ="${resident.residentEmail}"/></td>
						<td><c:out value ="${resident.residentPassword}"/></td>
						<td><c:out value ="${resident.wardOrVillage.wardOrVillageName}"/></td>				
					</tr>	
					 </c:forEach>				
                </tbody>
            </table>
            <a href="/ResidentManagementSystem/gas/home" style="display:inline-block;color:#3A4D6F;float:left;"><i class="fa fa-angle-double-left" style="font-size:28px;" aria-hidden="true"></i></a>
                            </div>
                        </div>
                    </div>


                </div>
            </div><!-- .animated -->
        </div><!-- .content -->


        <div class="clearfix"></div>

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

    </div><!-- /#right-panel -->

    <!-- Right Panel -->

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
