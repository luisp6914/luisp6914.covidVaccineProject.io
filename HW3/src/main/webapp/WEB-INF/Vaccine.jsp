<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Required meta tags -->
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
    integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
    integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
<title>Vaccine</title>
</head>
<body class="container">
<p>
<span> <a href='NewVaccine'>New vaccine</a> </span>
|
<span> <a href='NewDoses'>New Doses</a> </span>
|
<span> <a href='FrontPage'>Front Page</a> </span>
</p>

<table class="table table-striped">
<tr>

<th> Vaccine </th>
<th> Doses Required </th>
<th> Days Between Doses</th>
<th> Doses Received </th>
<th> Total Doses Left </th>
<th> </th>
</tr>

<c:forEach items="${entries}" var="entry">
<tr>
<td> ${entry.vaccine} </td>
<td> ${entry.dosesRequired }</td>
<td> ${entry.daysBetweenDoses} </td>
<td> ${entry.totalDosesReceived} </td>
<td> ${entry.totalDosesLeft} </td>
<td> <a href="EditEntry?id=${entry.id}">Edit</a></td>
</tr>
</c:forEach>


</table>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
    integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
    crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
    crossorigin="anonymous"></script>
</body>
</html>