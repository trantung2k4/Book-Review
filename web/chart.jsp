<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>RING!-ADMIN - Chart</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/price-range.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
	<link href="css/main.css" rel="stylesheet">
	<link href="css/responsive.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet" />
    <link href="css/jquery-ui.min.css" rel="stylesheet" />
       
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
    
</head>

<body> 
    <jsp:include page="adminNavigation.jsp"></jsp:include>
    
    <br>
    <div class="container">
		<div class="row">
            <div class="manager-title">
                <h2><i class="fa fa-area-chart">&nbsp;</i>Đồ thị dữ liệu</h2>
            </div>
		</div>
	</div> 

    <section>
		<div class="container">
			<div class="row">
                <div class="col-sm-12">
                    <div class="panel panel-default  mb-4">
                        <div class="panel-heading">
                            <i class="fa fa-area-chart" aria-hidden="true"></i>
                            Biểu đồ doanh số theo tháng
                        </div>
                        <div class="panel-body"><canvas id="myAreaChart" width="100%" height="30"></canvas></div>
                    </div>
                </div>
            </div>
            <div class="row">
            	<div class="col-sm-6">
                    <div class="panel panel-default mb-4">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart" aria-hidden="true"></i>
                            Biểu đồ top sách được đánh giá cao nhất
                        </div>
                        <div class="panel-body"><canvas id="myBarChart" width="100%" height="50"></canvas></div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    
    <br><br>
    
    <jsp:include page="adminFooter.jsp"></jsp:include>

    <script src="js/jquery.js"></script>
    <script src="js/jquery-ui.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.scrollUp.min.js"></script>
	<script src="js/price-range.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/main.js"></script>

    <script src="js/Chart.min.js"></script>
    
    <script type="text/javascript">
    Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
    Chart.defaults.global.defaultFontColor = '#292b2c';
    
    //xử lý dữ liệu của đồ thị
    var book = [
        <c:forEach items="${listOrderByBooks}" var="order">
        	'<c:out value="${order.bookId}"/>',  
        </c:forEach>
     ];
    var soldB = [
        <c:forEach items="${listOrderByBooks}" var="order">
        	<c:out value="${order.quantity}" />,  
        </c:forEach>
     ];
    
    var month = [
        <c:forEach items="${listOrderByMonths}" var="order">
        	'Tháng <c:out value="${order.month}"/>',  
        </c:forEach>
     ];
    var soldM = [
        <c:forEach items="${listOrderByMonths}" var="order">
        	<c:out value="${order.quantity}" />,  
        </c:forEach>
     ];
    
    var user = [
        <c:forEach items="${listAccount}" var="account">
        	'<c:out value="${account.username}"/>',  
        </c:forEach>
     ];
    var total = [
        <c:forEach items="${listAccount}" var="account">
        	<c:out value="${account.totalBooks}" />,  
        </c:forEach>
     ];
    
    
    
    var ctx = document.getElementById("myBarChart");
    var myLineChart = new Chart(ctx, {
      type: 'bar',
      data: {
        labels: book,
        datasets: [{
          label: "Doanh số",
          backgroundColor: "rgba(2,117,216,1)",
          borderColor: "rgba(2,117,216,1)",
          data: soldB,
        }],
      },
      options: {
        scales: {
          xAxes: [{
            time: {
              unit: 'tiêu thụ'
            },
            gridLines: {
              display: false
            },
            ticks: {
              maxTicksLimit: 10
            }
          }],
          yAxes: [{
            ticks: {
              min: 0,
              max: 100,
              maxTicksLimit: 5
            },
            gridLines: {
              display: true
            }
          }],
        },
        legend: {
          display: false
        }
      }
    });
    
    //Area Chart
    var ctx2 = document.getElementById("myAreaChart");
    var myLineChart = new Chart(ctx2, {
      type: 'line',
      data: {
        labels: month,
        datasets: [{
          label: "Tiêu thụ",
          lineTension: 0.3,
          backgroundColor: "rgba(2,117,216,0.2)",
          borderColor: "rgba(2,117,216,1)",
          pointRadius: 5,
          pointBackgroundColor: "rgba(2,117,216,1)",
          pointBorderColor: "rgba(255,255,255,0.8)",
          pointHoverRadius: 5,
          pointHoverBackgroundColor: "rgba(2,117,216,1)",
          pointHitRadius: 50,
          pointBorderWidth: 2,
          data: soldM,
        }],
      },
      options: {
        scales: {
          xAxes: [{
            time: {
              unit: 'date'
            },
            gridLines: {
              display: false
            },
            ticks: {
              maxTicksLimit: 12
            }
          }],
          yAxes: [{
            ticks: {
              min: 0,
              max: 100,
              maxTicksLimit: 5
            },
            gridLines: {
              color: "rgba(0, 0, 0, .125)",
            }
          }],
        },
        legend: {
          display: false
        }
      }
    });
    
    //Pie chart
    var ctx3 = document.getElementById("myPieChart");
    var myPieChart = new Chart(ctx3, {
      type: 'pie',
      data: {
        labels: user,
        datasets: [{
          data: total,
          backgroundColor: ['#63e399' ,'#007bff', '#dc3545', '#ffc107', '#28a745'],
        }],
      },
    });
    </script>
</body>
</html>