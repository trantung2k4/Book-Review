<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    String pageParam = request.getParameter("page");
    int currentPage = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;
    int bookCount = (Integer) request.getAttribute("bookCount");
    request.setAttribute("currentPage", currentPage);
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>RING! - Trang chủ</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css">
        <link href="css/prettyPhoto.css" rel="stylesheet">
        <link href="css/price-range.css" rel="stylesheet">
        <link href="css/animate.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <link href="css/responsive.css" rel="stylesheet">
        <link rel="shortcut icon" href="images/ico/favicon.ico">

        <script type="text/javascript">
            var currentPage = <%= currentPage %>;
            var bookCount = <%= bookCount %>;
            var itemsPerPage = 6; // Number of items per page

            function changePage(page) {
                var totalPages = Math.ceil(bookCount / itemsPerPage);
                if (page < 1 || page > totalPages)
                    return;

                currentPage = page;

                // Update the URL without reloading the page
                history.pushState(null, '', '?page=' + currentPage);


            }

            // Other JavaScript functions (loadBooks, updatePagination)...
        </script>
    </head>

    <body>
        <jsp:include page="navigation.jsp"></jsp:include>

            <section class="container">
                <p class="title-section h2"></i> <span>  Đừng bỏ lỡ ! </span> bạn muốn tìm kiếm gì ?</p>
            </section>

            <section id="category-display">
                <div class="container">
                    <div class="row">
                    <c:forEach var="category" items="${listBookByCategory}">
                        <a href="detailBookByCategory?pid=${category.id}">
                            <div class="col-sm-3">
                                <div class="category-item text-center">
                                    <div class="category-icon">
                                       <img src="${category.img}" alt="" class="img-home" />
                                    </div>
                                    <h4 class="category-title">${category.categoryName}</h4>
                                    <p class="category-count">${category.bookCount} mặt hàng</p>
                                </div>
                            </div>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </section>

        <section>
            <div class="container">
                <div class="row">
                    <jsp:include page="left.jsp"></jsp:include> 

                        <div class="col-sm-9 padding-right">
                            <div class="features_items"><!--features_items-->
                                <h2 class="title text-center">${empty selectedCategory ? "Tổng hợp sản phẩm" : selectedCategory }</h2>
                            <section class="containerHome">
                                <div id="list-show">
                                    <c:forEach items="${listBook}" var="book">
                                        <div class="col-sm-4 product-show">
                                            <div class="product-image-wrapper">
                                                <div class="single-products">
                                                    <div class="productinfo text-center">
                                                        <a href="details?pid=${book.id}"><img src="${book.image}" alt="" /></a>
                                                        <p><a href="details?pid=${book.id}"><strong>${book.title}</strong></a></p>
                                                    </div>
                                                </div>
                                                <div class="choose">
                                                    <ul class="nav nav-pills nav-justified">
                                                        <li><a href=""><i class="bi bi-person"></i>${book.author}</a></li>
                                                        <li>
                                                            <a class="rating">
                                                                ${book.rating[0]} Đánh giá
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a class="rating"> 
                                                                ${book.averageReview}
                                                                <i class="bi bi-star"></i>
                                                            </a>


                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>

                                <div class="pagination-container text-center">
                                    <ul class="pagination">
                                        <li class="page-item">
                                            <a class="page-link" href="./home?page=${currentPage - 1 <= 1? 1:currentPage - 1}""id="prevBtn">Previous</a>
                                        </li>
                                        <c:forEach begin="1" end="${(bookCount + 5) / 6}" var="page">
                                            <li class="page-item">
                                                <a class="page-link" href="./home?page=${page}">${page}</a>
                                            </li>
                                        </c:forEach>
                                        <li class="page-item">
                                            <a class="page-link" href="./home?page=${ currentPage + 1}"  id="nextBtn">Next</a>
                                        </li>
                                    </ul>
                                </div>
                            </section>

                            <br>
                        </div><!--features_items-->
                        <div class="recommended_items"><!--recommended_items-->
                            <h2 class="title text-center">Sản phẩm nổi bật</h2>

                            <div id="recommended-item-carousel" class="carousel slide" data-ride="carousel">
                                <div class="carousel-inner">

                                    <c:forEach begin="0" end="${Math.ceil(listRecommend.size()/4) - 1}" var="i">
                                        <c:if test="${i <= 0}">
                                            <div class="item active">	
                                                <c:forEach begin="0" end="3" var = "j">
                                                    <div class="col-xs-12 col-sm-6 col-md-3">
                                                        <div class="product-image-wrapper">
                                                            <div class="single-products">
                                                                <div class="productinfo text-center">
                                                                    <c:if test="${(4*i) + j < listRecommend.size()}">
                                                                        <c:set var="book" value="${listRecommend.get((4*i) + j)}"/>
                                                                    </c:if>
                                                                    <a href = "details?pid=${book.id}"><img src="${book.image}" alt="" /></a>
                                                                    <div class="choose">
                                                                        <ul class="nav nav-pills nav-justified">

                                                                            <li>
                                                                                <a class="rating">
                                                                                    ${book.totalReview} Đánh giá
                                                                                </a>
                                                                            </li>
                                                                            <li>
                                                                                <a class="rating"> 
                                                                                    ${book.averageReview}
                                                                                    <i class="bi bi-star"></i>
                                                                                </a>


                                                                            </li>
                                                                        </ul>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </c:if>

                                        <c:if test="${i > 0}">
                                            <div class="item">	
                                                <c:forEach begin="0" end="3" var = "j">
                                                    <c:if test="${((4*i)+j) < listRecommend.size()}">
                                                        <div class="col-xs-12 col-sm-6 col-md-3">
                                                            <div class="product-image-wrapper">
                                                                <div class="single-products">
                                                                    <div class="productinfo text-center">
                                                                        <c:set var = "book" value = "${listRecommend.get((4*i)+j)}"/>
                                                                        <a href = "details?pid=${book.id}"><img src="${book.image}" alt="" /></a>
                                                                        <div class="choose">
                                                                            <ul class="nav nav-pills nav-justified">
                                                                                <li>
                                                                                    <a class="rating">
                                                                                        ${book.totalReview} Đánh giá
                                                                                    </a>
                                                                                </li>
                                                                                <li>
                                                                                    <a class="rating"> 
                                                                                        ${book.averageReview}
                                                                                        <i class="bi bi-star"></i>
                                                                                    </a>


                                                                                </li>
                                                                            </ul>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </div>
                                <a class="left recommended-item-control" href="#recommended-item-carousel" data-slide="prev">
                                    <i class="fa fa-angle-left"></i>
                                </a>
                                <a class="right recommended-item-control" href="#recommended-item-carousel" data-slide="next">
                                    <i class="fa fa-angle-right"></i>
                                </a>			
                            </div>
                        </div><!--/recommended_items-->
                    </div>
                </div>
            </div>
        </section>

        <jsp:include page="footer.jsp"></jsp:include>

        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.scrollUp.min.js"></script>
        <script src="js/price-range.js"></script>
        <script src="js/jquery.prettyPhoto.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
