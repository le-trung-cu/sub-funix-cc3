<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" />

<section class="checkout spad">
	<div class="container">
		<div class="checkout__form">
			<h4>Login</h4>
			<form id="form-login" action="login" method="post">
				<p>
					<mark id="error"><%= session.getAttribute("error")!=null? session.getAttribute("error") : ""%></mark>
				</p>
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
							<input id="name" name="username" type="text" value="<%= session.getAttribute("loginUsername") !=  null? session.getAttribute("loginUsername") : ""%>"
								placeholder="user name">
						</div>
						<div class="checkout__input">
							<p>
								Password<span>*</span>
							</p>
							<input id="password" name="password" type="text"
								placeholder="password" class="checkout__input__add">
						</div>
						<p><label><input type="checkbox" name="remember"> Remeber me</label></p>
						
						<button type="submit" class="site-btn">Login</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</section>

<jsp:include page="footer.jsp" />