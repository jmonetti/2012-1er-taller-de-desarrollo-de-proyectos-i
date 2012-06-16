<html>
	<head>
		<meta http-equiv="cache-control" content="no-cache">
		<title>Seguridad</title>
		<script type="text/javascript" 
			src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAJ-lN0BVnxuQ06N3YUyFS_iUA8kuoFYVI&sensor=false">
		</script>
		<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="js/maps.js"></script>
		<script type="text/javascript" src="js/main.js"></script>
        <script type="text/javascript">
            $(function(){
	            mapHandler.init();
                main.loadMarkers();
                main.refresh();
            });
        </script>

		<link href="css/default.css"  type="text/css" rel="Stylesheet" />
	</head>
	<body>
		<form method="POST">
			<input type="hidden" id="action" name="action" />
			<div id="map_canvas" style="width:700px; height:500px"></div>
			<div>
				<h2>Current Emergencies</h2>
				<ul id="emergencies">
				</ul>
			</div>
		</form>
	</body>
</html>
