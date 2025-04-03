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
    <title>RING!-ADMIN - Chỉnh sửa tài khoản</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/price-range.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
	<link href="css/main.css" rel="stylesheet">
	<link href="css/responsive.css" rel="stylesheet">
    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
</head><!--/head-->

<body>

	<jsp:include page="simpleNavigation.jsp"></jsp:include>
	
	<br><br><br>
    <div class="container">
    	
    	<c:if test="${userEditMessage != null}">
	    	<div class="alert alert-success alert-dismissible" role="alert">
				<span aria-hidden="true"><i class="fa fa-check"></i></span>
				 ${userEditMessage}
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
	 					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:if>
    
		<div class="row">
			<div class="col-sm-5 col-md-7">
				<div class="manager-title">
					<h2>Chỉnh sửa tài khoản</h2>
				</div>
			</div>
			
			<div class="col-sm-7 col-md-5">
				<div class="manager_search_box">
					<form action = "" method = "get">
						<button type="submit" name="editBtn" form="edituserform" class="btn btn-default main-action pull-right"><i class="fa fa-edit">&nbsp;</i>Xác nhận</button>
						<button type="button" onclick="location.href='admin'" class="btn btn-default main-action pull-right"><i class="fa fa-times">&nbsp;</i>Huỷ</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<br>
	<section id="cart_items">
		<div class="container">
			
			<div class="add-product-infomations">
				<div class="row">
					<div class="bill-to">
						<div class="col-sm-12 clearfix">
							<p style="margin-left: 30px;">Thông tin tài khoản ID: ${user.id}</p>
							<div class="form-one">
								<form id="edituserform" action="edituser" method="post">
									<input type="hidden" name="uid" value="${user.id}">
									<input type="text" name="username" value="${user.username}" placeholder="Tên người dùng*" required>
									<input type="text" name="password" value="${user.password}" placeholder="Mật khẩu*" required>
									<input type="email" name="email" value="${user.email}" placeholder="Email*" required>
									<select name="isSeller" required>
										<option ${user.isSeller != 1 ? "selected" : ""} value="0">Không phải người bán sách</option>
										<option ${user.isSeller == 1? "selected" : ""} value="1">Là người bán sách</option>
									</select>
									<select name="isAdmin" required>
										<option ${user.isAdmin != 1 ? "selected" : ""} value="0">Không phải admin</option>
										<option ${user.isAdmin == 1? "selected" : ""} value="1">Là admin</option>
									</select>
								</form>
							</div>
						</div>	
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<br><br>
	
	<jsp:include page="footer.jsp"></jsp:include>

    <script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.scrollUp.min.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/main.js"></script>
    
    <script>
		function myFunction() {
		  var x = document.getElementById("imageInput").value;
		  document.getElementById("imageOuput").src=x;
		}
	</script>
</body>
</html>