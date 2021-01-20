<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/jsp/init-common.jsp" />


<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="curentDate" value="${now}" pattern="MM/dd/yyyy" />

<!DOCTYPE html>
<html lang="en">
<jsp:include page="/jsp/header.jsp" />

<body>
	<jsp:include page="/jsp/navigation.jsp" />
	<script>
	$(function() {
		$("#datepicker").datepicker({
			minDate: new Date()
		});

		$('.timepicker').timepicker({
		    timeFormat: 'G:i',
		    step: 60,
		    minTime: (new Date().getHours()+':00'),
		    maxTime: '17:00',
		    defaultTime: '11',
		    startTime: '8:00',
		    dynamic: false,
		    dropdown: true,
		    scrollbar: true
		});

		$('.card-content').on('click', function() {
			$('.card-content.active').removeClass('active');
			$(this).addClass('active');
			$('#doctorId').val($('.card-content.active').attr('doctorId'));
			$('#noDoctorsError').css("display", "none");
		});
		
		$('#rezervationForm').submit(function() {
			if($('#doctorId').val().length === 0){
				$('#noDoctorsError').css("display", "");
				return false;
			} else {
				$("#datepicker").val($("#datepicker").val() + ' ' + $("#timepicker").val())
			}
		});
		$(".card, #datepicker").on("change paste click", function() {
			(function (self) {
				if($("#datepicker").val().length !== 0 && $('#doctorId').val().length !== 0){
					$.getJSON('getMeetingsForTheDay',{day: $("#datepicker").val(), doctorId: $('#doctorId').val()}, function (data) {
						var currentDate = new Date();
						var oDates = [];
						if(currentDate > new Date($("#datepicker").val())){
							oDates.push(['00:00',(currentDate.getHours()-1+':59')]);
						}
						$.each(data, function(index) {
							fDate = new Date(data[index]).getHours()+':'+new Date(data[index]).getMinutes();
							eDate = new Date(data[index]).getHours()+1+':'+new Date(data[index]).getMinutes();
							oDates.push([fDate,eDate]);
						});
						$('.timepicker').timepicker({
						    timeFormat: 'G:i',
						    step: 60,
						    minTime: '08:00',
						    maxTime: '17:00',
						    startTime: '8:00',
						    dynamic: false,
						    dropdown: true,
						    scrollbar: true,
							disableTimeRanges: oDates
						});
						
			        });
				}
			})(this);
		});
	});
	</script>

	<div class="container-fluid text-center">
		<div class="row content">
			<jsp:include page="/jsp/external/sideNavigation.jsp" />
			<div class="col-sm-8 text-left">
				<h1>Rezervuokite susitikimą</h1>
				<p>Nuo šiol susitikius su daktarais galite ir internetu!
					Apačioje pasirinkus daktarą ir užpildžius formą daktaras bus
					informuotas ir į susitikimą galėsite ateiti pasirinktu laiku</p>
				<c:choose>
					<c:when test="${message != null}">
						<p class="${message.type.geCssClass()}">${message.content}</p>
					</c:when>

					<c:otherwise>
						<div class="row">
							<c:forEach items="${doctors}" var="doctor">
								<div class="col-xl-2 col-sm-6 col-12">
									<div class="card">
										<div href="#" class="card-content" doctorId="${doctor.id}">
											<div class="card-body">
												<div class="media d-flex">
													<div class="align-self-center">
														<i class="icon-user success font-large-2 float-right"></i>
													</div>
													<div class="media-body text-right">
														<h3>${doctor.name} ${doctor.surname}</h3>
														<span>${doctor.doctorOccupation.getName()}</span>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
						<hr>

						<form action="#" method="post" id="rezervationForm">
							<input type="hidden" id="doctorId" name="doctorId" value="" />
							<div class="form-table">
								<div class="form-row">
									<div class="form-label">Susitikimo data:</div>
									<div class="form-value">
										<input type="text" value="${curentDate}" id="datepicker"
											name="date" required>
										<input type="text" id="timepicker" name="time" class="timepicker" placeholder="Laikas" required>	
									</div>

								</div>
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
							<p id="noDoctorsError" style="display:none;" class="no-doctors-selected">Prašome pasirinkti daktarą</p>
								<input type="submit" value="Rezervuoti">
							</div>

						</form>
					</c:otherwise>

				</c:choose>

			</div>
		</div>

	</div>
</body>
</html>
