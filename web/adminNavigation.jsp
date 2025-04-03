<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:if test="${(sessionScope.loginedUser != null) && (sessionScope.loginedUser.isAdmin == 1)}">
	<header id="header"><!--header-->
		<div class="header-middle"><!--header-middle-->
			<div class="container">
				<div class="row">
					<div class="col-sm-4">
						<div class="logo pull-left">
							<div class="mainlogo">
								<h2><a href="home"><span><img src="images/home/bell.png" alt="" /> RING!</span> - admin</a></h2>
							</div>
						</div>
					</div>
					
					<div class="col-sm-8">
						<div class="shop-menu pull-right">
							<ul class="nav navbar-nav">
								<li><a href="logout"><i class="fa fa-sign-out"></i> Đăng xuất</a></li>
							</ul>
						</div>
					</div>
					
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
					</div>
					
					<div class="mainmenu pull-left">
						<ul class="nav navbar-nav collapse navbar-collapse">
							<li><a href="admin"><i class="fa fa-tachometer">&nbsp;</i>Dashboard</a></li>
							<li><a href="admin?isChart=1"><i class="fa fa-area-chart">&nbsp;</i>Đồ thị</a></li>
							<li><a href="admin?isTable=1"><i class="fa fa-table">&nbsp;</i>Bảng dữ liệu</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div><!--/header-middle-->
	</header><!--/header-->
</c:if>   
