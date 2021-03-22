<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp" />

<section class="checkout spad">
	<div class="container">
		<div class="checkout__form">
			<h4>Login s</h4>
			<form id="form-login" action='<c:url value="/login.html"/>' method="post">
				<c:if test="${not empty error }">
					<div class="alert alert-danger" role="alert">
						<c:out value="${error }" />
					</div>
				</c:if>
				<p>
					<mark id="error-name"></mark>
				</p>
				<p>
					<mark id="error-password"></mark>
				</p>
				<div class="row">
					<div class="col-lg-8 col-md-6">
						<div class="checkout__input">
							<p>
								User Name<span>*</span>
							</p>
							<input id="name" name="username" value='<c:out value="${ param.username }"/>' type="text" placeholder="user name">
						</div>
						<div class="checkout__input">
							<p>
								Password<span>*</span>
							</p>
							<input id="password" name="password" type="text" value='<c:out value="${ param.password }"/>'
								placeholder="password" class="checkout__input__add">
						</div>

						<button type="submit" class="site-btn">Login</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</section>


<jsp:include page="footer.jsp" />