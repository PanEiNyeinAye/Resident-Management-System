<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Police Officer Edit Profile</title>
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
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
						 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
                        <div class="user-menu">
                      	 <form:form method="post" action="/ResidentManagementSystem/policeOfficer/editProfile">
           					
           					
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
							<a href="/ResidentManagementSystem/policeOfficer/home" style="display:inline-block;"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
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
