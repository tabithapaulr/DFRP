<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.database.util.DBsingletone"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Road Accident Prediction</title>
        <link rel="shortcut icon" type="image/x-icon" href="favicon.png" />
        <link href="css/master.css" rel="stylesheet">
        <link rel="stylesheet" href="plugins/iview/css/iview.css" type='text/css' media='all' />
        <link rel="stylesheet" href="plugins/iview/css/skin/style.css" type='text/css' media='all' />

        <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
        <script src= "js/jquery-migrate-1.2.1.js" ></script>
        <script src="js/jquery-ui.min.js"></script>
        <script src="js/bootstrap-3.1.1.min.js"></script>
        <script src="js/modernizr.custom.js"></script>
        <script src="js/highcharts.js" type="text/javascript"></script>
        <script src="js/highcharts-more.js" type="text/javascript"></script>
        <script src="js/exporting.js" type="text/javascript"></script>
    </head>
   
    <body>
        <div class="layout-theme animated-css"  data-header="sticky" data-header-top="200"  >

            <div class="header">
                <div class="container">
                    <div class="header-inner">
                        <div class="row">
                            <div class="col-md-4 col-xs-12">
                                <h2>
                                    Road Accident Prediction
                                </h2>
                            </div>
                            <div class="col-md-8 col-xs-12">
                                <div class="header-block">

                                </div>
                                <form class="hidden-md hidden-lg text-center" id="search-global-mobile" method="get">
                                    <input type="text" value="" id="search-mobile" name="s" >
                                    <button type="submit"><i class="icon fa fa-search"></i></button>
                                </form>
                            </div>
                        </div>
                    </div><!-- end header-inner-->
                </div><!-- end container-->

                <div class="top-nav ">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12  col-xs-12">
                                <div class="navbar yamm " >
                                    <div class="navbar-header hidden-md  hidden-lg  hidden-sm ">
                                        <button type="button" data-toggle="collapse" data-target="#navbar-collapse-1" class="navbar-toggle"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
                                        <a href="#" class="navbar-brand">Menu</a> </div>
                                    <div id="navbar-collapse-1" class="navbar-collapse collapse">
                                        <ul class="nav navbar-nav">
                                           <li><a href="index.html"  >Home </a> </li>
                     <li><a href="Alcoholbased.jsp">Prediction</a> </li>
                    <li><a href="classification.jsp"  >Classification </a> </li>
                    <li><a href="StateBasis.jsp"  >Comparison </a> </li>
                    <li><a href="predict.jsp">predict2017</a></li>
                    <li><a href="analysis.jsp">analysis</a></li> 
                    <li><a href="Accuracy.jsp">Accuracy</a></li>
                                        </ul>
                                        <form id="search-global-menu" class="hidden-xs hidden-sm" method="get">
                                            <input type="text" value="" id="search" name="s" >
                                            <button type="submit"><i class="icon-magnifier"></i></button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div><!--end top-nav -->
            </div><!-- HEADER END -->


            <div class="ui-title-page bg_title bg_transparent">
                <div class="container">
                    <div class="row">
                        <div class="col-xs-12">
                            <h1>Road Accident Prediction</h1>

                        </div>
                    </div>
                </div>
            </div><!-- end ui-title-page -->


            <div class="border_btm">
                <div class="container">
                    <div class="row">
                        <div class="col-xs-12">
                            <ol class="breadcrumb">
                                <li><a href="javascript:void(0);"><i class="icon icon-home color_primary"></i></a></li>
                                <li class="active">Road Accident Prediction</li>
                            </ol>
                        </div>
                    </div>
                </div>
            </div><!-- end breadcrumb -->


            <main class="main-content">
                <section class="section wow fadeInUp" data-wow-delay="1.5s">
                    <div class="container" >
                        <div class="row">
                            <div class="col-md-3 col-sm-4">
                                
                              
                                <select id="ctype" class="form-control" style="border: solid black 1px">
                                    <option value="Alcohol">Alcohol</option>
                                    <option value="Climate">Climate</option>
                                    <option value="Location">Location</option>
                                    <option value="VehicleDefect">Vehicle Defect</option>
                                    <option value="Junction">Junction</option>
                                </select>
                            </div>
                           
                            
                            <div class="col-md-1 col-sm-4" style="margin-top: 0.5%"><button type="button" class="btn btn-primary btn-sm" onclick="getdata()">Get Accuracy</button></div>
                        
                            <div class="col-md-3 col-sm-4"></div>
                            <div class="col-md-12 col-sm-12">
                                <div class="row">
                                <div class='col-md-6' id="container" ></div>
                                <div class='col-md-6' id="container1"></div>
                                
                            </div>
                        </div>
                    </div>
                    
                </section><!-- end section -->






            </main><!-- end main-content -->




            <footer class="footer">


                <div class="footer__bottom">
                    <span class="copyright">? Copyrights 2018 Road Accident Prediction</span>

                </div>
            </footer>


        </div><!-- end layout-theme -->

        <span class="scroll-top bg-color_second"> <i class="fa fa-angle-up"> </i></span>
        <div id="page-preloader">
            <div class="spinner-loader"> Road Accident Prediction
                <div class="heartbeat-loader"></div>
            </div>
        </div>

        <!-- SCRIPTS -->
        <script type="text/javascript" src="plugins/isotope/jquery.isotope.min.js"></script>
        <script src="js/waypoints.min.js"></script>
        <script src="plugins/bxslider/jquery.bxslider.min.js"></script>
        <script src="plugins/prettyphoto/js/jquery.prettyPhoto.js"></script>
        <script src="../../../cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
        <script src="plugins/datetimepicker/jquery.datetimepicker.js"></script>
        <script src="plugins/jelect/jquery.jelect.js"></script>
        <script src="plugins/nouislider/jquery.nouislider.all.min.js"></script>

        <!--THEME-->
        <script src="js/cssua.min.js"></script>
        <script src="js/wow.min.js"></script>
        <script src="js/custom.js"></script>

        <script>

                              

                                function getdata()
                                {
                                   var ctype = $("#ctype").val();
                                  
                                   
                                   
                                        $.ajax({
                                            url: "Accuracy",
                                            dataType: 'json',
                                            type: 'POST',
                                            data: {ctype : ctype},
                                            success: function(data) {
                                            
                                           var chart = Highcharts.chart('container', {
                                            title: {
                                                text: ctype+ '  Based Accuracy'
                                            },
                                            credits: {
                                                enabled: false
                                            },
                                            exporting: {
                                                enabled: false
                                            },
                                            chart: {
                                                type: 'pie',
                                                options3d: {
                                                    enabled: true,
                                                    alpha: 45,
                                                    beta: 0
                                                }
                                            },
                                            plotOptions: {
                                                pie: {
                                                    allowPointSelect: true,
                                                    cursor: 'pointer',
                                                    depth: 35,
                                                    dataLabels: {
                                                        enabled: true,
                                                        format: '{point.name}'
                                                    }
                                                }
                                            },
                                            subtitle: {
                                                text: ''
                                            },
                                            xAxis: {
                                                categories: data.aray2
                                            },
                                            yAxis: {
                                                title: {
                                                    text: 'Yield',
                                                    rotation: 270,
                                                    margin: 50,
                                                }
                                            },
                                            series: data.aray1
                                        });
                                       var chart1 = Highcharts.chart('container1', {
                                            title: {
                                                text: ctype+ '  Based Accuracy'
                                            },
                                            credits: {
                                                enabled: false
                                            },
                                            exporting: {
                                                enabled: false
                                            },
                                            chart: {
                                                type: 'pie',
                                                options3d: {
                                                    enabled: true,
                                                    alpha: 45,
                                                    beta: 0
                                                }
                                            },
                                            plotOptions: {
                                                pie: {
                                                    allowPointSelect: true,
                                                    cursor: 'pointer',
                                                    depth: 35,
                                                    dataLabels: {
                                                        enabled: true,
                                                        format: '{point.name}'
                                                    }
                                                }
                                            },
                                            subtitle: {
                                                text: ''
                                            },
                                            xAxis: {
                                                categories: data.cat,
                                            },
                                            yAxis: {
                                                title: {
                                                    text: 'Yield',
                                                    rotation: 270,
                                                    margin: 50,
                                                }
                                            },
                                            series: [{
                                                    type: 'pie',
                                                    colorByPoint: true,
                                                    data: data.dat,
                                                    showLegend: false,
                                            }]
                                        });

                                            }
                                        });

                                    }
                                                          
        </script>

    </body>

</html>
