<?php
	include_once('./includes/GeoCoding.php');
	//$dbconn = "mongodb://root:JZrDH8OLFGoAhEkJSig3@seguridad-jfacorro-db-0.dotcloud.com:24854";
	//$mongo = new Mongo($dbconn);
	$mongo = new Mongo();
	$db = $mongo->urban_security;

	if(array_key_exists("data", $_REQUEST)) {
		$json = $_REQUEST["data"];
		echo $json;
		$data = json_decode ($json, true);
		$db->emergencies->insert($data);
	}
	
	$action = $_REQUEST["action"];
	
	switch ($action) {
		case 'reset':
			echo 'Droppin...';
			$db->emergencies->drop();
		break;
		case 'add':
			echo 'Adding...';
			$lat = $_REQUEST["txtLat"];
			$long = $_REQUEST["txtLong"];
			$location = array('lat' => $lat, 'lng' => $long);
			echo json_encode($location);
			$db->emergencies->insert($location);
		break;
	}
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
		<script type="text/javascript" src="js/main.js"></script>

		<link href="css/default.css"  type="text/css" rel="Stylesheet" />
	</head>
	<body>
		<form method="POST">
			<input type="hidden" id="action" name="action" />
			<div id="map_canvas" style="width:700px; height:500px"></div>
			<div>
				<input type="button" name="btnReset" value="Reset all" action="reset" />
				<div>
					Lat <input type="text" name="txtLat" value="" />
					Long <input type="text" name="txtLong" value="" />
					<input type="button" name="btnAdd" value="Add" action="add" />
				</div>
			<div>
			<div>
				<h2>Current Emergencies</h2>
			<?php
				$cursor = $db->emergencies->find();
				// iterate through the results
				if($cursor) {
					$markers = '';
					foreach ($cursor as $obj) {
						?>
						<location lat="<?php echo $obj['lat']; ?>" lng="<?php echo $obj['lng'] ?>"></location><br />
						<?
						$markers .= 'mapHandler.markers.push('.json_encode($obj).');';
					}
					?>
					<script type="text/javascript">
						<?php echo $markers; ?>
					</script>
					<?php
				}
			?>
			</div>
		</form>
	</body>
</html>
