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
    <link rel="stylesheet" href="/ResidentManagementSystem/assets/css/lib/datatable/dataTables.bootstrap.min.css">
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
        <div class="content" style="background-color:#3A4D6F;">
            <!-- Animated -->
            <div class="animated fadeIn">
                <!-- Widgets  -->
               
                <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
            
           
                            <div class="card-body card-block">
                            <div class="s013">
       <form:form action="/ResidentManagementSystem/policeOfficer/search/${policeOfficer.policeOfficerId}" modelAttribute="search">
        <fieldset>
          <legend style="color:#06213F;">Quickly search the resident's information!</legend>
        </fieldset>
        
        <div class="inner-form">
          <div class="left">
         
          <div class="input-wrap second">
              <div class="input-field second">
                <label>Choose the one below!</label>
                <div class="input-select">
                  <form:select path="searchType" name="choices-single-defaul">
                    <form:option value="nrc" label="NRC No."/>
                    <form:option value="name" label="Name"/>
                  </form:select>
                 
                </div>
              </div>
            </div>
            <div class="input-wrap first">
              <div class="input-field first">
                <label>Search by Name or NRC Number!</label>
                <form:input path="nameOrNrcValue" placeholder="Search here..."/>
              </div>
            </div>
            
          </div>
          <input type="Submit" class="btn-search" value="SEARCH">
        </div>
      </form:form>
    </div>
                     </div>
                    
            <div class="card">
           
                            <div class="card-body card-block">
                            
            <table id="bootstrap-data-table" class="table table-striped table-bordered">
                <thead>
                    <tr>
						<th>No.</th>
                        <th>Name</th>
						<th>Gender</th>
						<th>Ward/Village Name</th>
						<th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                 <c:forEach var="resident" items="${searchedResidents}" varStatus="residentCount">
                    <tr>
                    	<td><c:out value ="${residentCount.count}"/></td>
                    	<td><c:out value="${resident.residentName}"/></td>
                    	<td><c:out value ="${resident.gender}"/></td>
						<td><c:out value ="${resident.wardOrVillageName}"/></td>
						<td>
                            <a href="/ResidentManagementSystem/policeOfficer/getSearchedResidentDetails/${policeOfficer.policeOfficerId}/${resident.residentId}" class="fa fa-eye" aria-hidden="true"></a>
                        </td>				
					</tr>	
					 </c:forEach>				
                </tbody>
            </table>
           
            </div>
            
            </div>
       
    </div>
                     
					
                    
                </div>
                <!-- /Widgets -->
               
            </div>
            <!-- .animated -->
       
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
    <script src="/ResidentManagementSystem/assets/js/lib/data-table/vfs_fonts.js"></script>
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
