<html>
<head>
	<title>VMI`s - File Not Fund</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	  <base href="./assets/">
	<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link href="css/font-awesome.css" rel="stylesheet" type="text/css" />


	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<style>
		.navbar-brand>img {
		    max-width: 75%;
		    margin-left: -5px;
		}
		.navbar-default {
		   background: rgb(255,50,54);
    background: -moz-linear-gradient(left, rgba(255,50,54,1) 0%, rgba(255,89,92,1) 58%, rgba(255,255,255,1) 92%);
    background: -webkit-linear-gradient(left, rgba(255,50,54,1) 0%,rgba(255,89,92,1) 58%,rgba(255,255,255,1) 92%);
    background: linear-gradient(to right, rgba(255,50,54,1) 0%,rgba(255,89,92,1) 58%,rgba(255,255,255,1) 92%);
    filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ff3236', endColorstr='#ffffff',GradientType=1 );
		}
		.navbar {
		    position: fixed;
		    width: 100%;
		    z-index: 999;
		}
		.cubNavBar {
		    margin-bottom: 0px;
		    min-height: 65px;
		}
		.martopm15{margin-top:-15px}
		.martop7{margin-top:7px}
		.main-content{margin-top:200px;text-align:center;}
		.lightgray{color:#8a8a8a}
		@media only screen and (max-width: 320px) {
		    .navbar-brand {
		        height:0px !important;
		        padding:0px 15px !important;
		    }
		    .navbar-brand>img {
			    width: 75%;
			    margin-left: -5px;
			    margin-top: -40px;
			}
		}
		.navbar-right li a{color:#fff !important;}
	</style>
</head>
<body>
  <header>
  	<nav class="navbar navbar-default navigation-clean-search cubNavBar">
        <div class="container-fluid">
        
         <div class="navbar-header">
         <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span> 
	      </button>
	      	<a class="navbar-brand" href="#"><img src="img/" class="martopm15"/></a>
    	 </div>
    	 <div class="collapse navbar-collapse martop7" id="myNavbar">
	         <ul class="nav navbar-nav navbar-right">
		        <li>
		        <#if Session.usrId??>
		        <a href="../bankerinbox"><span class="glyphicon glyphicon-home"></span></a>
		        <#else>
		         <a href="../loaninfo"><span class="glyphicon glyphicon-home"></span></a>
		        </#if>
		        </li>
		        
		      </ul>
	      </div>
        </div>
    </nav>

   
  </header>    
  <section>
  	<div class="container-fluid">
  		<div class="row">
  			<div class="col-md-12 col-xs-12 col-lg-12 col-sm-12">
  				<div class="col-md-offset-3 col-sm-offset-0 col-xs-offset-0 col-md-6 col-lg-6 col-xs-12 col-sm-12">
  					<div class="main-content">
  						
  						<h3 class="text-bold text-center">PAGE NOT FOUND</h3>
  						<h4 class="lightgray text-center text-bold">The Page you were looking for could not be found.</h4>  						
  					</div>
  				</div>
  			</div>
  		</div>
  	</div>
  </section> 
</body>
</html>
