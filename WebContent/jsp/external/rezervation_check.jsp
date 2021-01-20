<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/jsp/init-common.jsp" />

<!DOCTYPE html>
<html lang="en">
<jsp:include page="/jsp/header.jsp" />
<body>
	<jsp:include page="/jsp/navigation.jsp" />

	<div class="container-fluid text-center">
		<div class="row content">
			<jsp:include page="/jsp/external/sideNavigation.jsp" />
			<div class="col-sm-8 text-left">
				<h1>Patikrinkite susitikimo laiką</h1>
				<p>
					Pamiršote susitikimo datą ar laiką?</br> Įveskite savo asmens duomenis
					su kuriais registravotės ir mes ją priminsime!
				</p>

				<hr>
				<c:choose>
					<c:when test="${message != null}">
						<p class="${message.type.geCssClass()}">${message.content}</p>
					</c:when>

					<c:otherwise>
						<form action="#" method="post">
							<div class="form-table">
								<div class="form-row">
									<div class="form-label">Vardas:</div>
									<div class="form-value">
										<input type="text" name="name" required minlength="2">
									</div>

								</div>
								<div class="form-row">
									<div class="form-label">Pavarde:</div>
									<div class="form-value">
										<input type="text" name="surname" required minlength="2">
									</div>

								</div>
								<div class="form-row">
									<div class="form-label">Asmens kodas:</div>
									<div class="form-value">
										<input type="number" name="personCode" required minlength="11"
											maxlength="11">
									</div>

								</div>
							</div>

							<div class="form-buttons">
								<input type="submit" value="Tikrinti">
							</div>

						</form>
					</c:otherwise>
				</c:choose>
			</div>
		</div>

	</div>
</body>
</html>
