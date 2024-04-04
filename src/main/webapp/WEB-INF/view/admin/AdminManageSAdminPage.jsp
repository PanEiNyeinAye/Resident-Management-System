<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Admin Manages Sub Admins</title>
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
	.card-header{
	 background: #06213F;
	}
	
	.table-wrapper {
        background: #fff;
        padding: 20px 25px;
        margin: 30px 0;
		border-radius: 3px;
        box-shadow: 0 1px 1px rgba(0,0,0,.05);
    }
	.table-title {        
		padding-bottom: 15px;
		color: #fff;
		padding: 16px 30px;
		margin: -20px -25px 10px;
		border-radius: 3px 3px 0 0;
    }
    .table-title h2 {
		margin: 5px 0 0;
		font-size: 24px;
	}
	.table-title .btn-group {
		float: right;
	}
	.table-title .btn {
		color: #fff;
		float: right;
		font-size: 13px;
		border: none;
		min-width: 50px;
		border-radius: 2px;
		border: none;
		outline: none !important;
		margin-left: 10px;
	}
	.table-title .btn i {
		float: left;
		font-size: 21px;
		margin-right: 5px;
	}
	.table-title .btn span {
		float: left;
		margin-top: 2px;
	}
    table.table tr th, table.table tr td {
        border-color: #e9e9e9;
		padding: 12px 15px;
		vertical-align: right;
    }
	table.table tr th:first-child {
		width: 60px;
	}
	table.table tr th:last-child {
		width: 100px;
	}
    table.table-striped tbody tr:nth-of-type(odd) {
    	background-color: #fcfcfc;
	}
	table.table-striped.table-hover tbody tr:hover {
		background: #f5f5f5;
	}
    table.table th i {
        font-size: 13px;
        margin: 0 5px;
        cursor: pointer;
    }	
    table.table td:last-child i {
		opacity: 0.9;
		font-size: 22px;
        margin: 0 5px;
    }
	table.table td a {
		font-weight: bold;
		color: #566787;
		display: inline-block;
		text-decoration: none;
		outline: none !important;
	}
	table.table td a:hover {
		color: #2196F3;
	}
	table.table td a.edit {
        color: #FFC107;
    }
    table.table td a.delete {
        color: #F44336;
    }
    table.table td i {
        font-size: 19px;
    }
	table.table .avatar {
		border-radius: 50%;
		vertical-align: middle;
		margin-right: 10px;
	}
  
    .hint-text {
        float: left;
        margin-top: 10px;
        font-size: 13px;
    }    
	/* Custom checkbox */
	.custom-checkbox {
		position: relative;
	}
	.custom-checkbox input[type="checkbox"] {    
		opacity: 0;
		position: absolute;
		margin: 5px 0 0 3px;
		z-index: 9;
	}
	.custom-checkbox label:before{
		width: 18px;
		height: 18px;
	}
	.custom-checkbox label:before {
		content: '';
		margin-right: 10px;
		display: inline-block;
		vertical-align: text-top;
		background: white;
		border: 1px solid #bbb;
		border-radius: 2px;
		box-sizing: border-box;
		z-index: 2;
	}
	.custom-checkbox input[type="checkbox"]:checked + label:after {
		content: '';
		position: absolute;
		left: 6px;
		top: 3px;
		width: 6px;
		height: 11px;
		border: solid #000;
		border-width: 0 3px 3px 0;
		transform: inherit;
		z-index: 3;
		transform: rotateZ(45deg);
	}
	.custom-checkbox input[type="checkbox"]:checked + label:before {
		border-color: #03A9F4;
		background: #03A9F4;
	}
	.custom-checkbox input[type="checkbox"]:checked + label:after {
		border-color: #fff;
	}
	.custom-checkbox input[type="checkbox"]:disabled + label:before {
		color: #b8b8b8;
		cursor: auto;
		box-shadow: none;
		background: #ddd;
	}
	/* Modal styles */
	.modal .modal-dialog {
		max-width: 400px;
	}
	.modal .modal-header, .modal .modal-body, .modal .modal-footer {
		padding: 20px 30px;
	}
	.modal .modal-content {
		border-radius: 3px;
	}
	.modal .modal-footer {
		background: #ecf0f1;
		border-radius: 0 0 3px 3px;
	}
    .modal .modal-title {
        display: inline-block;
    }
	.modal .form-control {
		border-radius: 2px;
		box-shadow: none;
		border-color: #dddddd;
	}
	.modal textarea.form-control {
		resize: vertical;
	}
	.modal .btn {
		border-radius: 2px;
		min-width: 100px;
	}	
	.modal form label {
		font-weight: normal;
	}
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
                        <a href="/ResidentManagementSystem/admin/home"><i class="menu-icon fa fa-laptop"></i>Dashboard </a>
                    </li>
                    <li class="menu-title">Administrative divisions<!-- /.menu-title -->
                    
                    <li class="menu-item-has-children">
                        <a href="/ResidentManagementSystem/admin/viewStateOrRegion"> <i class="menu-icon fa fa-table"></i>State/Regions</a>
                    </li>
                    
                    <li class="menu-item-has-children">
                        <a href="/ResidentManagementSystem/admin/viewTownship"> <i class="menu-icon fa fa-table"></i>Townships</a>
                    </li>
                    
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Ward/Villages</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewWard">Ward</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewVillage">Village</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewWardOrVillage">All</a></li>
                        </ul>
                    </li>
                    
                    <li class="menu-title">Staffs</li><!-- /.menu-title -->
                    
                     <li class="menu-item-has-children active">
                        <a href="/ResidentManagementSystem/admin/manageSAdminList"> <i class="menu-icon fa fa-table"></i>Sub Admin</a>
                    </li>
                    
                     <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Administrators</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/manageWardASList">Ward</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/manageVillageASList">Village</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewWardOrVillageASList">All</a></li>
                        </ul>
                    </li>
                    
                        <li class="menu-item-has-children">
                        <a href="/ResidentManagementSystem/admin/managePoliceOfficerList" > <i class="menu-icon fa fa-table"></i>Police Officers</a>
                    </li>
                    
                     <li class="menu-item-has-children ">
                        <a href="/ResidentManagementSystem/admin/manageGASList"> <i class="menu-icon fa fa-table"></i>GAS</a>
                    </li>
                    
                     
                    
                    <li class="menu-title">Residents</li><!-- /.menu-title -->
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Residents</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewMaleResidentList">Male</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewFemaleResidentList">Female</a></li>
                             <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/manageResidentList">All</a></li>
                        </ul>
                    </li>
                    
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Over 18</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewOver18MaleList">Male</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewOver18FemaleList">Female</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewOver18List">All</a></li>
                        </ul>
                    </li>
                    
                   	<li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Over 85</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewOver85MaleList">Male</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewOver85FemaleList">Female</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewOver85List">All</a></li>
                        </ul>
                    </li>
                    
                     <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Birth</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewMaleBirthList">Male</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewFemaleBirthList">Female</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/manageBirthList">All</a></li>
                        </ul>
                    </li>
                    
                     <li class="menu-item-has-children">
                        <a href="/ResidentManagementSystem/admin/manageDeathList"> <i class="menu-icon fa fa-table"></i>Deceased</a>
                    </li>
                    
                     <li class="menu-title">NRC Registration Status</li><!-- /.menu-title -->
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Over 10 Non NRC</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewOver10MaleNonNRC">Male</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewOver10FemaleNonNRC">Female</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewOver10NonNRC">All</a></li>
                        </ul>
                    </li>
                    
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Below 10 Non NRC</a>
                        <ul class="sub-menu children dropdown-menu">
                             <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewBelow10MaleNonNRC">Male</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewBelow10FemaleNonNRC">Female</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewBelow10NonNRC">All</a></li>
                        </ul>
                    </li>
                    
              		<li class="menu-title">Vaccination Status</li><!-- /.menu-title -->
                     <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>BCG</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewBCGDone">Done</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewBCGNotYet">Not Yet</a></li>
                        </ul>
                    </li>
                      <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Five Vaccines</a>
                        <ul class="sub-menu children dropdown-menu">
                           <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewFiveVaccinesDone">Done</a></li>
                           <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewFiveVaccinesNotYet">Not Yet</a></li>
                        </ul>
                    </li>
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Vitamin A</a>
                        <ul class="sub-menu children dropdown-menu">
                           <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewVitaminADone">Done</a></li>
                           <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewVitaminANotYet">Not Yet</a></li>
                        </ul>
                    </li>
   
                    <li class="menu-item-has-children ">
                        <a href="/ResidentManagementSystem/admin/view9MonthsList"> <i class="menu-icon fa fa-table"></i>9 months babies</a>
                    </li>
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>HPV Vaccine</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewHPVVaccineDone">Done</a></li>
                            <li><i class="fa fa-table"></i><a href="/ResidentManagementSystem/admin/viewHPVVaccineNotYet">Not Yet</a></li>
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
                    <a class="navbar-brand" href="./">Admin</a>
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
                             <a href="/ResidentManagementSystem/admin/getEditProfile"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>Edit</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/ResidentManagementSystem/admin/logout"><i class="fa fa-sign-out" aria-hidden="true"></i>Logout</a></div>
                      
                        </div>
                    </div>

                </div>
            </div>
        </header>
        <!-- /#header -->
        <!-- Header-->
        
        <!-- Content -->
        <div class="content">
            <!-- Animated -->
            <div class="animated fadeIn">
                <!-- Widgets  -->
                    <div class="row">
            <div class="card">
                            <div class="card-header" style="text-align:right; background-color:#3A4D6F; color:#fff;">
                            	<div style="text-align:center;position:relative;"><Strong>Sub Admins of Myanmar</Strong></div>
         						<a href="/ResidentManagementSystem/admin/getAddSAdmin" style="color:#fff"><i class="material-icons" style="color:#fff">&#xE147;</i> Add New Sub Admin</a>
                            </div>
                            <div class="card-body">
                           
            <table id="bootstrap-data-table" class="table table-striped table-bordered" style="text-align:center;">
                <thead>
                    <tr>
						<th>No.</th>
                        <th>Name</th>
						<th>Employee Number</th>
						<th>Email</th>
						<th>Password</th>
						<th>State/Region Name</th>
						<th>Edit</th>
                    </tr>
                </thead>
                <tbody>
                 <c:forEach var="sadmin" items="${sadminListByAdmin}" varStatus="sadminCount">
                    <tr>
                    	<td><c:out value ="${sadminCount.count}"/></td>
                    	<td><c:out value ="${sadmin.sadminName}"/></td>
                    	<td><c:out value ="${sadmin.sadminEmployeeNo}"/></td>
						<td><c:out value ="${sadmin.sadminEmail}"/></td>
						<td><c:out value ="${sadmin.sadminPassword}"/></td>
						<td><c:out value ="${sadmin.stateOrRegion.stateOrRegionName}"/></td>
						<td>
                            <a href="/ResidentManagementSystem/admin/getEditSAdmin/${sadmin.sadminId}" class="fa fa-pencil-square-o" aria-hidden="true"></a>
                        </td>				
					</tr>	
					 </c:forEach>				
                </tbody>
            </table>
           <a href="/ResidentManagementSystem/admin/home" style="display:inline-block;color:#3A4D6F;float:left;"><i class="fa fa-angle-double-left" style="font-size:28px;" aria-hidden="true"></i></a>
            </div>
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
