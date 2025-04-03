<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String pageParam = request.getParameter("page");
    int currentPage = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;
    request.setAttribute("currentPage", currentPage);
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>RING! - ${book.title}</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css">
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

        <jsp:include page="navigation.jsp"></jsp:include>

            <section class="bookByCategory">
                <div class="container">
                    <div class="row">
                        <div class="padding-right2">
                            <div class="features_items">
                                <h2 class="title text-center">${categoryName}</h2>
                            <section class="book-container">
                                <c:forEach var="book" items="${listBook}">
                                    <div>
                                        <div class="product-image-wrapper">
                                            <div class="single-products">
                                                <div class="productinfo productinfo2 text-center">
                                                    <img src="${book.image}" alt="${book.title}" />
                                                    <section class="info-section">
                                                        <a href="./details?pid=${book.id}">
                                                            <h2>${book.title}</h2>
                                                        </a>

                                                        <section >
                                                            <div class="choose">
                                                                <ul class="nav nav2 nav-pills">
                                                                    <li><a href=""><i class="bi bi-person"></i>${book.author}</a></li>
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

                                                        </section>
                                                        <section class="description-container">
                                                            ${book.description}
                                                        </section>
                                                    </section>
                                                </div>
                                            </div>
                                            <div class="product-overlay">
                                                <div class="overlay-content">
                                                    <h2>${book.price} $</h2>
                                                    <p>${book.title}</p>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>

                            </section>

                            <div class="pagination-container text-center">
                                <ul class="pagination">
                                    <li class="page-item">
                                        <a class="page-link" href="./detailBookByCategory?page=${currentPage - 1 <= 1? 1:currentPage - 1}&pid=${categoryID}""id="prevBtn">Previous</a>
                                    </li>
                                    <c:forEach begin="1" end="${(totalBook + 4) / 5}" var="page">
                                        <li class="page-item">
                                            <a class="page-link" href="./detailBookByCategory?page=${page}&pid=${categoryID}">${page}</a>
                                        </li>
                                    </c:forEach>
                                    <li class="page-item">
                                        <a class="page-link" href="./detailBookByCategory?page=${ currentPage + 1}&pid=${categoryID}"  id="nextBtn">Next</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="bestBooks.jsp"></jsp:include>    
            </section>


        <jsp:include page="footer.jsp"></jsp:include>

        <script src="js/jquery.js"></script>
        <script src="js/price-range.js"></script>
        <script src="js/jquery.scrollUp.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.prettyPhoto.js"></script>
        <script src="js/main.js"></script>


    </body>
</html>