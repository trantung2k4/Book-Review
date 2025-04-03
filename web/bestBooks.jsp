<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<section id="right-sideBar">
    <h3>TIN XEM NHIỀU</h3>
    <section class="container">
        <c:forEach var="book" items="${listTopBook}">
            <div class="item">
                <div>
                    <div>
                        <div>
                            <div class="it-p_new_img">
                                <img src="${book.image}" alt="${book.title}" />
                            </div>

                            <section class="it-p_new_body">
                                <a href="./details?pid=${book.id}">
                                    <h2>${book.title}</h2>
                                </a>
                                <ul  class="review">
                                    <li><i class="bi bi-person"></i>${book.author}</li>
                                    <li>
                                        <a >
                                            ${book.totalReview} Đánh giá
                                        </a>
                                    </li>
                                    <li>
                                        ${book.averageReview}
                                        <i class="bi bi-star"></i>
                                    </li>
                                </ul>

                            </section>
                        </div>
                    </div>

                </div>
            </div>
        </c:forEach>

    </section>
</section>