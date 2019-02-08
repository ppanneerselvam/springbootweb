<!DOCTYPE html>
<%-- <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
	
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"/>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<!-- /.container -->

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>
	$(document).ready(function() {
		$('#example').DataTable( {
			"ajax": "object.txt",
			"columns": [
				{ "data": "Reference" },
				{ "data": "AccountNumber" },
				{ "data": "Description" },
				{ "data": "Start Balance" },
				{ "data": "Mutation" },
				{ "data": "End Balance" }
			]
		} );
	} );
</script>

<!-- 
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">RaboBank</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#about">About</a></li>
				</ul>
			</div>
		</div>
	</nav>

<div class="container">
<form method="POST" action="/upload" enctype="multipart/form-data">
    <input type="file" name="file" /><br/><br/>
    <input type="submit" value="Submit" />
</form>
</div>
	<div class="container">

		<div class="starter-template">
									
			<table id="example" class="display" style="width:100%">
        <thead>
		<h1 align="center">Customer Statement Details</h1>
            <tr>
                <th>Reference</th>
                <th>AccountNumber</th>
                <th>Description</th>
                <th>Start Balance</th>
                <th>Mutation</th>
                <th>End Balance</th>
            </tr>
        </thead>
        <tfoot>
            <tr>
                <th>Reference</th>
                <th>AccountNumber</th>
                <th>Description</th>
                <th>Start Balance</th>
                <th>Mutation</th>
                <th>End Balance</th>
            </tr>
        </tfoot>
    </table>
			
		</div>

	</div>
	
</body>

</html>
