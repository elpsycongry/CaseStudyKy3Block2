<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.cs_block2_ky3.Model.Tour" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>The Band</title>
    <link rel="stylesheet" href="view/css.css">
    <link rel="stylesheet" href="view/ResponsiveMain.css"
    <script src="https://kit.fontawesome.com/39e48099af.js" crossorigin="anonymous"></script>
</head>
<body>
<div id="main">
    <div id="header">
        <ul id="nav">
            <li><a href="#slider">Home</a></li>
            <li><a href="#band">Band</a></li>
            <li><a href="#tour">Tour</a></li>
            <li><a href="#contact">Contact</a></li>
            <li>
                <a href="#">More <i class="fa-solid fa-caret-down"></i></a>
                <ul class="subNav">
                    <li><a href="#" class="text-black">Merchandise</a></li>
                    <li><a href="#" class="text-black">Extras</a></li>
                    <li><a href="#" class="text-black">Media</a></li>
                </ul>
            </li>
            <c:choose>
                <c:when test="${sessionScope.role.equals('admin') || sessionScope.role.equals('member') }">
                    <li class="float-right "><a href="/sessionController">Logout</a></li>
                    <li class="float-right "><a href="/cart-controller">MyCart</a></li>
                </c:when>
                <c:otherwise>
                    <li class="float-right"><a href="/login_logout/login.jsp">Login</a></li>
                </c:otherwise>
            </c:choose>

        </ul>
    </div>
    <div id="slider">
        <div class="sliderBottom">
            <h2>Chicago</h2>
            <p><b>Lorem ipsum dolor sit amet.</b></p>
        </div>
    </div>
    <div id="content">
        <div id="band" class="content-section">
            <h2 class="section-heading">THE BAND</h2>
            <p class="section-sub-heading"><i>We love music</i></p>
            <p class="about-text">
                We have created a fictional band website. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
                do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
                reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
                cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur
                adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
                veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
            </p>
            <div class="memberList row ">
                <div class="col col3 text-center">
                    <p>Name</p>
                    <img class="member-img" src="https://www.w3schools.com/w3images/bandmember.jpg" alt="imga">
                </div>
                <div class="col col3 text-center">
                    <p>Name</p>
                    <img class="member-img" src="https://www.w3schools.com/w3images/bandmember.jpg" alt="image">
                </div>
                <div class="col col3 text-center">
                    <p>Name</p>
                    <img class="member-img" src="https://www.w3schools.com/w3images/bandmember.jpg" alt="image">
                </div>

            </div>
        </div>

        <div id="tour" class="tour-section">
            <div class="content-section">
                <h2 class="section-heading text-white">Tour dates</h2>
                <p class="section-sub-heading text-white"><i>Remember to book your tickets</i></p>
                <ul class="tickets-list">
                    <li>September
                        <span class="sold-out">Sold out</span>
                    </li>
                    <li>October
                        <span class="sold-out">Sold out</span>
                    </li>
                    <li>November
                        <span class="current-ticket">3</span>
                    </li>
                </ul>
                <div class="tour-list row">
                    <c:forEach var="item" items="${sessionScope.listTour}">
                        <div class="col col3 mt-16">
                            <img src="${item.urlImage}" alt="">
                            <div class="place-body">
                                <p><b>${item.namePlace}</b></p>
                                <p><i>${item.date}</i></p>
                                <p style="height: 45px">${item.description}</p>
                                    <%--                                <p>${item.numberTicket} left</p>--%>
                                <c:choose>
                                    <c:when test="${sessionScope.role.equals('admin')}">
                                        <form action="/main" method="get">
                                            <input type="hidden" name="idTour" value="${item.id}">
                                            <button name="directTo" value="editForm" type="submit"
                                                    class="tourBtn col3 bg-green">
                                                Edit
                                            </button>
                                            <button name="directTo" value="deleteAction"
                                                    class="tourBtn delete-btn col3 bg-red ">
                                                Delete
                                            </button>
                                        </form>
                                    </c:when>
                                    <c:when test="${sessionScope.role.equals('member')}">
                                        <%--                                        <button class=" js.buy-ticket " >--%>
                                        <%--                                            Buy Tickets--%>
                                        <%--                                        </button>--%>
                                        <form action="/cart-controller" method="post">
                                            <input type="hidden" name="id" value="${item.id}">
                                            <input type="hidden" name="numberTicket" value="${item.numberTicket}">
                                            <input type="hidden" name="date" value="${item.date}">
                                            <input type="hidden" name="description" value="${item.description}">
                                            <input type="hidden" name="urlImage" value="${item.urlImage}">
                                            <input type="hidden" name="namePlace" value="${item.namePlace}">
                                            <button class=" js.buy-ticket " type="submit">
                                                Buy Tickets
                                            </button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <button onclick="window.location.href = '/login_logout/login.jsp'">
                                            Buy Tickets
                                        </button>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </c:forEach>
                    <c:if test="${sessionScope.role.equals('admin')}">
                        <div class="col col3 addTourDiv mt-16">
                            <a href="/CRUD-view/add-form.jsp" class="add-btn">
                                <i class="fa-solid fa-plus" style="font-size: 30px"></i>
                            </a>
                        </div>
                    </c:if>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
        <div id="contact" class="content-section">
            <h2 class="section-heading">CONTACT</h2>
            <p class="section-sub-heading">Fan? Drop a note! </p>
            <div class="row contact-content">
                <div class="col col2 contact-info">
                    <p><i class="fa-solid fa-location-dot"></i> Chicago, US</p>
                    <p><i class="fa-solid fa-phone"></i> Phone: +00 151515 </p>
                    <p><i class="fa-solid fa-envelope"></i> Email: mail@mail.com</p>
                </div>
                <div class="col col2">
                    <form>
                        <div class="row mb-8">
                            <div class="col col2">
                                <input class="inputType" required type="text" name="name" placeholder="Name">
                            </div>
                            <div class="col col2">
                                <input class="inputType" required type="email" name="email" placeholder="Email">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col col1">
                                <input class="inputType" required type="text" name="message" placeholder="Message">
                            </div>
                        </div>
                        <input class="text-white bg-black submit-contact" type="submit" value="SEND">
                    </form>
                </div>
            </div>
        </div>
        <div class="map-section">
            <img class="col1" src="https://www.w3schools.com/w3images/map.jpg">
        </div>
    </div>
    <div id="footer">
        <div class="social-link mb-8">
            <a href=""><i class="fa-brands fa-square-facebook"></i></a>
            <a href=""><i class="fa-brands fa-square-facebook"></i></a>
            <a href=""><i class="fa-brands fa-square-facebook"></i></a>
            <a href=""><i class="fa-brands fa-square-facebook"></i></a>
            <a href=""><i class="fa-brands fa-square-facebook"></i></a>
            <a href=""><i class="fa-brands fa-square-facebook"></i></a>
        </div>
        <p>Powered by <a href="#"> tùng chiến </a></p>
    </div>

    <div class="modal js-modal">
        <div class="buy-modal js-buy-modal">
            <div class="close js.close-buy-ticket"><i class="fa-solid fa-xmark"></i></div>
            <div class="modal-head">
                Tickets
            </div>
            <div class="modal-content">
                <label>Tickets, $15 per person</label>
                <form action="/cart-controller">
                    <input class="js.idInputModal" type="hidden" name="id" value="9">
                    <input type="text" class="modal-input" placeholder="How many?">
                    <button class="pay-btn">Pay</button>
                </form>
                <div class="footer">Need <a href="#">help?</a></div>
            </div>
        </div>
    </div>
</div>

<script>
    const buyBtns = document.getElementsByClassName("js.buy-ticket");
    const closeBtns = document.getElementsByClassName("js.close-buy-ticket")
    const modal = document.querySelector(".js-modal")
    const buyModal = document.querySelector(".js-buy-modal")

    for (const buyBtn of buyBtns) {
        buyBtn.addEventListener('click', showModal);
    }

    for (const closeBtn of closeBtns) {
        closeBtn.addEventListener('click', hideModal)
    }

    modal.addEventListener('click', hideModal)
    buyModal.addEventListener('click', function (event) {
        event.stopPropagation()
    });

    function showModal() {
        setValueToModal();
        getValueFromModal("js.idInputModal");
        displayModalStatus(true)
    }

    function hideModal() {
        displayModalStatus(false)
    }

    function displayModalStatus(visible) {
        if (visible) {
            console.log(document.querySelector(".modal").style.display = 'flex')
        } else {
            console.log(document.querySelector(".modal").style.display = 'none')
        }
    }

    function getValueFromModal(nameClass) {
        console.log(document.querySelector(nameClass).value);
    }

    function setValueToModal() {
        (document.querySelector(".idInputModal").value) = "13";
    }


</script>
</body>
</html>
