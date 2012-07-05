<html>
<head>
<meta http-equiv="cache-control" content="no-cache">
<title>Seguridad - Backend</title>
<script type="text/javascript"
	src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAJ-lN0BVnxuQ06N3YUyFS_iUA8kuoFYVI&sensor=false">
		</script>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/maps.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript">
	$(function(){
		main.init();
	});
</script>
<link href="css/default.css" type="text/css" rel="Stylesheet" />
</head>
<body>
	<div id="main">
		<?php include('header.php'); ?>
		<div id="content">
			<h2>Backend</h2>
			<form method="POST">
				<div>
					<h2>Eliminar todas</h2>
					<input type="button" name="btnReset" value="Reset all" action="reset.php" />
					<h2>Ingresar Coordenadas</h2>
					<div>
						Lat: <input type="text" name="lat" value="" />						
						Long: <input type="text" name="lng" value="" />
						<input type="button" name="btnAdd" value="Add" action="service.php" />
					</div>
				</div>
			</form>
			<div class="clear"></div>
		</div>
		<?php include('footer.php'); ?>
	</div>
</body>
</html>
