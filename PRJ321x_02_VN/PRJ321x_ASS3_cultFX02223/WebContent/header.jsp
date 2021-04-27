<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Ogani | Template</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href='<c:url value="/css/bootstrap.min.css"/>' type="text/css">
<link rel="stylesheet" href='<c:url value="/css/font-awesome.min.css"/>' type="text/css">
<link rel="stylesheet" href='<c:url value="/css/elegant-icons.css"/>' type="text/css">
<link rel="stylesheet" href='<c:url value="/css/nice-select.css"/>' type="text/css">
<link rel="stylesheet" href='<c:url value="/css/jquery-ui.min.css"/>' type="text/css">
<link rel="stylesheet" href='<c:url value="/css/owl.carousel.min.css"/>' type="text/css">
<link rel="stylesheet" href='<c:url value="/css/slicknav.min.css"/>' type="text/css">
<link rel="stylesheet" href='<c:url value="/css/style.css"/>' type="text/css">
<link rel="stylesheet" href='<c:url value="/css/my-style.css"/>' type="text/css">
</head>

<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Humberger Begin -->
	<div class="humberger__menu__overlay"></div>
	<div class="humberger__menu__wrapper">
		<div class="humberger__menu__logo">
			<a href="#"><img src="img/logo.png" alt=""></a>
		</div>
		<div class="humberger__menu__widget">
			<div class="header__top__right__language">
				<img src="img/language.png" alt="">
				<div>English</div>
				<span class="arrow_carrot-down"></span>
				<ul>
					<li><a href="#">Spanis</a></li>
					<li><a href="#">English</a></li>
				</ul>
			</div>
			<div class="header__top__right__auth">
				<c:if test="${empty account }">
					<a href='<c:url value="/register"/>'><i class="fa fa-user"></i>register</a>
					<a href='<c:url value="/login"/>'><i class="fa fa-user"></i>login</a>
				</c:if>
				<c:if test="${not empty account }">
					<form action='<c:url value="/logout"/>' method="post">
						<button type="submit"><i class="fa fa-user"></i>logout</button>
					</form>
					<c:if test="${account.isAdmin() }">
						<a href='<c:url value="/admin"/>'>admin</a>
					</c:if>
				</c:if>
			</div>
		</div>
		<nav class="humberger__menu__nav mobile-menu">
			<ul>
				<li class="active"><a href='<c:url value="/index"/>'>Home</a></li>
				<li><a href="#">Pages</a>
					<ul class="header__menu__dropdown">
						<li><a href='<c:url value="/cart"/>'>Shopping Cart</a></li>
						<li><a href='<c:url value="/orders"/>'>History Orders</a></li>
					</ul></li>
				<li><a href="contact.jsp">Contact</a></li>
			</ul>
		</nav>
		<div id="mobile-menu-wrap"></div>
		<div class="header__top__right__social">
			<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
				class="fa fa-twitter"></i></a> <a href="#"><i class="fa fa-linkedin"></i></a>
			<a href="#"><i class="fa fa-pinterest-p"></i></a>
		</div>
		<div class="humberger__menu__contact">
			<ul>
				<li><i class="fa fa-envelope"></i> hello@colorlib.com</li>
				<li>Free Shipping for all Order of $99</li>
			</ul>
		</div>
	</div>
	<!-- Humberger End -->

	<!-- Header Section Begin -->
	<header class="header">
		<div class="header__top">
			<div class="container">
				<div class="row">
					<div class="col-lg-6 col-md-6">
						<div class="header__top__left">
							<ul>
								<li><i class="fa fa-envelope"></i> hello@colorlib.com</li>
								<li>Free Shipping for all Order of $99</li>
							</ul>
						</div>
					</div>
					<div class="col-lg-6 col-md-6">
						<div class="header__top__right">
							<div class="header__top__right__social">
								<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
									class="fa fa-twitter"></i></a> <a href="#"><i
									class="fa fa-linkedin"></i></a> <a href="#"><i
									class="fa fa-pinterest-p"></i></a>
							</div>
							<div class="header__top__right__language">
								<img src='<c:url value="img/language.png"/>' alt="">
								<div>English</div>
								<span class="arrow_carrot-down"></span>
								<ul>
									<li><a href="#">Spanis</a></li>
									<li><a href="#">English</a></li>
								</ul>
							</div>
							<div class="header__top__right__auth">
								<c:if test="${empty account }">
									<a href='<c:url value="/register"/>'><i class="fa fa-user"></i>register</a>
									<a href='<c:url value="/login"/>'><i class="fa fa-user"></i>login</a>
								</c:if>
								<c:if test="${not empty account }">
									<form action='<c:url value="/logout"/>' method="post">
										<button type="submit"><i class="fa fa-user"></i>logout</button>
									</form>
									<c:if test="${account.isAdmin() }">
										<a href='<c:url value="/admin"/>'>admin</a>
									</c:if>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="header__logo">
						<a href="./index.html"><img src="img/logo.png" alt=""></a>
					</div>
				</div>
				<div class="col-lg-6">
					<nav class="header__menu">
						<ul>
							<li class="active"><a href='<c:url value="/index"/>'>Home</a></li>
							<li><a href="#">Pages</a>
								<ul class="header__menu__dropdown">
									<li><a href='<c:url value="/cart"/>'>Shoping Cart</a></li>
									<li><a href='<c:url value="/orders"/>'>History Orders</a></li>
								</ul></li>
							<li><a href="contact.jsp">Contact</a></li>
						</ul>
					</nav>
				</div>
			</div>
			<div class="humberger__open">
				<i class="fa fa-bars"></i>
			</div>
		</div>
	</header>
	<!-- Header Section End -->

	<!-- Hero Section Begin -->
	<section class="hero">
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<jsp:include page="./cart-summary.jsp"></jsp:include>
				</div>
				<div class="col-lg-9">
					<div class="hero__search">
						<div class="hero__search__form">
							<form action='<c:url value="/search"/>' method="get" >
								<select name="brand" type="checkbox">
									<option value="">All Categories</option>
									<option value="nokia" <c:if test="${brand == 'nokia'}">selected</c:if>>Nokia</option>
									<option value="apple" <c:if test="${brand == 'apple'}">selected</c:if>>Apple</option>
								</select>
								<input name="search" type="text" placeholder="What do yo u need?" value='<c:out value="${search }"/>'>
								<button type="submit" class="site-btn">SEARCH</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	
		<c:if test="${not empty message }">
		<div class="container">
			<div class="alert alert-primary" role="alert">
			  <c:out value="${ message}"/>
			</div>
		</div>
		</c:if>
