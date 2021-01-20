<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/jsp/init-common.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<style><%@include file="/css/calendar.css"%></style>
</head>
<jsp:include page="../header.jsp" />

<body>
	<jsp:include page="/jsp/navigation.jsp" />
	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-4 text-left">
				<div class="row">
					<div class="calendar-container container">
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
			<div class="col-sm-2 text-left">
				<div class="row">
					<div class="container" id="meetingsList">
						
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
<script src="../js/calendar.js"></script>
<script>
$( ".day, .today" ).click(function() {
	(function (self) {
		$.getJSON('getMeetingsForTheDay',{day: $(self).attr('date'), doctorId: 1}, function (data) {
			$("#meetingsList").empty();
			$.each(data, function(index) {
				$("#meetingsList").append('<div class="card"><div href="#" ><div style="float: right; padding-top: 10px;" class="col-sm-4 text-left"><div class="card" onclick="deleteMeeting(this)" meetingId="'+data[index].id+'" style="margin-bottom: 10px;"><div href="#" style="display: flex;" class="card-content"><div class="card-body" style="display: block;"><div style="justify-content: center;" class="media d-flex"><div class="align-self-center"><i class="icon-ban warning font-large-1 float-right"></i></div></div></div></div></div></div><div class="card-body"><div class="media d-flex"><div class="align-self-center"><i class="icon-user success font-large-2 float-right"></i></div><div class="media-body text-right" style="width: 80%;"><h3>'+data[index].person.name+' '+data[index].person.surname+'</h3><span>'+new Date(data[index].meetingDate).getHours()+'h '+new Date(data[index].meetingDate).getMinutes() +'min</span></div></div></div></div></div></div></div>');
			});
			
        });
	})(this);
});

function deleteMeeting(e) {
	if (confirm("Ar tikrai norite atšaukti susitikimą?")){
		(function (self) {
			$.getJSON('deleteMeetingForDoctor',{day: $(".day.active").attr('date'), 
				meetingId: e.getAttribute('meetingid'), 
				doctorId: 1}, 
				function (data) {
			});
			$('.active').trigger( "click" );
		})(this);
	}
};
</script>
</html>
