<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Required meta tags -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
	integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<title>Patient Management</title>
</head>
<body class="container">

	<p class="pt-5">
		<a href="AddPatient" class="btn btn-link bg-light rounded-pill">New
			Patient</a> | <a href="FrontPage"
			class="btn btn-link bg-light rounded-pill">Front Page</a>
	</p>
	<div>
		<table class="table table-striped">
			<tr>
				<th>Patient Id</th>
				<th>Patient Name</th>
				<th>Vaccine Id</th>
				<th>Vaccine Name</th>
				<th>Doses Required </th>
				<th>1st Dose Date</th>
				<th>2nd Dose Date</th>
			</tr>
			<c:forEach items="${patients}" var="patient">
				<tr>
					<td>${patient.patientId}</td>
					<td>${patient.patientName}</td>
					<td>${patient.vaccineId}</td>
					<td>${patient.vaccineName}</td>
					<td>${patient.vaccineDosesRequired } </td>
					<td>${patient.firstDoseDate}</td>
					<td><c:choose>
							<c:when test="${patient.vaccineDosesRequired == 1}">-</c:when>
							<c:when test="${patient.recievedSecondDose == true}">${patient.secondDoseDate}</c:when>
							<c:when test="${patient.vaccineDoseLeft == 0}">Out Of Stock</c:when>
							<c:otherwise>
								<form action="Patient" method="post">
									<input type="hidden" value="${patient.vaccineId }"
										name="vaccineId"> <input type="hidden"
										value="${ patient.patientId}" name="patientId">
									<button class="text-primary rounded-pill">Receive</button>
								</form>
							</c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>
		</table>
	</div>


	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
		crossorigin="anonymous"></script>

</body>
</html>