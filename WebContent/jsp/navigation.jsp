<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"></a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li
					class="${pageContext.request.requestURI.endsWith('/rezervation_creation.jsp') || 
					pageContext.request.requestURI.endsWith('/rezervation_check.jsp') ? 'active' : ''}"><a
					href="${pageContext.request.contextPath}/external/rezervation_creation.jsp">Išorinis portalas</a></li>
				<li
					class="${pageContext.request.requestURI.endsWith('/rezervation_list.jsp') ? 'active' : ''}"><a
					href="${pageContext.request.contextPath}/internal/rezervation_list.jsp">Vidinis
						portalas</a></li>
			</ul>
			<!--  <ul class="nav navbar-nav navbar-right">
					<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
							Login</a></li>
				</ul>-->
		</div>
	</div>
</nav>