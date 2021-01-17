<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/jsp/init-common.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="/jsp/header.jsp" />
<body>
	<jsp:include page="/jsp/navigation.jsp" />
	<script>
		$(function() {
			$("#datepicker").datepicker();
			$('.card-content').on('click', function() {
				$('.card-content.active').removeClass('active');
				$(this).addClass('active');
				$('#doctorId').val($('.card-content.active').attr('id'));
			});
		});
	</script>
	<input type="hidden" id="doctorId" value="" />

	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-2 sidenav">
				<ul class="nav navbar-nav sidebar-navbar-nav">
					<li class="active"><a href="#">Rezervuoti laiką</a></li>
					<li><a href="#">Patikrinti rezervaciją</a></li>
				</ul>
			</div>
			<div class="col-sm-8 text-left">
				<h1>Rezervuokite susitikimą</h1>
				<p>Nuo šiol susitikius su daktarais galite ir internetu!
					Apačioje pasirinkus daktarą ir užpildžius formą daktaras bus
					informuotas ir į susitikimą galėsite ateiti pasirinktu laiku</p>
				<div class="row">
					<ul></ul>
					<div class="col-xl-2 col-sm-6 col-12">
						<div class="card">
							<div href="#" class="card-content" id="1">

								<div class="card-body">
									<div class="media d-flex">
										<div class="align-self-center">
											<i class="icon-user success font-large-2 float-right"></i>
										</div>
										<div class="media-body text-right">
											<h3>Vaidas Morkūnas</h3>
											<span>Dermatologas</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-2 col-sm-6 col-12">
						<div class="card">
							<div class="card-content" id="2">
								<div class="card-body">
									<div class="media d-flex">
										<div class="align-self-center">
											<i class="icon-user success font-large-2 float-right"></i>
										</div>
										<div class="media-body text-right">
											<h3>Darius Kazlauskas</h3>
											<span>Kardiologas</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-2 col-sm-6 col-12">
						<div class="card">
							<div class="card-content" id="3">
								<div class="card-body">
									<div class="media d-flex">
										<div class="align-self-center">
											<i class="icon-user success font-large-2 float-right"></i>
										</div>
										<div class="media-body text-right">
											<h3>Marius Daukšas</h3>
											<span>Chirurgas</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--<div class="col-xl-3 col-sm-6 col-12">
        <div class="card">
          <div class="card-content">
            <div class="card-body">
              <div class="media d-flex">
                <div class="align-self-center">
                  <i class="icon-user success font-large-2 float-right"></i>
                </div>
                <div class="media-body text-right">
                  <h3>423</h3>
                  <span>Total Visits</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>-->
				</div>

				<hr>
				<form action="add" method="post">
					<div class="form-table">
						<div class="form-row">
							<div class="form-label">Susitikimo data:</div>
							<div class="form-value">
								<input type="text" id="datepicker" name="date">
							</div>

						</div>
						<div class="form-row">
							<div class="form-label">Vardas:</div>
							<div class="form-value">
								<input type="text" name="vardas">
							</div>

						</div>
						<div class="form-row">
							<div class="form-label">Pavarde:</div>
							<div class="form-value">
								<input type="text" name="pavarde">
							</div>

						</div>
						<div class="form-row">
							<div class="form-label">Asmens kodas:</div>
							<div class="form-value">
								<input type="number" name="ask">
							</div>

						</div>
					</div>

					<!-- TODO: validacija -->
					<div class="form-buttons">
						<input type="submit" value="Rezervuoti">
					</div>

				</form>
			</div>
		</div>

	</div>
</body>
</html>
