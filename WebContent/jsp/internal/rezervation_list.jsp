<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/jsp/init-common.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="../header.jsp" />
<head>
<style><%@include file="/css/calendar.css"%></style>
</head>
<body>
	<jsp:include page="/jsp/navigation.jsp" />
	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-8 text-left">
				<div class="row">
					<div class="container">
						<div class="calendar">
							<div class="month">
								<i class="fas fa-angle-left prev"></i>
								<div class="date">
									<h1></h1>
									<p></p>
								</div>
								<i class="fas fa-angle-right next"></i>
							</div>
							<div class="weekdays">
								<div>Sek</div>
								<div>Pir</div>
								<div>Ant</div>
								<div>Tre</div>
								<div>Ket</div>
								<div>Pen</div>
								<div>Šeš</div>
								

							</div>
							<div class="days"></div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
<script src="../js/calendar.js"></script>
</html>
