<html>
	<head>
		<meta http-equiv="cache-control" content="no-cache">
		<title>Seguridad - Backend</title>
		<link href="css/default.css"  type="text/css" rel="Stylesheet" />
		<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="js/main.js"></script>
        <script type="text/javascript">
            $(function(){
	            main.init();
            });
        </script>
	</head>
	<body>
		<form method="POST">
			<div>
				<input type="button" name="btnReset" value="Reset all" action="reset.php" />
				<div>
					Lat: <input type="text" name="lat" value="" />
					Long: <input type="text" name="lng" value="" />
					<input type="button" name="btnAdd" value="Add" action="service.php" />
				</div>
			</div>
		</form>
	</body>
</html>
