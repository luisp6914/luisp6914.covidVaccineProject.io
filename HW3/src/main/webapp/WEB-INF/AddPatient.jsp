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
<title>Add Patient</title>
</head>
<body class="container">
	<form action="AddPatient" method="post">
		<table class="table table-striped">
			<tr>
				<td>Patient First Name</td>
				<td><input type="txt" name="patientFirstName"></td>
				
				
			</tr>
			<tr>
				<td>Patient Last Name</td>
				<td><input type="txt" name="patientLastName"></td>
			</tr>

			<tr>
				<td>Vaccine</td>
				<td><select
					class="btn bg-light text-primary btn-sm rounded-pill"
					name='patientVaccine'>
						<c:forEach items="${entries}" var="entry">
							<c:if test="${entry.totalDosesLeft > 0 }">
								<option class="text-primary" value="${entry.vaccine}">${entry.vaccine}</option>
							</c:if>

						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td colspan="2"><button class="rounded-pill text-primary">Add</button></td>
			</tr>

		</table>

	</form>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
		crossorigin="anonymous"></script>
</body>
</html>