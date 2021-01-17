<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="col-sm-2 sidenav">
	<ul class="nav navbar-nav sidebar-navbar-nav">
		<li
			class="${pageContext.request.requestURI.endsWith('/rezervation_creation.jsp') ? 'active' : ''}"><a
			href="${pageContext.request.contextPath}/external/rezervation_creation.jsp">Rezervuoti
				laiką</a></li>
		<li
			class="${pageContext.request.requestURI.endsWith('/rezervation_check.jsp') ? 'active' : ''}"><a
			href="${pageContext.request.contextPath}/external/rezervation_check.jsp">Patikrinti rezervaciją</a></li>
	</ul>
</div>