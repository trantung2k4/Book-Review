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
        <title>RING! - Thanh toán</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/prettyPhoto.css" rel="stylesheet">
        <link href="css/price-range.css" rel="stylesheet">
        <link href="css/animate.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <link href="css/responsive.css" rel="stylesheet">
        <!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <script src="js/respond.min.js"></script>
        <![endif]-->       
        <link rel="shortcut icon" href="images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
        <script type="text/javascript">
            function updateShipping() {
                var selectedShipping = document.querySelector('input[name="shippingRadios"]:checked').value;
                var cartTotalPrice = parseInt(document.getElementById('cartTotalPrice').textContent);
                var shippingFeeElement = document.getElementById('shippingFee');
                var totalAmountElement = document.getElementById('totalAmount');
                var shippingFee = 0;

                switch (selectedShipping) {
                    case 'option1':
                        shippingFee = 30000;
                        break;
                    case 'option2':
                        shippingFee = 45000;
                        break;
                    case 'option3':
                        shippingFee = 0;
                        break;
                    default:
                        shippingFee = 0;
                }

                shippingFeeElement.textContent = shippingFee;
                totalAmountElement.textContent = (cartTotalPrice + shippingFee + (cartTotalPrice * 0.1));
            }

        </script>
    </head><!--/head-->

    <body>
        <jsp:include page="navigation.jsp"></jsp:include>

            <section id="cart_items">
                <div class="container">
                    <div class="breadcrumbs">
                        <ol class="breadcrumb">
                            <li><a href="home">Trang chủ</a></li>
                            <li class="active">Thanh toán</li>
                        </ol>
                    </div>

                <c:if test="${checkOutMessage != null}">
                    <div class="alert alert-danger alert-dismissible" role="alert">
                        <span aria-hidden="true"><i class="fa fa-exclamation-triangle"></i></span>
                            ${checkOutMessage}
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </c:if>

                <div class="step-one">
                    <h2 class="heading">Xác nhận người đặt hàng</h2>
                </div>
                <div class="checkout-options">

                    <c:if test = "${sessionScope.loginedUser != null}"> 
                        <h3>Thành viên</h3>
                        <p>${sessionScope.loginedUser.username}</p>
                    </c:if>

                    <c:if test = "${sessionScope.loginedUser == null}"> 
                        <h3>Khách hàng</h3>
                        <p>Bạn đang thanh toán dưới tư cách khách hàng</p>
                    </c:if>

                    <ul class="nav">
                        <li>
                            <c:if test = "${sessionScope.loginedUser != null}"> 
                                <a href="login"><i class="fa fa-lock"></i>Đổi tài khoản</a>
                            </c:if>

                            <c:if test = "${sessionScope.loginedUser == null}"> 
                                <a href="login"><i class="fa fa-lock"></i>Đăng nhập</a>
                            </c:if>
                        </li>

                        <li>
                            <a href="cart"><i class="fa fa-arrow-left"></i>Về giỏ hàng</a>
                        </li>
                    </ul>
                </div><!--/checkout-options-->

                <div class="register-req">
                    <c:if test = "${sessionScope.loginedUser != null}"> 
                        <p>Sau khi thanh toán bạn sẽ được tích luỹ điểm thưởng vào tài khoản</p>
                    </c:if>

                    <c:if test = "${sessionScope.loginedUser == null}"> 
                        <p>Vui lòng đăng nhập để được tích điểm thưởng và lưu thông tin chi tiết đơn hàng này</p>
                    </c:if>

                </div><!--/register-req-->

                <div class="shopper-informations">
                    <div class="row">
                        <div class="bill-to">
                            <div class="col-sm-12 clearfix">
                                <p>Địa chỉ người nhận</p>
                                <div class="form-one">
                                    <form id="checkoutform" action="checkout" method="post">
                                        <input type="text" name="fName" placeholder="Họ đệm*" required>
                                        <input type="text" name="lName" placeholder="Tên*" required>
                                        <input type="email" name="email" value="${sessionScope.loginedUser.email}" placeholder="Email*" required>
                                        <input type="tel" name="phone" placeholder="Số điện thoại*" required>
                                        <select name="city" required>
                                            <option value="" disabled selected>-- Tỉnh/Thành phố--</option>
                                            <option value="TP. Hồ Chí Minh">Hồ Chí Minh</option>
                                            <option value="Hải Phòng">Hải Phòng</option>
                                            <option value="Hà Nội">Hà Nội</option>
                                            <option value="Lâm Đồng">Lâm Đồng</option>
                                            <option value="Bà Rịa - Vũng Tàu">Bà Rịa - Vũng Tàu</option>
                                        </select>
                                        <select name="state" required>
                                            <option value="" disabled selected>-- Phường/Xã --</option>
                                            <option value="Xã Xuân Trường">Xã Xuân Trường</option>
                                            <option value="TP. Đà Lạt">TP. Đà Lạt</option>
                                            <option value="Quận 1">Quận 1</option>
                                            <option value="Quận 2">Quận 2</option>
                                            <option value="Quận 3">Quận 3</option>
                                            <option value="Quận 4">Quận 4</option>
                                            <option value="Quận 5">Quận 5</option>
                                            <option value="Quận 6">Quận 6</option>
                                        </select>
                                        <input type="text" name="address" placeholder="Địa chỉ nhận hàng*" required>
                                        <br>
                                        <p>Ghi chú</p>
                                        <textarea name="message" placeholder="Nhập ghi chú cho đơn hàng" rows="10"></textarea>
                                        <label><input type="checkbox"> Xuất hoá đơn GTGT</label>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="step-one">
                    <h2 class="heading">Chọn hình thức vận chuyển</h2>
                </div>
                <div class="shopper-shipping">
                    <div class="form-check disabled">
                        <input class="form-check-input" type="radio" name="shippingRadios" id="shippingOption1" value="option1" onchange="updateShipping()">
                        <label class="form-check-label" for="shippingOption1">
                            Giao hàng tiêu chuẩn: 30.000 đ
                        </label>
                    </div>
                    <div class="form-check disabled">
                        <input class="form-check-input" type="radio" name="shippingRadios" id="shippingOption2" value="option2" onchange="updateShipping()">
                        <label class="form-check-label" for="shippingOption2">
                            Giao hàng nhanh: 45.000 đ
                        </label>
                    </div>
                    <div class="form-check selected">
                        <input class="form-check-input" type="radio" name="shippingRadios" id="shippingOption3" value="option3" onchange="updateShipping()">
                        <label class="form-check-label" for="shippingOption3">
                            Giao hàng miễn phí: Free
                        </label>
                    </div>
                </div>


                <div class="step-one">
                    <h2 class="heading">Chọn phương thức thanh toán</h2>
                </div>
                <div class="shopper-payment">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="paymentRadios" id="paymentRadios" value="option2">
                        <label class="form-check-label" for="paymentRadios">
                            Ví ZaloPay
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="paymentRadios" id="paymentRadios" value="option2">
                        <label class="form-check-label" for="paymentRadios">
                            Ví Moca
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="paymentRadios" id="paymentRadios" value="option2">
                        <label class="form-check-label" for="paymentRadios">
                            VNPAY
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="paymentRadios" id="paymentRadios" value="option2">
                        <label class="form-check-label" for="paymentRadios">
                            Ví ShoppeePay
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="paymentRadios" id="paymentRadios" value="option2">
                        <label class="form-check-label" for="paymentRadios">
                            Ví Momo
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="paymentRadios" id="paymentRadios" value="option2">
                        <label class="form-check-label" for="paymentRadios">
                            Chuyển khoản ngân hàng
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="paymentRadios" id="paymentRadios" value="option2">
                        <label class="form-check-label" for="paymentRadios">
                            ATM/Internet Banking
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="paymentRadios" id="paymentRadios" value="option2">
                        <label class="form-check-label" for="paymentRadios">
                            Visa/Mastercard/JCB
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="paymentRadios" id="paymentRadios" value="option2">
                        <label class="form-check-label" for="paymentRadios">
                            Thanh toán bằng tiền mặt khi nhận hàng
                        </label>
                    </div>
                </div>


                <div class="step-one">
                    <h2 class="heading">Áp dụng mã giảm giá</h2>
                </div>
                <div class="shopper-discount">
                    <form>
                        <input type="text" placeholder="Nhập mã giảm giá">
                        <button type="submit" class="btn btn-apply">Áp dụng</button>
                    </form>
                    <p>Có thể áp dụng nhiều mã cùng lúc</p>
                </div>

                <div class="review-payment">
                    <h2>Kiểm tra lại đơn hàng</h2>
                </div>

                <!-- Cart -->
                <div class="table-stacked cart_info">
                    <table class="table table-condensed">
                        <thead>
                            <tr class="cart_menu">
                                <td class="image col-sm-2">Tổng sản phẩm (${sessionScope.cart.status})</td>
                                <td class="description col-sm-4"></td>
                                <td class="price col-sm-2">Giá</td>
                                <td class="quantity col-sm-2">Số lượng</td>
                                <td class="total col-sm-2">Thành tiền</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items = "${sessionScope.cart.items}" var = "item">
                                <tr>
                                    <td class="cart_product">
                                        <a href="details?pid=${item.book.id}"><img src="${item.book.image}" alt=""></a>
                                    </td>
                                    <td class="cart_description">
                                        <h4><a href="details?pid=${item.book.id}">${item.book.title}</a></h4>
                                    </td>
                                    <td class="cart_price">
                                        <p>${item.price}đ</p>
                                    </td>
                                    <td class="cart_quantity">
                                        <p>${item.quantity}</p>
                                    </td>
                                    <td class="cart_total">
                                        <p class="cart_total_price">${item.totalPrice}đ</p>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div>
        </section> <!--/#cart_items-->

        <section id="do_action">
            <div class="container">
                <div class="step-one">
                    <h2 class="heading">Kiểm tra lại đơn giá</h2>
                </div>
                <div class="row">
                    <div class="col-sm-6">
                        <div class="total_area">
                            <ul>
                                <li>Thành tiền: <span>${sessionScope.cart.cartTotalPrice}đ</span></li>
                                <li>VAT: <span>10%</span></li>
                                <li>Phí ship: <span>Free</span></li>
                                <li>Tổng: <span class="total">${sessionScope.cart.cartTotalPrice + sessionScope.cart.cartTotalPrice * 0.1}đ</span></li>
                            </ul>
                            <input type="submit" form="checkoutform" name="submit-checkout" class="btn btn-apply" value="Xác nhận thanh toán">
                        </div>
                    </div>
                </div>
            </div>
        </section><!--/#do_action-->

        <jsp:include page="footer.jsp"></jsp:include>



        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.scrollUp.min.js"></script>
        <script src="js/jquery.prettyPhoto.js"></script>
        <script src="js/main.js"></script>       
    </body>
</html>