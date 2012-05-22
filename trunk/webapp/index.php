<?php
	$dbconn = "mongodb://root:JZrDH8OLFGoAhEkJSig3@seguridad-jfacorro-db-0.dotcloud.com:24854";	
	$mongo = new Mongo($dbconn);
	//$mongo = new Mongo();
	$db = $mongo->urban_security;
?>
<html>
	<head>
		<meta http-equiv="cache-control" content="no-cache">
		<title>Urban-Security</title>
		<script type="text/javascript" 
			src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAJ-lN0BVnxuQ06N3YUyFS_iUA8kuoFYVI&sensor=false">
		</script>
		<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="js/maps.js"></script>
	</head>
	<body>
		<div style="float:right">
		<?php
			$cursor = $db->emergencies->find();
			// iterate through the results
			if($cursor)
			{
				foreach ($cursor as $obj) {
					echo '('.$obj["lat"].':'.$obj["long"].")<br>";

					$location = "{'lat':" . $obj["lat"] . ", 'long':".$obj["long"]."}";
					?>
					<script type="text/javascript">
						mapHandler.markers.push(<?php echo $location; ?>);
					</script>
					<?php
				}
			}
		?>
		</div>
		<div id="map_canvas" style="width:700px; height:500px; float:left;"></div>
		<?php
			if(array_key_exists("data", $_REQUEST))
			{
				$json = $_REQUEST["data"];
				echo $json;
				$data = json_decode ($json, true);
				var_dump($data);
				$db->emergencies->insert($data);
			}
		?>
	</body>
</html>
